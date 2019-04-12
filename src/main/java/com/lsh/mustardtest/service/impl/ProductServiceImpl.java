package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.ProductMapper;
import com.lsh.mustardtest.pojo.Category;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.ProductExample;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.CategoryService;
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
    CategoryService categoryService;
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
        Product p = productMapper.selectByPrimaryKey(id);
        setCategory(p);
        setWareHouse(p);
        return p;
    }

    public void setCategory(List<Product> ps) {
        for (Product p : ps)
            setCategory(p);
    }

    public void setCategory(Product p) {
        int categoryID = p.getCategoryID();
        Category c = categoryService.get(categoryID);
        p.setCategory(c);
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
    public List listByCategory(Integer categoryID) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCategoryIDEqualTo(categoryID);
        example.setOrderByClause("id");
        List result = productMapper.selectByExample(example);
        setCategory(result);
        return result;
    }

    @Override
    public List listByWarehouse(Integer wareHouseID) {
        ProductExample example = new ProductExample();
        example.createCriteria().andWarehouseIDEqualTo(wareHouseID);
        example.setOrderByClause("id");
        List result = productMapper.selectByExample(example);
        setWareHouse(result);
        return result;
    }
}
