package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class DriveConfig {
    private static final Map<String, DriveConfig> PRESETS = new HashMap<>();
    public static final String DEFAULT_PRESET_NAME = "default";

    static {
        PRESETS.put(DEFAULT_PRESET_NAME, new DriveConfig(1, 1, false));
        PRESETS.put("tank_drive", new DriveConfig(1, 1, true));
        PRESETS.put("person_2", new DriveConfig(2, 2, false));
    }

    /**
     * Provides the DriveConfig with the specified name
     * @see DriveConfig.DEFAULT_PRESET_NAME
     */
    public static DriveConfig getPreset(String name) {
        return PRESETS.get(name);
    }

    public static DriveConfig getCurrent() {
        // may be bad to get shuffleboard values constantly
        return PRESETS.getOrDefault(RobotContainer.drivePresetsChooser.getSelected(), PRESETS.get(DEFAULT_PRESET_NAME));
    }

    private final double speedSensitivity;
    private final double turnSensitivity;
    private final boolean useTankDrive;
    public DriveConfig(double speedSensitivity, double turnSensitivity, boolean useTankDrive) {
        this.speedSensitivity = speedSensitivity;
        this.turnSensitivity = turnSensitivity;
    }

    public double getSpeedSensitivity() {
        return speedSensitivity;
    }

    public double getTurnSensitivity() {
        return turnSensitivity;
    }

    public double isTankDriveEnabled() {
        return useTankDrive;
    }
}
