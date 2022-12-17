package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.DriveTrain;

import java.util.Set;

public class DriveForwardDistance implements Command {

    private final DriveTrain driveTrain;
    private double initialDistance;
    private final double distanceMeters;

    public DriveForwardDistance (DriveTrain driveTrain, double distanceMeters) {
        this.driveTrain = driveTrain;
        this.distanceMeters = distanceMeters;
    }

    @Override
    public void initialize() {
        initialDistance = driveTrain.getDistance();
        driveTrain.arcadeDrive(0.5, 0);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return driveTrain.getDistance() > initialDistance + distanceMeters;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(driveTrain);
    }
}
