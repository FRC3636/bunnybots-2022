package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Intake implements Subsystem {
    private final DigitalInput readSwitch = new DigitalInput(Constants.Intake.INTAKE_READ_SWITCH_PORT);

    private final CANSparkMax intakeMotor = new CANSparkMax(Constants.Intake.INTAKE_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private State currentState = State.Stop;
    @Override
    public void periodic() {
        switch (currentState) {
            case Intake:
                intakeMotor.set(Constants.Intake.INTAKE_SPEED);
                break;
            case Outtake:
                intakeMotor.set(-Constants.Intake.INTAKE_SPEED);
                break;
            case Stop:
                intakeMotor.set(0); // FIXME Return to vertical
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