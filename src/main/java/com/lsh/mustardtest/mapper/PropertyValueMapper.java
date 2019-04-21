package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.PropertyValue;
import com.lsh.mustardtest.pojo.PropertyValueExample;

import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface PropertyValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyValue record);

    int insertSelective(PropertyValue record);

    List<PropertyValue> selectByExample(PropertyValueExample example);

    PropertyValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyValue record);

    int updateByPrimaryKey(PropertyValue record);
}