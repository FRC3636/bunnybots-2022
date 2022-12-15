package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private final CANSparkMax actuationMotor = new CANSparkMax(
            Constants.Intake.ACTUATION_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless
    );

    private final CANSparkMax intakeMotor = new CANSparkMax(
            Constants.Intake.INTAKE_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless
    );

    private final DigitalInput bottomLimitSwitch = new DigitalInput(
            Constants.Intake.ACTUATION_LIMIT_SWITCH_ID
    );

    private final VictorSP indexerMotor = new VictorSP(Constants.Intake.INDEXER_MOTOR_ID);

    private Position position = Position.Coast;

    private double targetAngle = Constants.Intake.ACTUATION_DEGREES;

    // fixme: no protection against going backwards
    @Override
    public void periodic() {
        switch (position) {
            case Up: {
                if (this.getEncoderPositionDegrees() < targetAngle) {
                    actuationMotor.set(Constants.Intake.ACTUATION_MOVE_UP_SPEED);
                } else {
                    actuationMotor.set(0);
                    this.position = Position.HoldUp;
                }
                break;
            }
            case HoldUp: {
                if (this.getEncoderPositionDegrees() < targetAngle) {
                    actuationMotor.set(Constants.Intake.ACTUATION_HOLD_SPEED);
                } else {
                    actuationMotor.set(0);
                }
                break;
            }
            case Down: {
                if (bottomLimitSwitch.get()) {
                    actuationMotor.set(0);
                    actuationMotor.getEncoder().setPosition(0);
                } else {
                    actuationMotor.set(-Constants.Intake.ACTUATION_MOVE_DOWN_SPEED);
                }
                break;
            }
            case Match: {
                double delta = targetAngle - this.getEncoderPositionDegrees();

                if (delta > Constants.Intake.MAX_DELTA) {
                    actuationMotor.set(Constants.Intake.ACTUATION_MOVE_UP_SPEED);
                    break;
                }

                if (bottomLimitSwitch.get()) {
                    // failsafe
                    actuationMotor.set(0);
                    break;
                }

                if (delta < -Constants.Intake.MAX_DELTA) {
                    actuationMotor.set(-Constants.Intake.ACTUATION_MOVE_DOWN_SPEED);
                } else {
                    actuationMotor.set(0);
                }

                break;
            }
            case Coast: {
                break;
            }
            default: {
                throw new Error("Error determining intake position. " + position);
            }
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setIdleMode(CANSparkMax.IdleMode idleMode) {
        actuationMotor.setIdleMode(idleMode);
    }

    public void goUp() {
        position = Position.Up;
        actuationMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void goDown() {
        position = Position.Down;
        actuationMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    public double getEncoderPositionDegrees() {
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
        indexerMotor.stopMotor();
    }

    public void setWheelSpeed(double speed) {
        intakeMotor.set(speed * Constants.Intake.INTAKE_SPEED);
        indexerMotor.set(-speed * 0.25);
    }

    public enum Position {
        Up,
        Down,
        HoldUp,
        Match,
        Coast, // does nothing
    }
}
