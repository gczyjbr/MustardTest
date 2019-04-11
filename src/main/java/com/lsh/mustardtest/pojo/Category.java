package com.lsh.mustardtest.pojo;

/**
 * 刘森华
 * 2019/04/10
 */

public class Category {
    private Integer id;
    private String name;

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
}
