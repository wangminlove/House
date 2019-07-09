package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.entity.District;
import com.house.entity.DistrictExample;
import com.house.mapper.DistrictMapper;
import com.house.mapper.StreetMapper;
import com.house.service.DistrictService;
import com.house.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;
     //分页显示所有区域信息
    @Override
    public PageInfo<District> getDistrictPage(PageBean pageBean) {
        DistrictExample example=new DistrictExample();
        DistrictExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        List<District> list = districtMapper.selectByExample(example);
        PageInfo<District>info=new PageInfo<>(list);
        return info;
    }
    //添加
    @Override
    public int Add(District district) {
        return districtMapper.insertSelective(district);
    }
    //修改
    @Override
    public int Update(District district) {
       return districtMapper.updateByPrimaryKey(district);
    }
    //删除
    @Override
    @Transactional
    public int delete(Integer id) {
        streetMapper.deleteStreetByDistrictId(id);
        districtMapper.deleteByPrimaryKey(id);
        return 1;
    }
    //修改时回显当前区域详细
    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }
    //批量删除
    @Override
    public int deleteList(List<Integer> ids) {
        return districtMapper.delMoreDistrict(ids);
    }
    //获取所有区域信息
    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(null);
    }
}
