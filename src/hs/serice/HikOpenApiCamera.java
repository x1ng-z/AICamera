package hs.serice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hikvision.cms.api.common.util.Digests;
import com.hikvision.cms.api.common.util.HttpClientSSLUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * openapi 测试DEMO
 * 客户开发的时候可以参考此代码编写自己的应用
 * 也可以使用demo中的jar包，不过jar包使用务必使用全套，
 * 万不可只取其中一部分，避免依赖破坏
 *
 * @author shengyiling
 */

@Component
public class HikOpenApiCamera {
    private   Logger logger = Logger.getLogger(HikOpenApiCamera.class);

    /**
     * APPKEY需要到部署的平台服务器上生成。
     * <p>
     * 调用Openpai的操作码，需要去平台上生成，详见《海康威视iVMS-8700平台SDKV2.*.* HTTP-OpenAPI使用说明书.chm》中[获取AppKey和Secret]章节说明
     * </p>
     * <p>
     * 《海康威视iVMS-8700平台SDKV2.*.* HTTP-OpenAPI使用说明书.chm》 该文档请找技术支持或者交付的同事提供
     * </p>
     */
    @Value("${APPKEY}")
    private String APPKEY;

    /**
     * SECRET需要到部署的平台服务器上生成。
     * <p>
     * 调用Openpai的操作码，需要去平台上生成，详见《海康威视iVMS-8700平台SDKV2.*.* HTTP-OpenAPI使用说明书.chm》中[获取AppKey和Secret]章节说明
     * </p>
     * <p>
     * 《海康威视iVMS-8700平台SDKV2.*.* HTTP-OpenAPI使用说明书.chm》 该文档请找技术支持或者交付的同事提供
     * </p>
     */
    @Value("${SECRET}")
    private String SECRET;


    /**
     * http请求地址
     * <p>openapi的地址,默认情况下openapi的IP端口与基础应用的IP端口是一致的</p>
     * <p>请将地址配置正确.</p>
     * <p>默认情况下是127.0.0.1:80 ，如果地址不通请根据实际情况修改IP端口</p>
     */
    @Value("${OPENAPI_IP_PORT_HTTP}")
    private String OPENAPI_IP_PORT_HTTP;

    /**
     * https请求地址
     * <p>openapi的地址,默认情况下openapi的IP端口与基础应用的IP端口是一致的</p>
     * <p>请将地址配置正确.</p>
     * <p>默认情况下是127.0.0.1:443 ，如果地址不通请根据实际情况修改IP端口</p>
     */
    private static final String OPENAPI_IP_PORT_HTTPS = "https://127.0.0.1:443";

    /**
     * 获取默认用户UUID的接口地址，此地址可以从《海康威视iVMS-8700平台SDKV2.*.* HTTP-OpenAPI使用说明书.chm》中具体的接口说明上获取
     */
    private static final String ITF_ADDRESS_GET_DEFAULT_USER_UUID = "/openapi/service/base/user/getDefaultUserUuid";

    /**
     * 分页获取监控点信息的接口地址，此地址可以从《海康威视iVMS-8700平台SDKV2.*.* HTTP-OpenAPI使用说明书.chm》中具体的接口说明上获取
     */
    private static final String ITF_ADDRESS_GET_CAMERAS = "/openapi/service/vss/res/getCameras";

    /**
     * <p>操作用户UUID，即用户UUID，首次使用操作用户UUID可以通过接口 [获取默认用户UUID]来获取</p>
     * <p>也可以通过接口[分页获取用户]来获取</p>
     */

    private String OP_USER_UUID = null;//"0be83d40695011e7981e0f190ed6d2e7";

    private String default_user_uuid = null;// "cc78be40ec8611e78168af26905e6f0f";

    /**
     * key=ip,value=netZoneid
     */
    private Map<String, String> netZoneid = null;//"1d31892a5e4a4c75bdbcf94613fc9747";// e7483b66e973471babcb1779824ca5b7
    private Map<String,String> cameras;

    public void selfinit() {
        try {
            JSONObject resp_useruuid = JSON.parseObject(GetDefaultUserUUID());
            default_user_uuid = resp_useruuid.getString("data");
            netZoneid=getNetZone();
            cameras=GetCameras();
        } catch (Exception e) {
            logger.error(e);
        }

    }




    /**
     * HTTP方式
     * 获取默认用户UUID 测试
     *
     * @return
     * @throws Exception
     */
    private String GetDefaultUserUUID() throws Exception {
        String url = OPENAPI_IP_PORT_HTTP + ITF_ADDRESS_GET_DEFAULT_USER_UUID;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appkey", APPKEY);//设置APPKEY
        map.put("time", System.currentTimeMillis());//设置时间参数
        String params = JSON.toJSONString(map);
//        logger.info(" ====== testGetDefaultUserUUID 请求参数：【" + params + "】");
//        logger.info("url: " + url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET));
        String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET), params);
//        logger.info(" ====== testGetDefaultUserUUID 请求返回结果：【{" + data + "}】");

        return data;
    }


    private Map<String, String> getNetZone() {
        Map<String,String> netZone=new HashMap<>();
        String url = OPENAPI_IP_PORT_HTTP + "/openapi/service/base/netZone/getNetZones";


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appkey", APPKEY);//设置APPKEY
        map.put("time", System.currentTimeMillis());//设置时间参数
        map.put("opUserUuid", default_user_uuid);

        String params = JSON.toJSONString(map);


        try {
            String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET), params);
            JSONObject json = JSON.parseObject(data);
            JSONArray jsonArray =json.getJSONArray("data");
            for(int i=0;i<jsonArray.size();i++){
                JSONObject nodeZoneJson=jsonArray.getJSONObject(i);
                netZone.put(nodeZoneJson.getString("netZoneName"),nodeZoneJson.getString("netZoneUuid"));
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return netZone;
    }


    private  String testGetpreview(String OPENAPI_IP_PORT_HTTP, String APPKEY, String SECRET, String default_user_uuid, String netZoneid) {

        String url = OPENAPI_IP_PORT_HTTP + "/openapi/service/vss/preview/getPreviewParamByCameraUuid";


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appkey", APPKEY);//设置APPKEY
        map.put("time", System.currentTimeMillis());//设置时间参数
        map.put("opUserUuid", default_user_uuid);
        map.put("cameraUuid", "68ff6df7815444ffa638e3cec6bf7ef0");
        map.put("netZoneUuid", netZoneid);


        String params = JSON.toJSONString(map);
        logger.info("testGetpreview url: " + url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET) + params);


        try {
            String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET), params);
            logger.info(data);
        } catch (Exception e) {
            logger.error(e);
        }


        return null;


    }

    /**
     * HTTP方式
     * 分页获取监控点信息 测试
     *
     * @return
     * @throws Exception
     */
    private Map<String,String> GetCameras( ) throws Exception {
        Map<String,String> cameras=new HashMap<>();
        String url = OPENAPI_IP_PORT_HTTP + ITF_ADDRESS_GET_CAMERAS;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appkey", APPKEY);//设置APPKEY
        map.put("time", System.currentTimeMillis());//设置时间参数
        map.put("pageNo", 1);//设置分页参数
        map.put("pageSize", 1000);//设置分页参数
        map.put("opUserUuid", default_user_uuid);//设置操作用户UUID
        String params = JSON.toJSONString(map);
//        logger.info(" testGetCameras====== getCameras请求参数：【" + params + "】");
        String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET), params);
//        logger.info("testGetCameras ====== getCameras请求返回结果：【{" + data + "}】");
        JSONObject jsonObject=JSON.parseObject(data);
        JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("list");
        for(int i=0;i<jsonArray.size();++i){
            JSONObject nodecamera=jsonArray.getJSONObject(i);
            cameras.put(nodecamera.getString("cameraName"),nodecamera.getString("cameraUuid"));
        }
        return cameras;
    }

    /**
     * HTTPS方式
     * 获取默认用户UUID 测试
     *
     * @return
     * @throws Exception
     */
    private  String testGetDefaultUserUUID_Https(String APPKEY, String SECRET) throws Exception {
        String url = OPENAPI_IP_PORT_HTTPS + ITF_ADDRESS_GET_DEFAULT_USER_UUID;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appkey", APPKEY);//设置APPKEY
        map.put("time", System.currentTimeMillis());//设置时间参数
        String params = JSON.toJSONString(map);
        logger.info("============" + params + "============");
        String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET), params);
        logger.info("============" + data + "============");

        return data;
    }

    /**
     * HTTPS方式
     * 分页获取监控点信息 测试
     *
     * @return
     * @throws Exception
     */
    private  String testGetCameras_Https(String APPKEY, String SECRET, String OP_USER_UUID) throws Exception {
        String url = OPENAPI_IP_PORT_HTTPS + ITF_ADDRESS_GET_CAMERAS;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appkey", APPKEY);//设置APPKEY
        map.put("time", System.currentTimeMillis());//设置时间参数
        map.put("pageNo", 1);//设置分页参数
        map.put("pageSize", 5);//设置分页参数
        map.put("opUserUuid", OP_USER_UUID);//设置操作用户UUID
        String params = JSON.toJSONString(map);
        logger.info(" ====== getCameras请求参数：【" + params + "】");
        String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, SECRET), params);
        logger.info(" ====== getCameras请求返回结果：【{" + data + "}】");

        return data;
    }


    public String getAPPKEY() {
        return APPKEY;
    }

    public void setAPPKEY(String APPKEY) {
        this.APPKEY = APPKEY;
    }

    public String getSECRET() {
        return SECRET;
    }

    public void setSECRET(String SECRET) {
        this.SECRET = SECRET;
    }

    public String getOPENAPI_IP_PORT_HTTP() {
        return OPENAPI_IP_PORT_HTTP;
    }

    public void setOPENAPI_IP_PORT_HTTP(String OPENAPI_IP_PORT_HTTP) {
        this.OPENAPI_IP_PORT_HTTP = OPENAPI_IP_PORT_HTTP;
    }


    /**
     * 测试方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

//
//        String filepath = System.getProperty("user.dir") + "/conf/vedio_configuare.properties";
//        HikOpenApiCamera hikOpenApiCamera = new HikOpenApiCamera();
//        try {
//            FileInputStream fileInputStream = new FileInputStream(new File(filepath));
//            Properties prop = new Properties();
//            prop.load(fileInputStream);
//
//            hikOpenApiCamera.APPKEY = prop.getProperty("APPKEY");
//
//            hikOpenApiCamera.SECRET = prop.getProperty("SECRET");
//            hikOpenApiCamera.OPENAPI_IP_PORT_HTTP = prop.getProperty("OPENAPI_IP_PORT_HTTP");
//            hikOpenApiCamera.OP_USER_UUID = prop.getProperty("OP_USER_UUID");
//            hikOpenApiCamera.default_user_uuid = prop.getProperty("default_user_uuid");
//            hikOpenApiCamera.netZoneid = prop.getProperty("netZoneid");
//        } catch (IOException e) {
//            logger.error(e);
//        }
//
//
//        getNetZone(hikOpenApiCamera.OPENAPI_IP_PORT_HTTP, hikOpenApiCamera.APPKEY, hikOpenApiCamera.SECRET, hikOpenApiCamera.default_user_uuid);
//        System.out.println("\n");
//
////		testGetpreview();
//        System.out.println("\n");
//
//        /***http方式调用***/
//        logger.info(testGetDefaultUserUUID(hikOpenApiCamera.OPENAPI_IP_PORT_HTTP, hikOpenApiCamera.APPKEY, hikOpenApiCamera.SECRET));
//        logger.info(testGetCameras(hikOpenApiCamera.OPENAPI_IP_PORT_HTTP, hikOpenApiCamera.APPKEY, hikOpenApiCamera.SECRET, hikOpenApiCamera.default_user_uuid));

//		/***https方式调用***/
//		System.out.println(testGetDefaultUserUUID_Https());
//		System.out.println(testGetCameras_Https());
    }

    public String getDefault_user_uuid() {
        return default_user_uuid;
    }

    public void setDefault_user_uuid(String default_user_uuid) {
        this.default_user_uuid = default_user_uuid;
    }

    public Map<String, String> getNetZoneid() {
        return netZoneid;
    }

    public void setNetZoneid(Map<String, String> netZoneid) {
        this.netZoneid = netZoneid;
    }

    public Map<String, String> getCameras() {
        return cameras;
    }

    public void setCameras(Map<String, String> cameras) {
        this.cameras = cameras;
    }
}
