package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class Elevator implements Subsystem{

    private final VictorSPX elevatorMotor = new VictorSPX(Constants.Elevator.ELEVATOR_MOTOR_PORT);
    private final DigitalInput bottomLimitSwitch= new DigitalInput(
        Constants.Elevator.BOTTOM_LIMIT_SWITCH_CHANNEL);
    private final DigitalInput topLimitSwitch= new DigitalInput(
        Constants.Elevator.TOP_LIMIT_SWITCH_CHANNEL);
    

    public Elevator(){
        

    }

    Position position = Position.Stop;

    

    
    
    public void movElevator(double percentOutput){
        
            switch(position){
                case Up: if(!topLimitSwitch.get()){elevatorMotor.set(ControlMode.PercentOutput, percentOutput);}
                    break;
                case Down: if(!bottomLimitSwitch.get()){
                    elevatorMotor.set(ControlMode.PercentOutput, percentOutput);}
                    break;
                case Stop: elevatorMotor.set(ControlMode.PercentOutput, 0);
                        break;
            }
    }
        

    public void goUp(){
        position = Position.Up;
    }

    public void goDown(){
        position = Position.Down;

    }

    public enum Position {
        Up,
        Down,
        Stop,
    }
    
}
