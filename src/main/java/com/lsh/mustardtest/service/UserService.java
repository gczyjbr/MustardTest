package com.lsh.mustardtest.service;

import com.lsh.mustardtest.pojo.User;

import java.util.List;

/**
 * 刘森华
 * 2019/04/17
 */

public interface UserService {
    void add(User u);
    void delete(Integer id);
    void update(User u);
    User get(Integer id);
    List list();
    boolean isExist(long phone);
}
