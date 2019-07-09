package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.entity.Street;
import com.house.util.PageBean;
import com.house.util.StreetBean;

import java.util.List;

public interface StreetService {
    public PageInfo<Street> getStreetPageByDid(PageBean pageBean,Integer districtId);
    public int Add(Street street);
    public int delete(Integer id);
    public PageInfo<Street> getAllStreet(StreetBean streetBean);
    public int Update(Street street);
    public Street getSingleStreet(Integer id);
    public int delMoreStreet(List<Integer>ids);
    public List<Street> getAllStreet(Integer did);
}
