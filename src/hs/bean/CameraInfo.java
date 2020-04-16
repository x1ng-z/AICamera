package hs.bean;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/9 23:10
 */
public class CameraInfo {
    private int id;
    private String cameraUuid;
    private String cameraName;
    private String cameraComment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCameraUuid() {
        return cameraUuid;
    }

    public void setCameraUuid(String cameraUuid) {
        this.cameraUuid = cameraUuid;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraCommet() {
        return cameraComment;
    }


    public void setCameraCommet(String cameraCommet) {
        this.cameraComment = cameraCommet;
    }
}
