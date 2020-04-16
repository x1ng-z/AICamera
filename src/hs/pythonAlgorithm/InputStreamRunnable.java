package hs.pythonAlgorithm;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import hs.bean.PictureLocal;
import hs.dao.service.VedioPlantInfoService;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.concurrent.LinkedBlockingQueue;

public class InputStreamRunnable implements Runnable {
    private static Logger log = Logger.getLogger(InputStreamRunnable.class);

    private BufferedReader bReader = null;
    private String _type;
    private LinkedBlockingQueue linkedBlockingQueue = null;
    private boolean goon = true;
    private ExecutePythonBridge executePythonBridge;
    private PictureLocal pictureLocal;

    public InputStreamRunnable(ExecutePythonBridge executePythonBridge, PictureLocal pictureLocal, InputStream is, String _type, LinkedBlockingQueue linkedBlockingQueue) {
        this.executePythonBridge = executePythonBridge;
        this.linkedBlockingQueue = linkedBlockingQueue;

        this.pictureLocal = pictureLocal;

        try {
            this._type = _type;
            bReader = new BufferedReader(new InputStreamReader((is), "gb2312"));
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    public void run() {
        String line;

        while (goon) {

            try {
                if ((line = bReader.readLine()) != null) {
                    if (_type.equals("ErrorStream")) {
                        log.info(line);
                    }
                    if (_type.equals("Result")) {
                        if (linkedBlockingQueue != null) {
                            log.info(line);
                            linkedBlockingQueue.put(line);
                        } else {
                            log.info(line);
                            JSONObject result = null;
                            try {
                                result = JSON.parseObject(line);
                            } catch (JSONException e) {
                                log.error(e);
                            }
                            if (result != null) {
                                log.debug(result.toJSONString());
                                if (result.getString("result").equals("success")) {
                                    executePythonBridge.setExecuteComple(true);

                                    pictureLocal.setLocation(result.getJSONObject("data").getString("fileName"));

                                } else {
                                    executePythonBridge.setExecuteComple(true);
                                    log.warn("can't get picture");
                                }
                            }


                        }
                    }

                }
            } catch (IOException e) {
                log.error(e);
                executePythonBridge.setExecuteComple(true);
            } catch (InterruptedException e) {
                log.error(e);
                executePythonBridge.setExecuteComple(true);
            }

        }
        log.info("end " + _type);
        try {
            bReader.close();
        } catch (IOException e) {
            log.error(e);
        }

    }

    public void setGoon(boolean goon) {
        this.goon = goon;
    }
}
