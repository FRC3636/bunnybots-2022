package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private final CANSparkMax actuationMotor = new CANSparkMax(
            Constants.Intake.ACTUATION_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless
    );

    private final Spark intakeMotor = new Spark(
            Constants.Intake.INTAKE_MOTOR_PORT
    );

    private final DigitalInput bottomLimitSwitch = new DigitalInput(
            Constants.Intake.ACTUATION_LIMIT_SWITCH_ID
    );

    private Position position = Position.Coast;

    private double targetAngle = Constants.Intake.ACTUATION_DEGREES;

    // fixme: no protection against going backwards
    @Override
    public void periodic() {
        switch(position) {
            case Up: {
                if(this.getEncoderPositionDegrees() < targetAngle) {
                    actuationMotor.set(Constants.Intake.ACTUATION_MOVE_UP_SPEED);
                } else {
                    actuationMotor.set(0);
                    this.position = Position.HoldUp;
                }
                break;
            }
            case HoldUp: {
                if(this.getEncoderPositionDegrees() < targetAngle) {
                    actuationMotor.set(Constants.Intake.ACTUATION_HOLD_SPEED);
                } else {
                    actuationMotor.set(0);
                }
                break;
            }
            case Down: {
                if(bottomLimitSwitch.get()) {
                    actuationMotor.set(0);
                    actuationMotor.getEncoder().setPosition(0);
                } else {
                    actuationMotor.set(-Constants.Intake.ACTUATION_MOVE_DOWN_SPEED);
                }
                break;
            }
        }
    }

    public void goUp() {
        position = Position.Up;
        actuationMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void goDown() {
        position = Position.Down;
        actuationMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    private double getEncoderPositionDegrees() {
        return actuationMotor.getEncoder().getPosition()
                / Constants.Intake.ACTUATION_MOTOR_GEAR_RATIO
                * 360;
    }

    public double getTargetAngle() {
        return targetAngle;
    }

    public void setTargetAngle(double targetAngle) {
        this.targetAngle = targetAngle;
    }

    public void stopWheels() {
        intakeMotor.stopMotor();
    }

    public void setWheelSpeed(double speed) {
        intakeMotor.set(speed);
    }

    private enum Position {
        Up,
        Down,
        HoldUp,
        Coast // does nothing
    }
}
