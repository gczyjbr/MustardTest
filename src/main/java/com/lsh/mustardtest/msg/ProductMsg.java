package com.lsh.mustardtest.msg;

import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.PropertyValue;

import java.util.List;

/**
 * 刘森华
 * 2019/04/18
 */

public class ProductMsg {
    Product product;
    List<PropertyValue> propertyValues;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
