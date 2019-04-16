package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.ProductImage;
import com.lsh.mustardtest.pojo.ProductImageExample;
import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface ProductImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
}