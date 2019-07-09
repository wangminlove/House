package com.house.service;

import com.github.pagehelper.PageInfo;
import com.house.entity.Type;
import com.house.util.PageBean;

import java.util.List;

public interface TypeService {
    public PageInfo<Type> getTypePage(PageBean pageBean);
    public int Add(Type type);
    public int Update(Type type);
    public int Delete(Integer id);
    public Type getType(Integer id);
    public int deleteList(List<Integer>ids);
    public List<Type> getAllType();
}
