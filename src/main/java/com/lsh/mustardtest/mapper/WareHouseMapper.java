package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.pojo.WareHouseExample;

import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface WareHouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WareHouse record);

    int insertSelective(WareHouse record);

    List<WareHouse> selectByExample(WareHouseExample example);

    WareHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WareHouse record);

    int updateByPrimaryKey(WareHouse record);
}