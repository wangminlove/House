package com.house.mapper;

import com.house.entity.House;
import com.house.entity.HouseExample;
import java.util.List;

import com.house.util.HouseBean;
import com.house.util.HouseCondition;
import org.apache.ibatis.annotations.Param;

public interface HouseMapper {
    int countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int deleteHouseBytypeId(Integer typeId);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> getHouseByUserId(Integer userId);

    //根据houseId获取house信息.做连表,同时获取区域id
    House getHouse(String id);
    //查询未审核(已审核的)的房屋信息
    List<House>getHouseIspass(HouseBean houseBean);
    //用户浏览查询所有未删除及审核通过的房屋信息
    List<House>getBorswerHouse(HouseCondition condition);
}