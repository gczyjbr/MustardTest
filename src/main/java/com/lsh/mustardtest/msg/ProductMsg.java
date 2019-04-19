package com.lsh.mustardtest.msg;

import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.PropertyValue;

import java.util.List;

public class ProductMsg {
    Product product;
    List<PropertyValue> propertyValues;
    /*String productSingleImages = "img/productSingle/";
    String productDetailImages = "img/productDetail/";
    String format = "jpg";*/

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
