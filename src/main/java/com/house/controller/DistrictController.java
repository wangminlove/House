package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.entity.District;
import com.house.service.DistrictService;
import com.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/admin/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    //分页
    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(PageBean pageBean){
        PageInfo<District> pageinfo = districtService.getDistrictPage(pageBean);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageinfo.getTotal());
        map.put("rows",pageinfo.getList());
        return map;
    }

    //添加
    @RequestMapping(value="AddDistrict")
    @ResponseBody
    public String add(District district){
        int temp = districtService.Add(district);
        return "{\"result\":"+temp+"}";
    }
    //删除单条
    @RequestMapping(value="deleteDistrict")
    @ResponseBody
    public  String Update(Integer id){
        try{
            int temp = districtService.delete(id);
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":0}";
        }

    }
    //修改
    @RequestMapping(value="UpdateDistrict")
    @ResponseBody
    public  String deleteDistrict(District district){
        int temp = districtService.Update(district);
        return "{\"result\":"+temp+"}";
    }
    //获取单条District
    @RequestMapping(value ="getSingleDistrict")
    @ResponseBody
    public District getDistrict(Integer id){
        return districtService.getDistrict(id);
    }
    //批量删除
    @RequestMapping("deleteMoreDistrict")
    @ResponseBody
    public  String deleteMoreDistrict(String ids){
        String[] str = ids.split(",");
        List<Integer>list=new ArrayList<>();
        for (int i=0;i<str.length;i++){
            list.add(Integer.parseInt(str[i]));
        }
        int temp = districtService.deleteList(list);
        return "{\"result\":"+temp+"}";
    }
}
