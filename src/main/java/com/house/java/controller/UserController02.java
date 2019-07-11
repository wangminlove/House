package com.house.java.controller;

import com.house.entity.Users;
import com.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
//前台用户注册登录控制器
@Controller
@RequestMapping("/page/")
public class UserController02 {
    @Autowired
    private UserService userService;
    //注册时判断用户名是否存在
    @RequestMapping(value = "checkName",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkName(String name){
        int temp = userService.checkUserName(name);
        return "{\"result\":"+temp+"}";
    }
    //用户注册
    @RequestMapping(value = "reg",produces = "text/html;charset=UTF-8")
    public String reg(Users users){
        int temp = userService.AddUser(users);
        if (temp>0){
              return "/page/login";
        }else{
            return "/page/reg";
        }
    }
    //用户登录
    @RequestMapping("loginAction")
    public String login(String name, String password,String veryCode, Model model, HttpSession session){
        String code = (String) session.getAttribute("code");
        if(code.equals(veryCode)){
            Users user = userService.login(name, password);
            if (user!=null){
                //将查到的用户存到session作用域
                session.setAttribute("login",user);
                return "redirect:/page/getHouse";
            }else{
                model.addAttribute("info","用户名或密码不正确!");
                return "/page/login";
            }
        }else{
            model.addAttribute("info","验证码错误!");
            return "/page/login";
        }
    }
}
