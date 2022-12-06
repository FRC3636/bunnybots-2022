package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;
import frc.robot.Constants;


public class Elevator extends SubsystemBase{

    private final TalonSRX topIndexerMotor = new TalonSRX(Constants.Elevator.ELEVATOR_MOTOR_ID);

    private boolean running = false;



    @Override
    public void periodic() {
        if(running){
            topIndexerMotor.set(TalonSRXControlMode.PercentOutput, Constants.Elevator.ELEVATOR_SPEED);
        }
    }

    public void setRunning(boolean running){
        this.running = running;
    }
    
}
