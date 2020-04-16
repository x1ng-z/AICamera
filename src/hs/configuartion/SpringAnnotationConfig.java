package hs.configuartion;

import hs.bean.CameraInfo;
import hs.bean.PlantInfo;
import hs.dao.service.VedioPlantInfoService;
import hs.serice.HikOpenApiCamera;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/9 10:55
 */

@Configuration
@ImportResource("classpath:applicationContext.xml")
public class SpringAnnotationConfig {

    private static final Logger logger = Logger.getLogger(SpringAnnotationConfig.class);

    @Autowired
    private VedioPlantInfoService vedioPlantInfoService;
    @Autowired
    private HikOpenApiCamera hikOpenApiCamera;


    @Autowired
    private HikOpenApiCamera apiCamera;

    @Bean("plantInfos")
    public List<PlantInfo> findCameraUUID() {
        List<PlantInfo> plantInfoList = vedioPlantInfoService.findPlantInfo();
        return plantInfoList;
    }

    @Bean("CameraInfos")
    public List<CameraInfo> findCameraInfos() {
        List<CameraInfo> cameraInfoList = vedioPlantInfoService.findCameraInfos();
        for(CameraInfo cameraInfo:cameraInfoList){
            cameraInfo.setCameraUuid(hikOpenApiCamera.getCameras().get(cameraInfo.getCameraName()));
        }
        return cameraInfoList;
    }



}
