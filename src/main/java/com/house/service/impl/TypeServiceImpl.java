package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.entity.Type;
import com.house.entity.TypeExample;
import com.house.mapper.HouseMapper;
import com.house.mapper.TypeMapper;
import com.house.service.TypeService;
import com.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private HouseMapper houseMapper;
    //分页查询所有房屋类型
    @Override
    public PageInfo<Type> getTypePage(PageBean pageBean) {
        TypeExample example=new TypeExample();
        TypeExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        List<Type> typeList = typeMapper.selectByExample(example);
        PageInfo<Type>info=new PageInfo<>(typeList);
        return info;
    }
    //添加
    @Override
    public int Add(Type type) {
        return typeMapper.insertSelective(type);
    }
    //修改
    @Override
    public int Update(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }
    //删除
    @Override
    @Transactional
    public int Delete(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }
    //获取单条房屋类型
    @Override
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }
    //批量删除
    @Override
    public int deleteList(List<Integer> ids) {
        return typeMapper.delMdelMoreType(ids);
    }
     //查询所有房屋类型
    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(null);
    }
}
