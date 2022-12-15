package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Intake implements Subsystem {
    private final DigitalInput resetSwitch = new DigitalInput(Constants.Intake.INTAKE_RESET_SWITCH_PORT);
    private final PIDController positionController = new PIDController(Constants.Intake.INTAKE_CONTROLLER_P, Constants.INTAKE_CONTROLLER_I, Constants.Intake.INTAKE_CONTROLLER_D);

    private final CANSparkMax intakeMotor = new CANSparkMax(Constants.Intake.INTAKE_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final RelativeEncoder intakeEncoder = intakeMotor.getEncoder();
    private State currentState = State.Stop;

    @Override
    public void periodic() {
        if (resetSwitch.get()) {
            intakeEncoder.setPosition(0);
        }

        switch (currentState) {
            case Intake:
                intakeMotor.set(Constants.Intake.INTAKE_SPEED);
                break;
            case Outtake:
                intakeMotor.set(-Constants.Intake.INTAKE_SPEED);
                break;
            case Stop:
                intakeMotor.set(positionController.calculate(intakeEncoder.getPosition(), Math.round(intakeEncoder.getPosition()) + Constants.Intake.INTAKE_STOP_POSITION));
                break;
        }
    }


    public void setState(State state) {
        currentState = state;
    }

    public enum State {
        Intake,
        Outtake,
        Stop,
    }
}