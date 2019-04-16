package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.PropertyValue;

import java.util.List;

/**
 * 刘森华
 * 2019/04/16
 */

public interface PropertyValueService {
    void init(Product p);
    void update(PropertyValue pv);

    PropertyValue get(Integer productID, Integer propertyID);
    List<PropertyValue> list(Integer productID);
}
