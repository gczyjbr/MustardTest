package com.lsh.mustardtest.pojo;

/**
 * 刘森华
 * 2019/04/15
 */

public class Property {
    private Integer id;

    private Integer warehouseID;

    private String name;

    /**
     * 非数据库字段
     **/
    private WareHouse wareHouse;

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}