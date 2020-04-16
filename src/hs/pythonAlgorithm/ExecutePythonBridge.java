package hs.pythonAlgorithm;

import hs.bean.PictureLocal;
import hs.dao.service.VedioPlantInfoService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;

public class ExecutePythonBridge {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExecutePythonBridge.class);
    public  Process p=null;
    public String pythonhome="python.exe";
    private String pythonjs="E:\\LinkAPC.py";
    private String url;//new String[]{pythonhome,pythonjs,"http://localhost:8080/python/modlebuild/"+key+".do"};
    private String cameraNamComment;
    private int cameraId;
    InputStreamRunnable  result=null;
    InputStreamRunnable  error=null;
    private boolean isExecuteComple=false;
   private PictureLocal pictureLocal;

    public ExecutePythonBridge( String pythonjs, String url,String compayName) {
        this.pythonjs = pythonjs;
        this.url = url;
        this.cameraNamComment =compayName;
    }
    public ExecutePythonBridge(){}

    public  void stop(){
        if(p!=null){
            p.destroy();
            result.setGoon(false);
            error.setGoon(false);
        }
        p=null;
    }

    public  void execute() {
        if(p!=null){
            return;
        }
        //LinkedBlockingQueue<String> linkedBlockingQueue=new LinkedBlockingQueue();
        BufferedReader bReader = null;
        InputStreamReader sReader = null;

        try {
            p = Runtime.getRuntime().exec(new String[]{pythonhome,pythonjs, url, cameraNamComment,cameraId+""});
            result=new InputStreamRunnable(this,pictureLocal,p.getInputStream(),"Result",null);
            Thread resultThread= new Thread(result);
            resultThread.setDaemon(true);
            resultThread.start();

            /* 为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞 */
             error=new InputStreamRunnable(this,pictureLocal,p.getErrorStream(), "ErrorStream",null);
            Thread errorThread = new Thread(error);
            errorThread.setDaemon(true);
            errorThread.start();

        } catch (Exception e) {
           logger.error(e);
        }
    }

    public String getPythonjs() {
        return pythonjs;
    }

    public void setPythonjs(String pythonjs) {
        this.pythonjs = pythonjs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCameraNamComment() {
        return cameraNamComment;
    }

    public void setCameraNamComment(String cameraNamComment) {
        this.cameraNamComment = cameraNamComment;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public boolean isExecuteComple() {
        return isExecuteComple;
    }

    public void setExecuteComple(boolean executeComple) {
        isExecuteComple = executeComple;
    }


    public PictureLocal getPictureLocal() {
        return pictureLocal;
    }

    public void setPictureLocal(PictureLocal pictureLocal) {
        this.pictureLocal = pictureLocal;
    }
}


