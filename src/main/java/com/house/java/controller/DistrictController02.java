package com.house.java.controller;

import com.house.entity.District;
import com.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class DistrictController02 {
    @Autowired
    private DistrictService districtService;
    //异步获取所有区域信息
    @RequestMapping("getAllDistrict")
    @ResponseBody
    public List<District> getAllDistrict(){
        List<District> districts = districtService.getAllDistrict();
        return districts;
    }
}
