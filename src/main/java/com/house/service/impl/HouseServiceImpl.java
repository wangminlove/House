package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.entity.House;
import com.house.entity.HouseExample;
import com.house.mapper.HouseMapper;
import com.house.service.HouseService;
import com.house.util.HouseBean;
import com.house.util.HouseCondition;
import com.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    //用户发布房屋信息
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }
    //通过用户id获取该用户发布的所有房屋信息
    @Override
    public PageInfo<House> getHouseByUserId(PageBean pageBean, Integer userId) {
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        List<House> list = houseMapper.getHouseByUserId(userId);
        return new PageInfo<House>(list);
    }
    //通过房屋id,获取单条房屋信息
    @Override
    public House getHouse(String id) {
        return houseMapper.getHouse(id);
    }
   //用户个人修改自己发布的房屋信息
    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }
    //逻辑删除房屋信息isdel=1
    @Override
    public int deleteHouse(String id, Integer isdel) {
        House house=new House();
        house.setId(id);
        house.setIsdel(isdel);
        return houseMapper.updateByPrimaryKeySelective(house);
    }
    //获取所有审核通过ispass=1或审核未通过ispass=0的房屋信息
    @Override
    public PageInfo<House> getHouseIspass(PageBean pageBean,HouseBean houseBean) {
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        List<House> houses = houseMapper.getHouseIspass(houseBean);
        PageInfo<House>pageInfo=new PageInfo<>(houses);
        return pageInfo;
    }
    //操作房屋审核通过ispass=1或审核未通过ispass=0
    @Override
    public int HousePass(String id, Integer ispass) {
        House house=new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);
    }
    //用户浏览查询所有未删除及审核通过的房屋信息
    @Override
    public PageInfo<House> getBorswerHouse(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> houses = houseMapper.getBorswerHouse(condition);
        PageInfo<House>pageInfo=new PageInfo<>(houses);
        return pageInfo;
    }
}
