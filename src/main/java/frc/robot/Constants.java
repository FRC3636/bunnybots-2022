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
    public static final int PS4CONTROLLER_PORT = 2;

    public static final class Intake {
        public static final double INTAKE_SPEED = 0.6;
        public static final double INTAKE_STOP_POSITION = 0; // intake-signed rotations between the reset position and the stop position

        public static final double INTAKE_CONTROLLER_P = 0;
        public static final double INTAKE_CONTROLLER_I = 0;
        public static final double INTAKE_CONTROLLER_D = 0;

        // external identifiers
        public static final int INTAKE_MOTOR_INDEX = 5;
        public static final int INTAKE_RESET_SWITCH_PORT = 2;
    }

    public static final class DriveTrain {
        public static final int RIGHT_MOTOR_INDEX_MAIN = 1;
        public static final int RIGHT_MOTOR_INDEX_FOLLOWER = 2;
        public static final int LEFT_MOTOR_INDEX_MAIN = 3;
        public static final int LEFT_MOTOR_INDEX_FOLLOWER = 4;

        public static final double TRACK_WIDTH = 0.54; // in meters

        public static final int PULES_PER_ROTATION = 1024;

        public static final double WHEEL_RADIUS = 4 * 0.0254; // in meters
        public static final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS;
    }

    public static final class Elevator {
        public static final int ELEVATOR_MOTOR_PORT = 0;
        public static final int ELEVATOR_DOOR_MOTOR_INDEX = 6;
        public static final int DOOR_MOTOR_PDP_CHANNEL = 3;
        public static final double MAX_DOOR_DRAW = 3;
        public static final int BOTTOM_LIMIT_SWITCH_CHANNEL = 6;
        public static final int TOP_LIMIT_SWITCH_CHANNEL = 7;

    }
}
