package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.Category;
import com.lsh.mustardtest.util.Page;

import java.util.List;

/**
 * 刘森华
 * 2019/04/10
 */

public interface CategoryService {
    int total();

    List<Category> list(Page page);

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}
