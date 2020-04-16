package hs.configuartion;

import com.alibaba.fastjson.JSONObject;
import hs.serice.HikOpenApiCamera;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/4/9 10:58
 */
public class test {



    public test(){

    }


    public static void main(String[] args)throws Exception{


        String strReqDelTime = "2020-04-10 17:59:53";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strReqDelTime);
        Instant reqInstant = date.toInstant();
        Long second=reqInstant.getEpochSecond();
        System.out.println(second);

//
//        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(SpringAnnotationConfig.class);
//        HikOpenApiCamera s=ctx.getBean(HikOpenApiCamera.class);
//        s.getAPPKEY();
    }
}
