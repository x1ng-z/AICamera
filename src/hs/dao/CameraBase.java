package hs.dao;

import hs.bean.CameraInfo;
import hs.bean.PictureLocal;
import hs.bean.PlantInfo;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.util.List;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/8 15:42
 */
public interface CameraBase {
     List<PlantInfo> findPlantInfo();
     List<CameraInfo> findCameraInfos();

     void addPicture(@Param("pictureLocal") PictureLocal pictureLocal);
     List<PictureLocal> getPictures(@Param("begin") Instant begin,@Param("end") Instant end);
}
