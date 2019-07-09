package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.entity.House;
import com.house.service.HouseService;
import com.house.util.HouseBean;
import com.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController02 {
    @Autowired
    private HouseService houseService;
    //审核通过的房屋信息
    @RequestMapping("ispassHouse")
    @ResponseBody
    public Map ispassHouse(PageBean pageBean, HouseBean houseBean){
        houseBean.setIspass(1);
        PageInfo<House> pageInfo = houseService.getHouseIspass(pageBean, houseBean);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //审核未通过的房屋信息
    @RequestMapping("nopassHouse")
    @ResponseBody
    public Map nopassHouse(PageBean pageBean, HouseBean houseBean){
        PageInfo<House> pageInfo = houseService.getHouseIspass(pageBean, houseBean);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //操作房屋审核通过
    @RequestMapping("HousePass")
    @ResponseBody
    public String HousePass(String id){
        int temp = houseService.HousePass(id, 1);
        return "{\"result\":"+temp+"}";
    }
    //操作房屋审核不通过
    @RequestMapping("HouseNoPass")
    @ResponseBody
    public String HouseNoPass(String id){
        int temp = houseService.HousePass(id, 0);
        return "{\"result\":"+temp+"}";
    }
}
