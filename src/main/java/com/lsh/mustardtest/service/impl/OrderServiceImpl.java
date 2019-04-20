package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.OrderMapper;
import com.lsh.mustardtest.pojo.Order;
import com.lsh.mustardtest.pojo.OrderExample;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.User;
import com.lsh.mustardtest.service.OrderService;
import com.lsh.mustardtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 刘森华
 * 2019/04/17
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Order o) {
        orderMapper.insert(o);
    }

    @Override
    public void delete(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order o) {
        orderMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public Order get(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public void setUser(List<Order> os) {
        for (Order o : os)
            setUser(o);
    }

    public void setUser(Order o) {
        int userID = o.getUserID();
        User u = userService.get(userID);
        o.setUser(u);
    }

    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    @Override
    public Order get(String orderCode) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderCodeEqualTo(orderCode);
        return orderMapper.selectByExample(example).get(0);
    }

    @Override
    public List<Order> list(Integer userID, String status) {
        OrderExample example = new OrderExample();
        example.createCriteria().andUserIDEqualTo(userID).andStatusEqualTo(status);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }

    @Override
    public String date(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        return format.format(date);
    }

    @Override
    public List<Order> list(Integer userID) {
        OrderExample example = new OrderExample();
        example.createCriteria().andUserIDEqualTo(userID);
        example.setOrderByClause("id desc");
        return  orderMapper.selectByExample(example);
    }
}
