/* (C)2022 Max Niederman, Silas Gagnon, and contributors */
package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import java.util.ArrayList;

public class FollowTrajectoryCommand extends RamseteCommand {
    private final DriveTrain driveTrain;
    private final ArrayList<PositionedCommand<Command>> positionedCommands;
    private final Trajectory trajectory;

    private boolean resetOdometry = true;

    public FollowTrajectoryCommand(
            DriveTrain driveTrain,
            Trajectory trajectory,
            ArrayList<PositionedCommand<Command>> positionedCommands) {
        super(
                trajectory,
                driveTrain::getPose,
                new RamseteController(),
                new SimpleMotorFeedforward(
                        Constants.DriveTrain.FEED_FORWARD_KS,
                        Constants.DriveTrain.FEED_FORWARD_KV,
                        Constants.DriveTrain.FEED_FORWARD_KA
                ),
                new DifferentialDriveKinematics(Constants.DriveTrain.TRACK_WIDTH),
                driveTrain::getWheelSpeeds,
                new PIDController(Constants.DriveTrain.DRIVE_VELOCITY_KP, 0, 0),
                new PIDController(Constants.DriveTrain.DRIVE_VELOCITY_KP, 0, 0),
                driveTrain::tankDriveWithRawVoltage,
                driveTrain
        );

        this.trajectory = trajectory;

        this.driveTrain = driveTrain;
        this.positionedCommands = positionedCommands;
    }

    public FollowTrajectoryCommand(DriveTrain driveTrain, Trajectory trajectory, boolean resetOdometry) {
        this(driveTrain, trajectory, new ArrayList<>(0));
        this.resetOdometry = resetOdometry;
    }

    public void addPositionedCommand(PositionedCommand<Command> positionedCommand) {
        positionedCommands.add(positionedCommand);
    }

    @Override
    public void initialize() {
        if (resetOdometry) {
            driveTrain.resetOdometryTo(trajectory.getInitialPose());
        }
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();

        Pose2d pose = driveTrain.getPose();

        for (PositionedCommand<Command> command : positionedCommands) {
            command.executeAt(pose);
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
        super.end(interrupted);
    }

    public class PositionedCommand<C extends Command> {
        private final C command;
        private final Pose2d start;
        private final Pose2d end;
        private final double tolerance;
        private boolean running = false;

        public PositionedCommand(C command, Pose2d start, Pose2d end, double tolerance) {
            this.command = command;
            this.start = start;
            this.end = end;
            this.tolerance = tolerance;
        }

        public PositionedCommand(C command, Pose2d start, Pose2d end) {
            this(command, start, end, 0.0);
        }

        public void executeAt(Pose2d pose) {
            if (transformLength(start.minus(pose)) < tolerance) {
                running = true;
                command.initialize();
            }

            if (transformLength(end.minus(pose)) < tolerance) {
                running = false;
                command.end(true);
            }

            if (running) {
                command.execute();
            }
        }

        double transformLength(Transform2d transform) {
            return Math.sqrt(
                    Math.pow(transform.getTranslation().getX(), 2)
                            + Math.pow(transform.getTranslation().getY(), 2));
        }
    }
}
