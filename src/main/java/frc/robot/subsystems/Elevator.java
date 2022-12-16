package frc.robot.subsystems;

import org.ejml.equation.IntegerSequence.Range;
import org.w3c.dom.ranges.RangeException;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class Elevator implements Subsystem {

    private final VictorSPX elevatorMotor = new VictorSPX(Constants.Elevator.ELEVATOR_MOTOR_PORT);
    private final CANSparkMax doorMotor = new CANSparkMax(Constants.Elevator.ELEVATOR_DOOR_MOTOR_PORT, MotorType.kBrushed); //TODO: ask THEM if it is a spark
    private final PowerDistribution powerDistribution = new PowerDistribution();
    private final DigitalInput bottomLimitSwitch = new DigitalInput(
            Constants.Elevator.BOTTOM_LIMIT_SWITCH_CHANNEL);
    private final DigitalInput topLimitSwitch = new DigitalInput(
            Constants.Elevator.TOP_LIMIT_SWITCH_CHANNEL);


    @Override
    public void periodic() {
        if (isDoorStalled()) {
            doorMotor.set(0);
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
        if (powerDistribution.getCurrent(Constants.Elevator.DOOR_MOTOR_PDP_CHANNEL) > Constants.Elevator.MAX_DOOR_DRAW) {
            return true;
        }

        return false;
    }

    public void setDoorSpeed(double speed) {
        if (speed < -1 || speed > 1) {
            throw new RangeException((short)0, "Speed must be between -1 and 1");
        }

        if (isDoorStalled()) {
            doorMotor.set(0);
            return;
        }

        doorMotor.set(speed);
    }
}