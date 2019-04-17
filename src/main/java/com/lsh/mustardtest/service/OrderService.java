package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Order;

import java.util.List;

/**
 * 刘森华
 * 2019/04/17
 */

public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String finish = "finish";
    String delete = "delete";
    String renting ="renting";

    void add(Order o);

    void delete(Integer id);

    void update(Order o);

    Order get(Integer id);

    List<Order> list();
}
