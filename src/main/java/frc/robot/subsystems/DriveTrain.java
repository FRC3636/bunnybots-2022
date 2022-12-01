package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain implements Subsystem {
    // these fields represent the motor hardware
    // they are declared private because they should
    // never be accessed directly outside this subsystem
    private final Spark leftMotor = new Spark(Constants.DriveTrain.LEFT_MOTOR_PORT);
    private final Spark rightMotor = new Spark(Constants.DriveTrain.RIGHT_MOTOR_PORT);
    private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);


    private final Encoder leftEncoder = new Encoder(
            Constants.DriveTrain.LEFT_ENCODER_PORT_A,
            Constants.DriveTrain.LEFT_ENCODER_PORT_B,
            true
    );
    private final Encoder rightEncoder = new Encoder(
            Constants.DriveTrain.RIGHT_ENCODER_PORT_A,
            Constants.DriveTrain.RIGHT_ENCODER_PORT_B,
            false
    );

    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
            new Rotation2d()
    );

    public DriveTrain() {
        leftMotor.setInverted(true);

        leftEncoder.setDistancePerPulse(Constants.DriveTrain.WHEEL_CIRCUMFERENCE / Constants.DriveTrain.PULES_PER_ROTATION);
        rightEncoder.setDistancePerPulse(Constants.DriveTrain.WHEEL_CIRCUMFERENCE / Constants.DriveTrain.PULES_PER_ROTATION);
    }

    @Override
    public void periodic() {
        double dl = leftEncoder.getDistance();
        double dr = rightEncoder.getDistance();
        System.out.println(leftEncoder.getRaw());
        System.out.println(rightEncoder.getRaw());
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
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void arcadeDrive(double speed, double rotation) {
        robotDrive.arcadeDrive(speed, rotation);
    }

    public void tankDrive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }
}
