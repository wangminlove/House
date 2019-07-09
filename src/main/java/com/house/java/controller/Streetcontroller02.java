package com.house.java.controller;

import com.house.entity.Street;
import com.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class Streetcontroller02 {
     @Autowired
     private StreetService streetService;
     @RequestMapping("getListStreet")
     @ResponseBody
     public List<Street> getListStreet(Integer id){
         //异步方式通过区域id获取当前区域的所有街道信息
         List<Street> list = streetService.getAllStreet(id);
         return list;
     }
}
