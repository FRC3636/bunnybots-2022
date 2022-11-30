package frc.robot.commands;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

import java.util.Set;

public class IntakeCalibration extends CommandBase {
    private final Intake intake;
    private final Direction direction;

    public IntakeCalibration(Intake intake, Direction direction) {
        this.intake = intake;
        this.direction = direction;
    }

    @Override
    public void initialize() {
        intake.setIdleMode(CANSparkMax.IdleMode.kBrake);
        intake.setTargetAngle(intake.getEncoderPositionDegrees() + (5 * direction.mult));
        intake.setPosition(Intake.Position.Match);
    }

    @Override
    public void execute() {
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(intake);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    public enum Direction {
        Up(1), Down(-1);

        public final double mult;

        Direction(double mult) {
            this.mult = mult;
        }
    }
}
