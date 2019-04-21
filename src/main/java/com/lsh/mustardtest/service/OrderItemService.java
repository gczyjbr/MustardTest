package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Order;
import com.lsh.mustardtest.pojo.OrderItem;

import java.util.List;

/**
 * 刘森华
 * 2019/04/17
 */

public interface OrderItemService {
    void add(OrderItem oi);

    void delete(Integer id);

    void update(OrderItem oi);

    OrderItem get(Integer id);

    List<OrderItem> list();

    void fill(List<Order> os);

    List<OrderItem> list(Integer orderID);
}
