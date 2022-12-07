package frc.robot.commands;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class IndexCommand extends CommandBase{
    final Elevator elevator;
    final double direction;


    public IndexCommand(frc.robot.subsystems.Elevator elevator, double direction){
        this.elevator = elevator;
        this.direction = direction;
    }

    @Override
    public void initialize() {
        elevator.setRunning(true, direction);
    }

    @Override
    public void end(boolean interrupted) {
        elevator.setRunning(false, 0);
    }


}
