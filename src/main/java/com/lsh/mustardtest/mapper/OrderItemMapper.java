package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.OrderItem;
import com.lsh.mustardtest.pojo.OrderItemExample;
import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}