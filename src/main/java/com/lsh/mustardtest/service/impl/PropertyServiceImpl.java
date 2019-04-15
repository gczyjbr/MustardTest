package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.PropertyMapper;
import com.lsh.mustardtest.pojo.Property;
import com.lsh.mustardtest.pojo.PropertyExample;
import com.lsh.mustardtest.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 刘森华
 * 2019/04/11
 */

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public void add(Property p) {
        propertyMapper.insert(p);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Property p) {
        propertyMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Property get(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int warehouseID) {
        PropertyExample example = new PropertyExample();
        example.createCriteria().andWarehouseIDEqualTo(warehouseID);
        example.setOrderByClause("id");
        return propertyMapper.selectByExample(example);
    }
}
