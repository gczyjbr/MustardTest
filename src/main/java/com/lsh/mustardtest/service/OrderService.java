package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 刘森华
 * 2019/04/17
 */

public interface OrderService {
    String waitPay = "waitPay";
    String waitConfirm = "waitConfirm";
    String finish = "finish";
    String delete = "delete";

    void add(Order o);

    void delete(Integer id);

    void update(Order o);

    Order get(Integer id);

    List<Order> list();

    Order get(String orderCode);

    List<Order> list(Integer userID, String status);

    String date(Date date);

    List<Order> list(Integer userID);
}
