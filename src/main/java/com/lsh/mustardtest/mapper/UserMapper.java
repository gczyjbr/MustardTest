package com.lsh.mustardtest.mapper;

import com.lsh.mustardtest.pojo.User;
import com.lsh.mustardtest.pojo.UserExample;
import java.util.List;

/**
 * 刘森华
 * 2019/04/15
 */

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}