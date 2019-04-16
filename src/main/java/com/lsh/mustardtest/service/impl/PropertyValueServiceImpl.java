package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.PropertyValueMapper;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.Property;
import com.lsh.mustardtest.pojo.PropertyValue;
import com.lsh.mustardtest.pojo.PropertyValueExample;
import com.lsh.mustardtest.service.PropertyService;
import com.lsh.mustardtest.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 刘森华
 * 2019/04/16
 */

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    @Override
    public void init(Product p) {
        List<Property> pts = propertyService.list(p.getWareHouseID());
        for (Property pt : pts) {
            PropertyValue pv = get(pt.getId(), p.getId());
            if (null == pv) {
                pv = new PropertyValue();
                pv.setProductID(p.getId());
                pv.setPropertyID(pt.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.updateByPrimaryKeySelective(pv);
    }

    @Override
    public PropertyValue get(Integer propertyID, Integer productID) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPropertyIDEqualTo(propertyID).andProductIDEqualTo(productID);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty())
            return null;
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(Integer productID) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andProductIDEqualTo(productID);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv : result) {
            Property property = propertyService.get(pv.getPropertyID());
            pv.setProperty(property);
        }
        return result;
    }
}
