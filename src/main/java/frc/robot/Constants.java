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
        // external identifiers
        public static final int INTAKE_MOTOR_PORT = 7;

        public static final int ACTUATION_MOTOR_ID = 12;
        public static final int ACTUATION_LIMIT_SWITCH_ID = 0;


        // actuation configuration
        public static final double ACTUATION_MOTOR_GEAR_RATIO = 125;
        public static final double ACTUATION_DEGREES = 70;


        // actuation speeds
        public static final double ACTUATION_MOVE_DOWN_SPEED = 0.25;
        public static final double ACTUATION_MOVE_UP_SPEED = 0.25;
        public static final double ACTUATION_HOLD_SPEED = 0.10;

    }

    public static final class DriveTrain {
        public static final int LEFT_MOTOR_PORT = 0;
        public static final int RIGHT_MOTOR_PORT = 1;

        public static final int LEFT_ENCODER_PORT_A = 2;
        public static final int LEFT_ENCODER_PORT_B = 3;

        public static final int RIGHT_ENCODER_PORT_A = 4;
        public static final int RIGHT_ENCODER_PORT_B = 5;

        public static final double TRACK_WIDTH = 0.54; // in meters

        public static final int PULES_PER_ROTATION = 1024;

        public static final double WHEEL_RADIUS = 4 * 0.0254; // in meters
        public static final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS;
    }
}
