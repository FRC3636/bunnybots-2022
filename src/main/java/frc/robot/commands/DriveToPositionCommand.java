package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

/** Might want to update encoders before using this? */
public class DriveToPositionCommand extends CommandBase {
    protected final DriveTrain drivetrain;
    protected final Camera camera;
    protected final int targetId;

    public DriveToPositionCommand(DriveTrain drivetrain, Camera camera, int targetId) {
        super();
        this.drivetrain = drivetrain;
        this.camera = camera;
        this.targetId = targetId;
    }

    @Override
    public void execute() {
        // trajectory following black magic goes here
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(drivetrain, camera);
    }
}
