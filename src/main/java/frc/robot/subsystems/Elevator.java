package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Elevator extends SubsystemBase {

    private final VictorSP elevatorMotor = new VictorSP(Constants.Elevator.ELEVATOR_MOTOR_ID);

    public void setRunning(Direction direction) {
        switch (direction) {
            case Up:
                elevatorMotor.set(-1 * Constants.Elevator.ELEVATOR_UP_SPEED);
                break;
            case Down:
                elevatorMotor.set(Constants.Elevator.ELEVATOR_DOWN_SPEED);
                break;
        }
    }

    public void stop() {
        elevatorMotor.set(0);
    }

    public enum Direction {
        Up,
        Down,
    }
}
