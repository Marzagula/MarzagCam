package com.marzag.cameraserver.repository;

import com.marzag.cameraserver.model.ReleaseHistory;
import org.springframework.data.repository.Repository;

public interface ReleaseHistoryRepository extends Repository<ReleaseHistory, Long> {

    public void save(ReleaseHistory releaseHistory);

    public ReleaseHistory findTopByOrderByIdDesc();

    public ReleaseHistory findReleaseHistoryByReleaseName(String releaseName);

}
