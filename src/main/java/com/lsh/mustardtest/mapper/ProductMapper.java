package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.ProductExample;
import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}