package frc.robot.subsystems;

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
    private final CANSparkMax rightMotor1 = new CANSparkMax(1, MotorType.kBrushed); // has encoder
    private final CANSparkMax rightMotor2 = new CANSparkMax(2, MotorType.kBrushed);
    private final CANSparkMax leftMotor1 = new CANSparkMax(4, MotorType.kBrushed); // has encoder
    private final CANSparkMax leftMotor2 = new CANSparkMax(3, MotorType.kBrushed);
    private final DifferentialDrive robotDrive = new DifferentialDrive(rightMotor1, leftMotor1);

    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
            new Rotation2d()
    );

    public DriveTrain() {
        rightMotor2.follow(rightMotor1);
        leftMotor2.follow(leftMotor1);
        leftMotor1.setInverted(true);

        // TODO: get acutal conversion factor
        leftMotor1.getEncoder().setPositionConversionFactor(Constants.DriveTrain.WHEEL_CIRCUMFERENCE / Constants.DriveTrain.PULES_PER_ROTATION);
        rightMotor1.getEncoder().setPositionConversionFactor(Constants.DriveTrain.WHEEL_CIRCUMFERENCE / Constants.DriveTrain.PULES_PER_ROTATION);
    }

    @Override
    public void periodic() {
        double dl = leftMotor1.getEncoder().getPosition();
        double dr = rightMotor1.getEncoder().getPosition();
        double w = Constants.DriveTrain.TRACK_WIDTH;
        Rotation2d rotation = new Rotation2d((dr - dl) / w);
        odometry.update(rotation, dl, dr);
        RobotContainer.field.setRobotPose(odometry.getPoseMeters());
        System.out.println(
        "Heading: " + rotation + ", Pos: (" + odometry.getPoseMeters().getX() + ", " + odometry.getPoseMeters().getY() + ")");
    }

    public void resetOdometryTo(Pose2d pose2d, Rotation2d rotation2d) {
        resetEncoders();
        odometry.resetPosition(pose2d, rotation2d);
    }

    private void resetEncoders() {
        leftMotor1.getEncoder().setPosition(0);
        rightMotor1.getEncoder().setPosition(0);
    }

    public void arcadeDrive(double speed, double rotation) {
        robotDrive.arcadeDrive(speed, rotation);
    }

    public void tankDrive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }
}
