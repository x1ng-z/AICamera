package hs.dao.service;

import hs.bean.CameraInfo;
import hs.bean.PictureLocal;
import hs.bean.PlantInfo;
import hs.dao.CameraBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/9 14:10
 */

@Component
public class VedioPlantInfoService {


    private CameraBase cameraBase;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<PlantInfo> findPlantInfo(){
        return cameraBase.findPlantInfo();
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<CameraInfo> findCameraInfos(){
        return cameraBase.findCameraInfos();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addPicture(PictureLocal pictureLocal){
        cameraBase.addPicture(pictureLocal);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<PictureLocal> getPictures(Instant begin,Instant end){
       return  cameraBase.getPictures(begin,end);
    }

    @Autowired
    public void setCameraBase(CameraBase cameraBase) {
        this.cameraBase = cameraBase;
    }
}
