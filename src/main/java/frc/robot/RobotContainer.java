// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.DriveConfig.DriveScheme;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.ElevatorCommand;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DriveTrain driveTrain = new DriveTrain();
    private final Elevator elevator = new Elevator();
    public static final Joystick joystickL = new Joystick(Constants.JOYSTICK_LEFT_PORT);
    public static final Joystick joystickR = new Joystick(Constants.JOYSTICK_RIGHT_PORT);
    public static final SendableChooser<String> drivePresetsChooser = new SendableChooser<String>();
    private static final ShuffleboardTab driveSettings = Shuffleboard.getTab("Drive Settings");
    public static final ShuffleboardTab autoTab = Shuffleboard.getTab("Auto");
    private static Optional<NetworkTableEntry> driveSchemeEntry = Optional.empty();
    public static Field2d field = new Field2d();
    public static PS4Controller controller = new PS4Controller(Constants.PS4CONTROLLER_PORT);

    static {
        drivePresetsChooser.addOption("Default", DriveConfig.DEFAULT_PRESET_NAME);
        drivePresetsChooser.addOption("Person 2", "person_2");
        drivePresetsChooser.addOption("Tank Drive", "tank_drive");
        drivePresetsChooser.addOption("Arcade Single", "arcade_single");
    }

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        driveSettings.add("Drive Presets", drivePresetsChooser)
                .withWidget(BuiltInWidgets.kComboBoxChooser);
        driveSchemeEntry = Optional.of(driveSettings
                .add("Drive Scheme", "None")
                .withWidget(BuiltInWidgets.kTextView)
                .getEntry());

        autoTab.add("Field", field).withWidget(BuiltInWidgets.kField);
    }

    public static void updateDriveSchemeWidget(DriveScheme driveScheme) {
        if (!driveSchemeEntry.isPresent())
            return;
        driveSchemeEntry.get().setString(driveScheme.toString());
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        driveTrain.setDefaultCommand(new DriveCommand(driveTrain));
        elevator.setDefaultCommand(new ElevatorCommand(elevator));
        
        
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
