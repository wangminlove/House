package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.entity.Users;
import com.house.entity.UsersExample;
import com.house.mapper.UsersMapper;
import com.house.service.UserService;
import com.house.util.MD5Utils;
import com.house.util.PageBean;
import com.house.util.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    //带条件分页所有显示管理员信息isadmin=1
    @Override
    public PageInfo<Users> getUserPage(UserBean userBean) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIsadminEqualTo(1);
        if (userBean.getName()!=null){
            criteria.andNameLike("%"+userBean.getName()+"%");
        }
        if (userBean.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userBean.getTelephone()+"%");
        }
        PageHelper.startPage(userBean.getPage(),userBean.getRows());
        List<Users> list = usersMapper.selectByExample(example);
        return new PageInfo<Users>(list);
    }
    //添加用户信息
    @Override
    public int Add(Users users) {
        return usersMapper.insertSelective(users);
    }
    //前台普通用户注册
    @Override
    public int AddUser(Users users) {
        users.setIsadmin(0);
        /*String name = users.getName();
        try{
            //转换中文乱码
            name=new String(name.getBytes("ISO-8859-1"), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        users.setName(name);*/
        //密码加密
        String pwd = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(pwd);
        return usersMapper.insertSelective(users);
    }
    //修改用户信息
    @Override
    public int Update(Users users) {
        return usersMapper.updateByPrimaryKey(users);
    }
    //删除用户信息
    @Override
    public int delete(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }
    //获取单条用户信息
    @Override
    public Users getUser(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }
    //批量删除
    @Override
    public int deleteList(List<Integer> ids) {
        return usersMapper.deleteUserById(ids);
    }
    //查询数据库是否有该用户名的数据
    @Override
    public int checkUserName(String name) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        /*try{
            //转换中文乱码
            name=new String(name.getBytes("ISO-8859-1"), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }*/
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);
        List<Users> list = usersMapper.selectByExample(example);
        return list.size();//0没有,1有
    }
    //普通用户登录
    @Override
    public Users login(String name, String password) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);
        //密码加密后进行对比
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(example);
        if (list.size()==1){
            return list.get(0);
        }
        return null;
    }
    //管理用户登录
    @Override
    public Users HTlogin(String name, String password) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(1);
        //密码加密后进行对比
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(example);
        if (list.size()==1){
            return list.get(0);
        }
        return null;
    }
}
