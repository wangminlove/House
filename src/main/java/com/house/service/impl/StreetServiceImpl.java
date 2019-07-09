package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.entity.District;
import com.house.entity.Street;
import com.house.entity.StreetExample;
import com.house.mapper.StreetMapper;
import com.house.service.DistrictService;
import com.house.service.StreetService;
import com.house.util.PageBean;
import com.house.util.StreetBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Autowired
    private DistrictService districtService;

    //通过区域id获取当前区域下的所有街道
    @Override
    public PageInfo<Street> getStreetPageByDid(PageBean pageBean,Integer districtId) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(districtId);
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        List<Street> list = streetMapper.selectByExample(example);
        for (Street street:list) {
            Integer id = street.getDistrictId();
            District district = districtService.getDistrict(id);
            street.setDistrictName(district.getName());
        }
        return new PageInfo<Street>(list);
    }
    //添加街道
    @Override
    public int Add(Street street) {
        return streetMapper.insertSelective(street);
    }
    //删除街道
    @Override
    public int delete(Integer id) {
        return streetMapper.deleteByPrimaryKey(id);
    }
    //分页条件查询所有街道信息
    @Override
    public PageInfo<Street> getAllStreet(StreetBean streetBean) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        if (streetBean.getName()!=null){
            criteria.andNameLike("%"+streetBean.getName()+"%");
        }
        if (streetBean.getDistrictId()!=null){
            criteria.andDistrictIdEqualTo(streetBean.getDistrictId());
        }
        PageHelper.startPage(streetBean.getPage(),streetBean.getRows());
        List<Street> list = streetMapper.selectByExample(example);
        for (Street street:list) {
            Integer id = street.getDistrictId();
            District district = districtService.getDistrict(id);
            street.setDistrictName(district.getName());
        }
        return new PageInfo<Street>(list);
    }
    //修改街道
    @Override
    public int Update(Street street) {
        return streetMapper.updateByPrimaryKeySelective(street);
    }
    //修改页面获取单条街道信息以便回显
    @Override
    public Street getSingleStreet(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }
    //批量删除街道信息
    @Override
    public int delMoreStreet(List<Integer> ids) {
        return streetMapper.delMoreStreet(ids);
    }
    //异步方式通过区域id获取当前区域的所有街道信息
    @Override
    public List<Street> getAllStreet(Integer did) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        List<Street> list = streetMapper.selectByExample(example);
        return list;
    }
}
