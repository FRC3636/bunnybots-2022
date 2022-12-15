package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Elevator.Direction;


public class IndexCommand extends CommandBase {
    private final Elevator elevator;
    private final Direction direction;

    public IndexCommand(Elevator elevator, Direction direction) {
        this.elevator = elevator;
        this.direction = direction;
    }

    @Override
    public void initialize() {
        elevator.setRunning(direction);
    }

    @Override
    public void end(boolean interrupted) {
        elevator.stop();
    }
}
