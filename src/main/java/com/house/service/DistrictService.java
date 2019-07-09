package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.entity.District;
import com.house.util.PageBean;

import java.util.List;

public interface DistrictService {
    public PageInfo<District> getDistrictPage(PageBean pageBean);
    public int Add(District district);
    public int Update(District district);
    public int delete(Integer id);
    public District getDistrict(Integer id);
    public int deleteList(List<Integer> ids);
    public List<District> getAllDistrict();
}
