package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.Order;
import com.lsh.mustardtest.pojo.OrderExample;
import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}