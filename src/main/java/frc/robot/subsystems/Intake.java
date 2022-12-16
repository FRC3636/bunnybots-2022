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
    private final PIDController positionController = new PIDController(Constants.Intake.INTAKE_CONTROLLER_P, Constants.Intake.INTAKE_CONTROLLER_I, Constants.Intake.INTAKE_CONTROLLER_D);

    private final CANSparkMax intakeMotor = new CANSparkMax(Constants.Intake.INTAKE_MOTOR_INDEX, CANSparkMaxLowLevel.MotorType.kBrushless);
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
                double error = signedModularDistance(intakeEncoder.getPosition(), Constants.Intake.INTAKE_STOP_POSITION, 1);
                intakeMotor.set(positionController.calculate(error));
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

    static double signedModularDistance(double a, double b, double modulus) {
        a = (a % modulus + a) % modulus;
        b = (b % modulus + b) % modulus;

        double posDist, negDist;
        if (b >= a) {
            posDist = b - a;
            negDist = a + (modulus - b);
        } else {
            posDist = b + (modulus - a);
            negDist = a - b;
        }

        if (posDist > negDist) {
            return posDist;
        } else {
            return -negDist;
        }
    }
}