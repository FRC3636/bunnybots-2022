package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class Elevator implements Subsystem {

    private final VictorSPX elevatorMotor = new VictorSPX(Constants.Elevator.ELEVATOR_MOTOR_PORT);
    private final DigitalInput bottomLimitSwitch = new DigitalInput(
            Constants.Elevator.BOTTOM_LIMIT_SWITCH_CHANNEL);
    private final DigitalInput topLimitSwitch = new DigitalInput(
            Constants.Elevator.TOP_LIMIT_SWITCH_CHANNEL);

    public void moveElevator(double percentOutput) {

        if ((topLimitSwitch.get() && percentOutput > 1) || (bottomLimitSwitch.get() && percentOutput < 1)) {
            return;
        }

        elevatorMotor.set(ControlMode.PercentOutput, percentOutput);
    }
}