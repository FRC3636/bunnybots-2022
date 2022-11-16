// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int JOYSTICK_LEFT_PORT = 0;
    public static final int JOYSTICK_RIGHT_PORT = 1;

    public static final class Intake {

        public static final int ACTUATION_MOTOR = 12;
        public static final int ACTUATION_LIMIT_SWITCH_BOTTOM = 0;

        public static final double ACTUATION_MOTOR_GEAR_RATIO = 125;
        public static final double ACTUATION_DEGREES = 70;

        public static final double ACTUATION_MOVE_DOWN_SPEED = 0.25;
        public static final double ACTUATION_MOVE_UP_SPEED = 0.25;
        public static final double ACTUATION_HOLD_SPEED = 0.10;

    }

    public static final class DriveTrain {
        public static final int LEFT_MOTOR_PORT = 1;
        public static final int RIGHT_MOTOR_PORT = 2;
    }
}
