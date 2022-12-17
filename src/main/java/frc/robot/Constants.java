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
    public static final int INDEX_BUTTON = 2;

    public static final class Intake {
        public static final double MAX_DELTA = 2;

        // external identifiers
        public static final int INTAKE_MOTOR_PORT = 2;

        public static final int ACTUATION_MOTOR_ID = 1;
        public static final int ACTUATION_LIMIT_SWITCH_ID = 4;
        public static final int INDEXER_MOTOR_ID = 3;


        // actuation configuration
        public static final double ACTUATION_MOTOR_GEAR_RATIO = 125;
        public static final double ACTUATION_DEGREES = 78;

        // actuation speeds
        public static final double ACTUATION_MOVE_DOWN_SPEED = 0.15;
        public static final double ACTUATION_MOVE_UP_SPEED = 0.15;
        public static final double ACTUATION_HOLD_SPEED = 0.10;

        public static final double INTAKE_SPEED = 0.15;
    }

    public static final class Elevator {

        public static final int ELEVATOR_MOTOR_ID = 4;
        public static final double ELEVATOR_UP_SPEED = 1;
        public static final double ELEVATOR_DOWN_SPEED = 1;

    }

    public static final class DriveTrain {
        public static final int LEFT_MOTOR_PORT = 0;
        public static final int RIGHT_MOTOR_PORT = 1;
        public static final int LEFT_ENCODER_PORT_A = 0;
        public static final int LEFT_ENCODER_PORT_B = 1;

        public static final int RIGHT_ENCODER_PORT_A = 2;
        public static final int RIGHT_ENCODER_PORT_B = 3;

        public static final double TRACK_WIDTH = 0.54; // in meters

        public static final int PULSES_PER_REVOLUTION = 4096;

        public static final double WHEEL_DIAMETER = 6 * 0.0254; // in meters
        public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;

        // amy
        public static final double FEED_FORWARD_KS = -70.693;
        public static final double FEED_FORWARD_KV = 33.277;
        public static final double FEED_FORWARD_KA = 310.93;

        public static final double DRIVE_VELOCITY_KP = 1.8538;
    }
}
