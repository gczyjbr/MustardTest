package com.lsh.mustardtest.pojo;

public class WareHouse {
    private Integer id;

    private String name;

    private String address;

    private Integer tiny_stock;

    private Integer small_stock;

    private Integer middle_stock;

    private Integer big_stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getTiny_stock() {
        return tiny_stock;
    }

    public void setTiny_stock(Integer tiny_stock) {
        this.tiny_stock = tiny_stock;
    }

    public Integer getSmall_stock() {
        return small_stock;
    }

    public void setSmall_stock(Integer small_stock) {
        this.small_stock = small_stock;
    }

    public Integer getMiddle_stock() {
        return middle_stock;
    }

    public void setMiddle_stock(Integer middle_stock) {
        this.middle_stock = middle_stock;
    }

    public Integer getBig_stock() {
        return big_stock;
    }

    public void setBig_stock(Integer big_stock) {
        this.big_stock = big_stock;
    }
}