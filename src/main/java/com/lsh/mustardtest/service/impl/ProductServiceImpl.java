package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.ProductMapper;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.ProductExample;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.ProductService;
import com.lsh.mustardtest.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    WareHouseService wareHouseService;

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
        return productMapper.selectByPrimaryKey(id);
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
    public List listByWarehouse(Integer wareHouseID) {
        ProductExample example = new ProductExample();
        example.createCriteria().andWareHouseIDEqualTo(wareHouseID);
        example.setOrderByClause("id");
        List result = productMapper.selectByExample(example);
        setWareHouse(result);
        return result;
    }
}
