package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class Elevator implements Subsystem {

    private final VictorSPX elevatorMotor = new VictorSPX(Constants.Elevator.ELEVATOR_MOTOR_PORT);
    private final VictorSPX doorMotor = new VictorSPX(Constants.Elevator.ELEVATOR_DOOR_MOTOR_PORT);
    private final PowerDistribution powerDistribution = new PowerDistribution();
    private final DigitalInput bottomLimitSwitch = new DigitalInput(
            Constants.Elevator.BOTTOM_LIMIT_SWITCH_CHANNEL);
    private final DigitalInput topLimitSwitch = new DigitalInput(
            Constants.Elevator.TOP_LIMIT_SWITCH_CHANNEL);


    @Override
    public void periodic() {
        if (isDoorStalled()) {
            doorMotor.set(ControlMode.PercentOutput, 0);
        }
    }

    public void moveElevator(double percentOutput) {

        if ((topLimitSwitch.get() && percentOutput > 1) || (bottomLimitSwitch.get() && percentOutput < 1)) {
            elevatorMotor.set(ControlMode.PercentOutput, 0);
            return;
        }

        elevatorMotor.set(ControlMode.PercentOutput, percentOutput);
    }

    public boolean isDoorStalled() {
        return powerDistribution.getCurrent(Constants.Elevator.DOOR_MOTOR_PDP_CHANNEL) > Constants.Elevator.MAX_DOOR_DRAW;
    }

    public void setDoorSpeed(double speed) {
        // Speed should never be above 1 or below -1
        Math.min(speed, 1);
        Math.max(speed, -1);

        if (isDoorStalled()) {
            doorMotor.set(ControlMode.PercentOutput, 0);
            return;
        }

        doorMotor.set(ControlMode.PercentOutput, speed);
    }
}