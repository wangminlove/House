package com.house.java.controller;

import com.house.entity.Type;
import com.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class TypeController02 {
    @Autowired
    private TypeService typeService;
    //异步返回所有房屋类型
    @RequestMapping("getAllType")
    @ResponseBody
    public List<Type> getAllType(){
        List<Type> types = typeService.getAllType();
        return types;
    }
}
