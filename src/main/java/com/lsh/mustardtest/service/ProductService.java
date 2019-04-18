package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.WareHouse;

import java.util.List;

/**
 * 刘森华
 * 2019/04/13
 */

public interface ProductService {
    void add(Product p);
    void delete(Integer id);
    void update(Product p);
    Product get(Integer id);
    List list(Integer warehouseID);
    void setFirstProductImage(Product p);
    void fill(List<WareHouse> ws);
    void fill(WareHouse w);
}
