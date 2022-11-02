package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive implements Command {
    private final DriveTrain drivetrain;

    public ArcadeDrive(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void initialize() {
        // // this method is called once when the command is scheduled
        // // we can use it to reset the DriveTrain's encoders
        // drivetrain.resetEncoders();
    }

    public void execute() {
        // this method is called periodically while the command is running
        // we can use it to accelerate the DriveTrain forward
        drivetrain.drive(0.5, 0.5);
    }

    public void end(boolean interrupted) {
        // this method is called when the command ends
        // we can use it to stop the DriveTrain
        drivetrain.drive(0, 0);
    }

    public boolean isFinished() {
        // if the DriveTrain has traveled more than 1 meter,
        // the command is finished
        return false;
    }

    public Set<Subsystem> getRequirements() {
        // tell the scheduler that this command requires
        // exclusive control of the DriveTrain
        return Set.of(drivetrain);
    }
}
