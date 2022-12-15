package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Intake implements Subsystem {
    private static DigitalInput reedSwitch = new DigitalInput(Constants.Intake.INTAKE_REED_SWITCH_PORT);
}