package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.ProductImageMapper;
import com.lsh.mustardtest.pojo.ProductImage;
import com.lsh.mustardtest.pojo.ProductImageExample;
import com.lsh.mustardtest.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 刘森华
 * 2019/04/16
 */

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public void add(ProductImage pi) {
        productImageMapper.insert(pi);
    }

    @Override
    public void delete(Integer id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage pi) {
        productImageMapper.updateByPrimaryKeySelective(pi);
    }

    @Override
    public ProductImage get(Integer id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int productID, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andProductIDEqualTo(productID).andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }
}
