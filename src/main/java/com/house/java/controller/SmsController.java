package com.house.java.controller;

import com.house.sms.SMSUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class SmsController {

    @RequestMapping("getCode")
    @ResponseBody
    public String getCode(String telephone, HttpSession session){
        int code=(int)((Math.random()*9+1)*100000);//随机生成1个6位数的验证码
        String sendMsg="验证码是:"+code+",请在120秒内输入验证码";
        int result = SMSUtil.sendMSG(telephone, sendMsg);//调用短信接口发送验证码
        session.setAttribute("code",code);//将验证码存作用域以做比较
        session.setMaxInactiveInterval(120);//验证码时限120秒
        return "{\"result\":"+result+"}";
    }
}
