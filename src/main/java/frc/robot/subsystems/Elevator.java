package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class Elevator extends SubsystemBase{

    private final TalonSRX topIndexerMotor = new TalonSRX(Constants.Elevator.ELEVATOR_MOTOR_ID);

    @Override
    public void periodic() {
        // TODO Auto-generated method stub
    }
    
}
