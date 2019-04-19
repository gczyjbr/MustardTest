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
        setFirstProductImage(p);
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

    @Override
    public void fill(List<WareHouse> ws) {
        for (WareHouse w : ws) {
            fill(w);
        }
    }

    @Override
    public void fill(WareHouse w) {
        List<Product> ps = list(w.getId());
        w.setProducts(ps);
    }

    @Override
    public List usableProducts(Integer wareHouseID) {
        ProductExample example =  new ProductExample();
        example.createCriteria().andWareHouseIDEqualTo(wareHouseID).andUsedEqualTo(false).andUserIDEqualTo(null);
        example.setOrderByClause("id");
        List result = productMapper.selectByExample(example);
        setWareHouse(result);
        setFirstProductImage(result);
        return result;
    }

    @Override
    public List productsByType(Integer wareHouseID, String type) {
        ProductExample example = new ProductExample();
        example.createCriteria().andWareHouseIDEqualTo(wareHouseID).andUsedEqualTo(false).andUserIDEqualTo(null).andTypeEqualTo(type);
        example.setOrderByClause("id");
        List result = productMapper.selectByExample(example);
        setWareHouse(result);
        setFirstProductImage(result);
        return result;
    }

    @Override
    public List myProducts(Integer userID) {
        ProductExample example = new ProductExample();
        example.createCriteria().andUserIDEqualTo(userID);
        example.setOrderByClause("id");
        List result = productMapper.selectByExample(example);
        setWareHouse(result);
        setFirstProductImage(result);
        return result;
    }


    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps)
            setFirstProductImage(p);
    }
}
