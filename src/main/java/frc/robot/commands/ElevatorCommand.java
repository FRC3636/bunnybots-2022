package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;

public class ElevatorCommand implements Command{
    private final Elevator elevator;

    public ElevatorCommand(Elevator elevator){
        this.elevator = elevator;
    }

    @Override
    public void execute() {
        elevator.moveElevator(RobotContainer.controller.getRightY());
    }

    @Override
    public boolean isFinished() {
        return false;
    }


    public Set<Subsystem> getRequirements() {
        return Set.of(elevator);
    }
    
    
}
