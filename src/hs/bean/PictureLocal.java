package hs.bean;

import java.time.Instant;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/10 0:11
 */
public class PictureLocal {
    private int id;
    private String location;
    private Instant saveTime;
    private int cameraId;
    private long batchNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Instant saveTime) {
        this.saveTime = saveTime;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(long batchNo) {
        this.batchNo = batchNo;
    }
}
