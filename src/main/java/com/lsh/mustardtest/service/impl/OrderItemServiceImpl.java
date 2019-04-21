package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.OrderItemMapper;
import com.lsh.mustardtest.pojo.Order;
import com.lsh.mustardtest.pojo.OrderItem;
import com.lsh.mustardtest.pojo.OrderItemExample;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.service.OrderItemService;
import com.lsh.mustardtest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 刘森华
 * 2019/04/17
 */

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem oi) {
        orderItemMapper.insert(oi);
    }

    @Override
    public void delete(Integer id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem oi) {
        orderItemMapper.updateByPrimaryKeySelective(oi);
    }

    @Override
    public OrderItem get(Integer id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> os) {
        for (Order o : os) {
            fill(o);
        }
    }

    @Override
    public List<OrderItem> list(Integer orderID) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andUserIDEqualTo(orderID);
        example.setOrderByClause("id desc");
        List result = orderItemMapper.selectByExample(example);
        return result;
    }

    public void fill(Order o) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOrderIDEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);

        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
    }

    public void setProduct(List<OrderItem> ois) {
        for (OrderItem oi : ois) {
            setProduct(oi);
        }
    }

    public void setProduct(OrderItem oi) {
        Product p = productService.get(oi.getProductID());
        oi.setProduct(p);
    }
}
