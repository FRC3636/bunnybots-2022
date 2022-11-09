package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class DrivePresetSwitch implements Command {
    public void initialize() {
        // // this method is called once when the command is scheduled
        // // we can use it to reset the DriveTrain's encoders
        // drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        // Use two buttons to set current DriveConfig to the one next/previous in the list of presets
    }

    public boolean isFinished() {
        return false;
    }

    public Set<Subsystem> getRequirements() {
        // tell the scheduler that this command requires
        // exclusive control of the DriveTrain
        return Set.of();
    }
}
