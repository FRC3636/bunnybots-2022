package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

import java.util.Set;

public class IntakeCommand extends CommandBase {
    private final Intake intake;

    public IntakeCommand(Intake intake) {
        this.intake = intake;
    }

    @Override
    public void initialize() {
        intake.goDown();
    }

    @Override
    public void execute() {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(intake);
    }
}
