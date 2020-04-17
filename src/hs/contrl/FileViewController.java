package hs.contrl;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;

/**
 * @author zzx
 * @version 1.0
 * @date 2020/3/19 15:09
 */
@Controller("fileViewController")
@RequestMapping("/status")
public class FileViewController {

    @RequestMapping("/login")
//    @ResponseBody
    public ModelAndView userlogin(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/index")
//    @ResponseBody
    public ModelAndView manager(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/home")
//    @ResponseBody
    public ModelAndView manager_home(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping("/user")
//    @ResponseBody
    public ModelAndView manager_user(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping("/userinfo")
//    @ResponseBody
    public ModelAndView manager_userinfo(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("userinfo");
        return mv;
    }

    @RequestMapping("/set")
//    @ResponseBody
    public ModelAndView manager_set(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("set");
        return mv;
    }

    @RequestMapping("/register")
//    @ResponseBody
    public ModelAndView manager_register(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @RequestMapping("/changepassword")
//    @ResponseBody
    public ModelAndView manager_changepassword(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("changepassword");
        return mv;
    }

    @RequestMapping("/message")
//    @ResponseBody
    public ModelAndView manager_message(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("message");
        return mv;
    }





    @RequestMapping("/smokestack")
//    @ResponseBody
    public ModelAndView ModelReadData(@RequestParam(value = "date",required = false) String date, @RequestParam(value = "time",required = false) String time) {
        ModelAndView  mv=new ModelAndView();
        mv.setViewName("smokestack");
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("id",1);
        jsonObject1.put("cameraComment","建德1线");
        jsonObject1.put("saveTime",Instant.now().toString());
        jsonObject1.put("pictureUrl","../img/picture1586452275.670126.png");

        jsonArray.add(jsonObject1);

        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("id",2);
        jsonObject2.put("cameraComment","兰州");
        jsonObject2.put("saveTime",Instant.now().toString());
        jsonObject2.put("pictureUrl","../img/picture1586459464.97204.png");

        jsonArray.add(jsonObject2);
        if(date!=null){
            JSONObject jsonObject3=new JSONObject();
            jsonObject3.put("id",3);
            jsonObject3.put("cameraComment","茂鑫");
            jsonObject3.put("saveTime",Instant.now().toString());
            jsonObject3.put("pictureUrl","../img/picture1586459655.8842828.png");
            jsonArray.add(jsonObject3);
        }



        mv.addObject("data",jsonArray.toJSONString());
        return mv;
    }



    @RequestMapping("/findhostory")
    @ResponseBody
    public String findhostory(@RequestParam(value = "date",required = false) String date, @RequestParam(value = "time",required = false) String time) {

        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("id",1);
        jsonObject1.put("cameraComment","建德1线");
        jsonObject1.put("saveTime",Instant.now().toString());
        jsonObject1.put("pictureUrl","../img/picture1586452275.670126.png");

        jsonArray.add(jsonObject1);

        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("id",2);
        jsonObject2.put("cameraComment","兰州");
        jsonObject2.put("saveTime",Instant.now().toString());
        jsonObject2.put("pictureUrl","../img/picture1586459464.97204.png");

        jsonArray.add(jsonObject2);
        if(date!=null){
            JSONObject jsonObject3=new JSONObject();
            jsonObject3.put("id",3);
            jsonObject3.put("cameraComment","茂鑫");
            jsonObject3.put("saveTime",Instant.now().toString());
            jsonObject3.put("pictureUrl","../img/picture1586459655.8842828.png");
            jsonArray.add(jsonObject3);
        }
        return jsonArray.toString();
    }




}
