package hs.pythonAlgorithm;

import hs.bean.CameraInfo;
import hs.bean.PictureLocal;
import hs.dao.service.VedioPlantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/9 23:01
 */
@Component
public class CycleExeuteSerice {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CycleExeuteSerice.class);

    private ExecutePythonBridge executePythonBridge=new ExecutePythonBridge();

    @Autowired
    @Qualifier("CameraInfos")
    private List<CameraInfo> cameraInfoList;
    private Timer timer=null;

    @Value("${pythonjs_local}")
    private String pythonjs_local;

    @Autowired
    private VedioPlantInfoService vedioPlantInfoService;


    public void selfinit(){

        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long batchNo=Instant.now().getEpochSecond();
                for(CameraInfo cameraInfo:cameraInfoList){
                    PictureLocal pictureLocal =new PictureLocal();
                    pictureLocal.setBatchNo(batchNo);
                    pictureLocal.setCameraId(cameraInfo.getId());
                    executePythonBridge.setPictureLocal(pictureLocal);
                    executePythonBridge.setPythonjs(pythonjs_local);
                    executePythonBridge.setUrl("rtsp://10.10.10.3:554/pag://10.10.10.3:7302:"+cameraInfo.getCameraUuid()+":0:SUB:TCP?streamform=rtp");
                    executePythonBridge.setCameraId(cameraInfo.getId());
                    executePythonBridge.setCameraNamComment(cameraInfo.getCameraCommet());
                    logger.info(cameraInfo.getCameraCommet()+"try to get picture");
                    executePythonBridge.execute();
                    while (!executePythonBridge.isExecuteComple()){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            logger.error(e);
                        }
                    }
                    executePythonBridge.stop();
                    logger.info(pictureLocal.getLocation()+"get picture location");
                    if(pictureLocal.getLocation()!=null){
                        pictureLocal.setSaveTime(Instant.now());
                        vedioPlantInfoService.addPicture(pictureLocal);
                    }
                    executePythonBridge.setExecuteComple(false);
                }



            }
        },1000*1,1000*60*60);

    }

    public void selfclose(){
        if (timer!=null){
            timer.cancel();
        }

    }



}
