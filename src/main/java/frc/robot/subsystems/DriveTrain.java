package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain implements Subsystem {
    // these fields represent the motor hardware
    // they are declared private because they should
    // never be accessed directly outside this subsystem
    private final CANSparkMax rightMotorMain = new CANSparkMax(
            Constants.DriveTrain.RIGHT_MOTOR_INDEX_MAIN,
            MotorType.kBrushed
    ); // has encoder
    private final CANSparkMax rightMotorFollower = new CANSparkMax(
            Constants.DriveTrain.RIGHT_MOTOR_INDEX_FOLLOWER,
            MotorType.kBrushed
    );
    private final RelativeEncoder rightEncoder;
    private final CANSparkMax leftMotorMain = new CANSparkMax(
            Constants.DriveTrain.LEFT_MOTOR_INDEX_MAIN,
            MotorType.kBrushed
    ); // has beans encoder
    private final CANSparkMax leftMotorFollower = new CANSparkMax(
            Constants.DriveTrain.LEFT_MOTOR_INDEX_FOLLOWER,
            MotorType.kBrushed
    );
    private final RelativeEncoder leftEncoder;
    private final DifferentialDrive robotDrive = new DifferentialDrive(rightMotorMain, leftMotorMain);

    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
            new Rotation2d()
    );

    public DriveTrain() {
        rightMotorFollower.follow(rightMotorMain);
        leftMotorFollower.follow(leftMotorMain);
        rightMotorMain.setInverted(true);

        // TODO: get actual conversion factor
        // hopefully encoder type + countsPerRev is right
        leftEncoder = leftMotorMain.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 4096);
        leftEncoder.setPositionConversionFactor(Constants.DriveTrain.WHEEL_CIRCUMFERENCE / Constants.DriveTrain.PULES_PER_ROTATION);
        rightEncoder = rightMotorMain.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 4096);
        rightEncoder.setPositionConversionFactor(Constants.DriveTrain.WHEEL_CIRCUMFERENCE / Constants.DriveTrain.PULES_PER_ROTATION);
    }

    @Override
    public void periodic() {
        rightMotorFollower.follow(rightMotorMain);
        leftMotorFollower.follow(leftMotorMain);
        double dl = leftEncoder.getPosition();
        double dr = rightEncoder.getPosition();
        double w = Constants.DriveTrain.TRACK_WIDTH;
        Rotation2d rotation = new Rotation2d((dr - dl) / w);
        odometry.update(rotation, dl, dr);
        RobotContainer.field.setRobotPose(odometry.getPoseMeters());
    }

    public void resetOdometryTo(Pose2d pose2d, Rotation2d rotation2d) {
        resetEncoders();
        odometry.resetPosition(pose2d, rotation2d);
    }

    private void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public void arcadeDrive(double speed, double rotation) {
        robotDrive.arcadeDrive(speed, rotation);
    }

    public double getDistance() {
        return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2;
    }

    public void tankDrive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }
}
