package com.house.controller;

import com.github.pagehelper.PageInfo;
import com.house.entity.Type;
import com.house.service.TypeService;
import com.house.util.PageBean;
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
public class TypeController {
    @Autowired
    private TypeService typeService;
    //分页所有房屋类型
    @RequestMapping("getType")
    @ResponseBody
    public Map getType(PageBean pageBean){
        PageInfo<Type> pageInfo = typeService.getTypePage(pageBean);
        Map<String,Object>map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //添加
    @RequestMapping("AddType")
    @ResponseBody
    public String AddType(Type type){
        int temp = typeService.Add(type);
        return "{\"result\":"+temp+"}";
    }
    //修改
    @RequestMapping("UpdateType")
    @ResponseBody
    public String updateType(Type type){
        int temp = typeService.Update(type);
        return "{\"result\":"+temp+"}";
    }
    //删除
    @RequestMapping("deleteType")
    @ResponseBody
    public String deleteType(Integer id){
        int temp=typeService.Delete(id);
        return "{\"result\":"+temp+"}";
    }
    //获取单条记录
    @RequestMapping("getSingleType")
    @ResponseBody
    public Type getSingleType(Integer id){
        return typeService.getType(id);
    }
    @RequestMapping("deleteListType")
    @ResponseBody
    public String deleteListType(String id){
        String[] str = id.split(",");
        List<Integer>ids=new ArrayList<>();
        for (int i=0;i<str.length;i++){
            ids.add(Integer.parseInt(str[i]));
        }
        int temp = typeService.deleteList(ids);
        return "{\"result\":"+temp+"}";
    }
}
