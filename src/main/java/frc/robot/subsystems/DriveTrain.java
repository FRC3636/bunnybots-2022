package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class DriveTrain implements Subsystem {
    // these fields represent the motor hardware
    // they are declared private because they should
    // never be accessed directly outside this subsystem
    private final Spark leftMotor = new Spark(Constants.DriveTrain.LEFT_MOTOR_PORT);
    private final Spark rightMotor = new Spark(Constants.DriveTrain.RIGHT_MOTOR_PORT);
    private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

    public DriveTrain() {
        leftMotor.setInverted(true);
    }

    public void arcadeDrive(double speed, double rotation) {
        robotDrive.arcadeDrive(speed, rotation);
    }

    public void tankDrive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }
}
