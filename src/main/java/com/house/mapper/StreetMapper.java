package com.house.mapper;

import com.house.entity.Street;
import com.house.entity.StreetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StreetMapper {
    int countByExample(StreetExample example);

    int deleteByExample(StreetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Street record, @Param("example") StreetExample example);

    int updateByExample(@Param("record") Street record, @Param("example") StreetExample example);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    int deleteStreetByDistrictId(Integer districtId);

    int delMoreStreet(List<Integer>ids);
}