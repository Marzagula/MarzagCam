package com.marzag.raspberrymanager.dto;

import java.util.Date;

public class ReleaseHistory {

    Long id;
    String releaseName;
    byte[] releaseFile;
    Date timestamp;


    public ReleaseHistory() {
    }

    public ReleaseHistory(String releaseName, byte[] releaseFile) {
        this.releaseName = releaseName;
        this.releaseFile = releaseFile;
    }

    public ReleaseHistory(Long id, String releaseName, byte[] releaseFile) {
        this.id = id;
        this.releaseName = releaseName;
        this.releaseFile = releaseFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public byte[] getReleaseFile() {
        return releaseFile;
    }

    public void setReleaseFile(byte[] releaseFile) {
        this.releaseFile = releaseFile;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
