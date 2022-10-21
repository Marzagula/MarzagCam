package com.marzag.cameraserver.repository;

import com.marzag.cameraserver.model.Camera;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CameraRepository extends Repository<Camera,Long> {
    Camera save(Camera camera);
    List<Camera> getCameraByOwnerId(String ownerId);
    Camera getCameraById(String ownerId);
    Camera getCameraByOwnerIdAndCameraId(String ownerId, String CameraId);
}
