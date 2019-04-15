package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product p);
    void delete(Integer id);
    void update(Product p);
    Product get(Integer id);
    List list(Integer warehouseID);
}
