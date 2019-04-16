package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.Property;
import com.lsh.mustardtest.pojo.PropertyExample;
import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface PropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Property record);

    int insertSelective(Property record);

    List<Property> selectByExample(PropertyExample example);

    Property selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);
}