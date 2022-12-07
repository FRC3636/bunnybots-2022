package frc.robot.commands;
import frc.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator.Direction;


public class IndexCommand extends CommandBase{
    final Elevator elevator;
    final boolean moveUp;
    final Direction direction;
    


    public IndexCommand(frc.robot.subsystems.Elevator elevator, boolean moveUp){
        this.elevator = elevator;
        this.moveUp = moveUp;
        direction = (moveUp) ? Direction.Up : Direction.Down;
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
