package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.entity.Street;
import com.house.entity.Type;
import com.house.service.StreetService;
import com.house.util.PageBean;
import com.house.util.StreetBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {
    @Autowired
    private StreetService streetService;
    //district.jsp弹窗显示当前区域id下的所有街道
    @RequestMapping("StreetByDid")
    @ResponseBody
    public Map getStreetById(PageBean pageBean,Integer id){
        PageInfo<Street> pageInfo = streetService.getStreetPageByDid(pageBean, id);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //条件查询所有街道信息
    @RequestMapping("getAllStreet")
    @ResponseBody
    public Map getAllStreet(StreetBean streetBean){
        PageInfo<Street> pageInfo = streetService.getAllStreet(streetBean);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //添加街道
    @RequestMapping("AddStreet")
    @ResponseBody
    public String AddStreet(Street street){
        int temp = streetService.Add(street);
        return "{\"result\":"+temp+"}";
    }
    //修改街道
    @RequestMapping("UpdateStreet")
    @ResponseBody
    public String UpdateStreet(Street street){
        int temp = streetService.Update(street);
        return "{\"result\":"+temp+"}";
    }
    //删除街道
    @RequestMapping(value="deleteStreet")
    @ResponseBody
    public  String Update(Integer id){
        int temp = streetService.delete(id);
        return "{\"result\":"+temp+"}";
    }
    //修改页面获取单条街道信息以便回显
    @RequestMapping(value="getSingleStreet")
    @ResponseBody
    public  Street getSingleStreet(Integer id){
        Street street = streetService.getSingleStreet(id);
        return street;
    }
    //批量删除街道信息
    @RequestMapping("deleteMoreStreet")
    @ResponseBody
    public  String deleteMoreStreet(String ids){
        String[] str = ids.split(",");
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<str.length;i++){
            list.add(Integer.parseInt(str[i]));
        }
        int temp = streetService.delMoreStreet(list);
        return "{\"result\":"+temp+"}";
    }
}
