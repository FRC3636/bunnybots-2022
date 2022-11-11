package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class DriveTrain implements Subsystem {
    // these fields represent the motor hardware
    // they are declared private because they should
    // never be accessed directly outside this subsystem
    private final Spark leftMotor = new Spark(1);
    private final Spark rightMotor = new Spark(2);
    private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

    public DriveTrain() {
        leftMotor.setInverted(true);
    }

    public void drive(double leftOrSpeed, double rightOrRotation, boolean useTankDrive) {
        if (useTankDrive) {
            robotDrive.tankDrive(leftOrSpeed, rightOrRotation);
        } else {
            robotDrive.arcadeDrive(leftOrSpeed, rightOrRotation);
        }
    }
}
