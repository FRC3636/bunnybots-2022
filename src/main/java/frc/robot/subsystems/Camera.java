package frc.robot.subsystems;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
    public final PhotonCamera camera;

    /**
     * @param address - The hostname of the camera, usually {@code photonvision}.
     */
    public Camera(String address) {
        camera = new PhotonCamera(address);
        camera.setDriverMode(false);
    }

    public List<PhotonTrackedTarget> findTargets() {
        PhotonPipelineResult snapshot = camera.getLatestResult();
        return snapshot.getTargets();
    }

    private Optional<Transform3d> target = Optional.empty();

    @Override
    public void periodic() {
        PhotonPipelineResult result = camera.getLatestResult();
        if (result.hasTargets()) {
            target = Optional.of(result.getBestTarget().getBestCameraToTarget());
        } else {
            target = Optional.empty();
        }
    }

    /** Will return an empty value when the target is not visible */
    public Optional<Transform3d> getTargetRelative() {
        return target;
    }

    /** Will return an empty value when the target is not visible */
    public Optional<Transform2d> getTargetRelative2D() {
        return target.map(target -> {
            var translation = target.getTranslation().toTranslation2d();
            var rotation = target.getRotation().toRotation2d();
            return new Transform2d(translation, rotation);
        });
    }

    public static Pose2d calculateRobotPositionOnField(Pose2d targetLocationOnField,
            Transform2d targetRelativeToRobot) {
        return targetLocationOnField.transformBy(targetRelativeToRobot.inverse());
    }
}
