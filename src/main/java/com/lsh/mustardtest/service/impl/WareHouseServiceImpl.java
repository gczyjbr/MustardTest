package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.WareHouseMapper;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.pojo.WareHouseExample;
import com.lsh.mustardtest.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareHouseServiceImpl implements WareHouseService {
    @Autowired
    WareHouseMapper wareHouseMapper;

    @Override
    public List<WareHouse> list() {
        WareHouseExample example = new WareHouseExample();
        example.setOrderByClause("id desc");
        return wareHouseMapper.selectByExample(example);
    }

    @Override
    public void add(WareHouse wareHouse) {
        wareHouseMapper.insert(wareHouse);
    }

    @Override
    public void delete(Integer id) {
        wareHouseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WareHouse get(Integer id) {
        return wareHouseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(WareHouse wareHouse) {
        wareHouseMapper.updateByPrimaryKeySelective(wareHouse);
    }
}