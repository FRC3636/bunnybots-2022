package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

public class IntakeCommand implements Command {
    private final Intake intake;
    private final Intake.State state;

    public IntakeCommand(Intake intake, Intake.State state) {
        this.intake = intake;
        this.state = state;
    }

    @Override
    public void execute() {
        intake.setState(state);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(intake);
    }
}
