package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.entity.Users;
import com.house.util.UserBean;

import java.util.List;

public interface UserService {
    public PageInfo<Users> getUserPage(UserBean userBean);
    //后台添加管理员
    public int Add(Users users);
    //前台普通用户注册
    public int AddUser(Users users);
    public int Update(Users users);
    public int delete(Integer id);
    public Users getUser(Integer id);
    public int deleteList(List<Integer>ids);
    //查询数据库是否有该用户名的数据
    public int checkUserName(String name);
    //普通用户登录
    public Users login(String name,String password);
    //后台管理用户登录
    public Users HTlogin(String name, String password);
}
