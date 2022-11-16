package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

import java.util.Set;

public class IntakeCalibration extends CommandBase {
    private final Intake intake;

    public IntakeCalibration(Intake intake) {
        this.intake = intake;
    }

    @Override
    public void initialize() {
        intake.setTargetAngle(0);
        intake.goDown();
    }

    @Override
    public void execute() {
        intake.setTargetAngle(intake.getTargetAngle() + 5f);
        intake.goUp();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(intake);
    }
}
