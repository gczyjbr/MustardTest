package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.WareHouse;

import java.util.List;

/**
 * 刘森华
 * 2019/04/12
 */

public interface WareHouseService {
    List<WareHouse> list();

    void add(WareHouse wareHouse);

    void delete(Integer id);

    WareHouse get(Integer id);

    void update(WareHouse wareHouse);
}
