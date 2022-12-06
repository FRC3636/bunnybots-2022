package frc.robot.commands;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class IndexCommand extends CommandBase{
    final Elevator elevator;


    public IndexCommand(frc.robot.subsystems.Elevator elevator){
        this.elevator = elevator;
    }

    @Override
    public void initialize() {
        elevator.setRunning(true);
    }

    @Override
    public void end(boolean interrupted) {
        elevator.setRunning(false);
    }


}
