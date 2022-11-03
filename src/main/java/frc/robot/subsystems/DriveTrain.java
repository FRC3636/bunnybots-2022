package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class DriveTrain implements Subsystem {
    // these fields represent the motor hardware
    // they are declared private because they should
    // never be accessed directly outside this subsystem
    private final Spark leftMotor = new Spark(1);
    private final Spark rightMotor = new Spark(2);
    
    public DriveTrain() {
        leftMotor.setInverted(true);
    }

    public void drive(double leftSpeed, double rightSpeed) {
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);
    }
}