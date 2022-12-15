package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.targeting.PhotonTrackedTarget;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
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
    private final AHRS navX = new AHRS();
    private boolean doAssistOdometryUsingCamera = false;
    private Camera camera;

    private static final Pose2d aprilTagAbsolutePosition = new Pose2d(); // TODO: add real location??


    private final Encoder leftEncoder = new Encoder(
            Constants.DriveTrain.LEFT_ENCODER_PORT_A,
            Constants.DriveTrain.LEFT_ENCODER_PORT_B,
            false
    );
    private final Encoder rightEncoder = new Encoder(
            Constants.DriveTrain.RIGHT_ENCODER_PORT_A,
            Constants.DriveTrain.RIGHT_ENCODER_PORT_B,
            true
    );

    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
            new Rotation2d()
    );

    public DriveTrain(Camera camera) {
        this.camera = camera;
        
        rightMotor.setInverted(true);

        double distancePerPulse =
                Constants.DriveTrain.WHEEL_CIRCUMFERENCE
                        / Constants.DriveTrain.PULSES_PER_REVOLUTION
                        / 4; // magic number that by all means shouldn't be here but is
        leftEncoder.setDistancePerPulse(distancePerPulse);
        rightEncoder.setDistancePerPulse(distancePerPulse);
    }

    private void updateOdometryUsingCamera() {
        Optional<Transform2d> targetRelative = camera.getTargetRelative2D();
        if (targetRelative.isEmpty()) return;
        
        Pose2d newOdometry = Camera.calculateRobotPositionOnField(aprilTagAbsolutePosition, targetRelative.get());
        resetOdometryTo(newOdometry);
        // System.out.println(String.format("Spotted april tag - inferred pose is: %s", newOdometry));
    }

    @Override
    public void periodic() {
        if (doAssistOdometryUsingCamera) updateOdometryUsingCamera();
        double dl = leftEncoder.getDistance();
        double dr = rightEncoder.getDistance();
        odometry.update(navX.getRotation2d(), dl, dr);
        RobotContainer.field.setRobotPose(odometry.getPoseMeters());
    }

    public void resetOdometryTo(Pose2d pose2d) {
        resetEncoders();
        odometry.resetPosition(pose2d, navX.getRotation2d());
    }

    private void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
        navX.reset();
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                leftEncoder.getRate(),
                rightEncoder.getRate()
        );
    }

    public void stop() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    public void arcadeDrive(double speed, double rotation) {
        robotDrive.arcadeDrive(speed, rotation);
    }

    public void tankDrive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }

    public void tankDriveWithRawVoltage(double leftV, double rightV) {
        leftMotor.setVoltage(leftV);
        rightMotor.setVoltage(rightV);
    }
}
