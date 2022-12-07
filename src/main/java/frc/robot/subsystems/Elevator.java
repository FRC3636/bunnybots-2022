package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;
import frc.robot.Constants;


public class Elevator extends SubsystemBase{

    private final TalonSRX topIndexerMotor = new TalonSRX(Constants.Elevator.ELEVATOR_MOTOR_ID);

    private boolean running = false;

    private double direction = 0;



    @Override
    public void periodic() {
        if(running){
            topIndexerMotor.set(TalonSRXControlMode.PercentOutput, direction);
        }
    }

    public void setRunning(boolean running, double direction){
        this.running = running;
        this.direction = direction;
    }
    
}
