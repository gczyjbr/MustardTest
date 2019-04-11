package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.Category;
import com.lsh.mustardtest.util.Page;

import java.util.List;

/**
 * 刘森华
 * 2019/04/10
 */

public interface CategoryMapper {
    List<Category> list(Page page);

    int total();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}
