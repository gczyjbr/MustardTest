package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.ProductMapper;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.ProductExample;
import com.lsh.mustardtest.pojo.ProductImage;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.ProductImageService;
import com.lsh.mustardtest.service.ProductService;
import com.lsh.mustardtest.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 刘森华
 * 2019/04/13
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    WareHouseService wareHouseService;
    @Autowired
    ProductImageService productImageService;

    @Override
    public void add(Product p) {
        productMapper.insert(p);
    }

    @Override
    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Product get(Integer id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setWareHouse(p);
        return p;
    }

    public void setWareHouse(List<Product> ps) {
        for (Product p : ps)
            setWareHouse(p);
    }

    public void setWareHouse(Product p) {
        int wareHouseID = p.getWareHouseID();
        WareHouse w = wareHouseService.get(wareHouseID);
        p.setWareHouse(w);
    }

    @Override
    public List list(Integer warehouseID) {
        ProductExample example = new ProductExample();
        example.createCriteria().andWareHouseIDEqualTo(warehouseID);
        example.setOrderByClause("id");
        List result = productMapper.selectByExample(example);
        setWareHouse(result);
        setFirstProductImage(result);
        return result;
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }

    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps)
            setFirstProductImage(p);
    }
}
