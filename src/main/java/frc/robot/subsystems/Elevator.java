package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;


public class Elevator implements Subsystem {

    private final VictorSPX elevatorMotor = new VictorSPX(Constants.Elevator.ELEVATOR_MOTOR_PORT);
    private final VictorSPX doorMotor = new VictorSPX(Constants.Elevator.ELEVATOR_DOOR_MOTOR_INDEX);
    private final PowerDistribution powerDistribution = new PowerDistribution();
    private final DigitalInput bottomLimitSwitch = new DigitalInput(
            Constants.Elevator.BOTTOM_LIMIT_SWITCH_CHANNEL);
    private final DigitalInput topLimitSwitch = new DigitalInput(
            Constants.Elevator.TOP_LIMIT_SWITCH_CHANNEL);

    private DoorPosition doorPosition = DoorPosition.Close;

    public Elevator() {
        doorMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Door Current", powerDistribution.getCurrent(Constants.Elevator.DOOR_MOTOR_PDP_CHANNEL));

        switch (doorPosition) {
            case Close:
                if(doorStalled()) {
                    doorPosition = DoorPosition.Hold;
                    break;
                }
                doorMotor.set(VictorSPXControlMode.PercentOutput, 0.6);
                break;
            case Open:
                if(doorStalled()) {
                    doorPosition = DoorPosition.Hold;
                    break;
                }
                doorMotor.set(VictorSPXControlMode.PercentOutput, -0.4);
                break;
            case Hold:
                doorMotor.set(VictorSPXControlMode.PercentOutput, 0);

        }
    }

    public void moveElevator(double percentOutput) {
        if ((topLimitSwitch.get() && percentOutput > 1) || (bottomLimitSwitch.get() && percentOutput < 1)) {
            elevatorMotor.set(VictorSPXControlMode.PercentOutput, 0);
            return;
        }

        elevatorMotor.set(VictorSPXControlMode.PercentOutput, percentOutput);
    }

    public void moveDoor(double percentOutput) {
        doorMotor.set(VictorSPXControlMode.PercentOutput, percentOutput);
    }

    public boolean doorStalled() {
        return powerDistribution.getCurrent(Constants.Elevator.DOOR_MOTOR_PDP_CHANNEL) > Constants.Elevator.MAX_DOOR_DRAW;
    }

    public void closeDoor() {
        doorPosition = DoorPosition.Close;
    }

    public void openDoor() {
        doorPosition = DoorPosition.Open;
    }

    public enum DoorPosition {
        Close,
        Open,
        Hold
    }
}