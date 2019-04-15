package com.lsh.mustardtest.pojo;

public class Product {
    private Integer id;

    private String productCode;

    private String type;

    private Float originalPrice;

    private Float promotePrice;

    private Integer wareHouseID;

    private Boolean used;

    private Integer userID;

    /** 非数据库字段 **/
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getWareHouseID() {
        return wareHouseID;
    }

    public void setWareHouseID(Integer wareHouseID) {
        this.wareHouseID = wareHouseID;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}