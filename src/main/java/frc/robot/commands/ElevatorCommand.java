package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Elevator;

public class ElevatorCommand implements Command{
    private final Elevator elevator;
    private final double percentOutput;

    public ElevatorCommand(Elevator elevator){
        this.elevator = elevator;
    }

    @Override
    public void execute() {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }


    public Set<Subsystem> getRequirements() {
        return Set.of(elevator);
    }
    
    
}
