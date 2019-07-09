package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.entity.Users;
import com.house.service.UserService;
import com.house.util.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UserService userService;
    //后台管理页面的管理员用户显示
    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String, Object> getUsers(UserBean userBean){
        PageInfo<Users> pageInfo = userService.getUserPage(userBean);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //添加
    @RequestMapping(value="AddUsers")
    @ResponseBody
    public String add(Users users){
        int temp = userService.Add(users);
        return "{\"result\":"+temp+"}";
    }
    //修改
    @RequestMapping("UpdateUsers")
    @ResponseBody
    public String UpdateUsers(Users users){
        int temp = userService.Update(users);
        return "{\"result\":"+temp+"}";
    }
    //获取单条记录
    @RequestMapping("getSingleUsers")
    @ResponseBody
    public Users getSingleUsers(Integer id){
        return userService.getUser(id);
    }
    //删除
    @RequestMapping("deleteUsers")
    @ResponseBody
    public String deleteUsers(Integer id){
        int temp=userService.delete(id);
        return "{\"result\":"+temp+"}";
    }
    //批量删除
    @RequestMapping("deleteUserList")
    @ResponseBody
    public String deleteUserList(String id){
        String[] str = id.split(",");
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<str.length;i++){
            list.add(Integer.parseInt(str[i]));
        }
        int temp=userService.deleteList(list);
        return "{\"result\":"+temp+"}";
    }
    //管理员页面登录
    @RequestMapping("HtLogin")
    public String HtLogin(String name, String password, Model model, HttpSession session){
        Users user = userService.HTlogin(name, password);
        if (user!=null){
            session.setAttribute("loginInfo",user);
            //登录成功跳转admin.jsp
            return "/admin/admin";
        }else{
            System.out.println();
            model.addAttribute("info","用户名或密码不正确!");
            //登陆失败返回login.jsp
            return "/admin/login";
        }
    }
}
