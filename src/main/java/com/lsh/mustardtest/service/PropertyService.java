package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Property;

import java.util.List;

/**
 * 刘森华
 * 2019/04/11
 */

public interface PropertyService {
    void add(Property p);
    void delete(int id);
    void update(Property p);
    Property get(int id);
    List list(int warehouseID);
}
