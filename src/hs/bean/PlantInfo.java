package hs.bean;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/9 14:14
 */
public class PlantInfo {
    private int id;
    private String name;
    private String streamIp;
    private int plantType;
    private String netZoneUuid;
    private String defaultUserUuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreamIp() {
        return streamIp;
    }

    public void setStreamIp(String streamIp) {
        this.streamIp = streamIp;
    }

    public int getPlantType() {
        return plantType;
    }

    public void setPlantType(int plantType) {
        this.plantType = plantType;
    }
}
