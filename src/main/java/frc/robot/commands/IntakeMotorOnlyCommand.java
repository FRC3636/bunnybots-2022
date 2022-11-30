package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

import java.util.Set;

public class IntakeMotorOnlyCommand extends CommandBase {
    private final Intake intake;
    private final Direction direction;

    public IntakeMotorOnlyCommand(Intake intake, Direction direction) {
        this.intake = intake;
        this.direction = direction;
    }

    @Override
    public void initialize() {
        intake.setWheelSpeed(direction.speed);
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        intake.stopWheels();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(intake);
    }

    public enum Direction {
        In(-1),
        Out(1);

        public final double speed;

        Direction(double speed) {
            this.speed = speed;
        }
    }
}
