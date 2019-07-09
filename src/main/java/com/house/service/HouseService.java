package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.entity.House;
import com.house.util.HouseBean;
import com.house.util.HouseCondition;
import com.house.util.PageBean;

public interface HouseService {
    //添加出租房信息
    public int addHouse(House house);
    //查询某用户发布的房屋信息(多表连接查询)
    public PageInfo<House> getHouseByUserId(PageBean pageBean,Integer userId);
    //获取单条房屋信息
    public House getHouse(String id);
    //修改房屋信息
    public int updateHouse(House house);
    //逻辑删除房屋信息,实际修改isdel值,0未删除1已删除
    public int deleteHouse(String id,Integer isdel);
    //查询已审核(未审核)的房屋信息0未审核,1已审核
    public PageInfo<House> getHouseIspass(PageBean pageBean, HouseBean houseBean);
    //审核房屋信息
    public int HousePass(String id,Integer ispass);
    //用户浏览查询所有未删除及审核通过的房屋信息
    public PageInfo<House> getBorswerHouse(HouseCondition condition);
}
