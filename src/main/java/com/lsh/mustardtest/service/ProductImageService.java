package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.ProductImage;

import java.util.List;

/**
 * 刘森华
 * 2019/04/16
 */

public interface ProductImageService {

    String type_single = "type_single";
    String type_detail = "type_detail";

    void add(ProductImage pi);

    void delete(Integer id);

    void update(ProductImage pi);

    ProductImage get(Integer id);

    List list(int productID, String type);
}
