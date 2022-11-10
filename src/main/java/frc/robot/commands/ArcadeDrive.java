package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.DriveConfig;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

import java.util.Set;

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

    @Override
    public void execute() {
        DriveConfig config = DriveConfig.getCurrent();

        double speed = RobotContainer.joystickL.getY();
        double turn  = RobotContainer.joystickR.getX();


        drivetrain.drive(speed / config.getSpeedSensitivity(), turn / config.getTurnSensitivity(), config.isTankDriveEnabled());
    }

    public void end(boolean interrupted) {
        // this method is called when the command ends
        // we can use it to stop the DriveTrain
        drivetrain.drive(0, 0);
    }

    public boolean isFinished() {
        return false;
    }

    public Set<Subsystem> getRequirements() {
        // tell the scheduler that this command requires
        // exclusive control of the DriveTrain
        return Set.of(drivetrain);
    }
}
