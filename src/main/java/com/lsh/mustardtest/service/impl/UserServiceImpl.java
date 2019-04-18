package com.lsh.mustardtest.service.impl;

import com.lsh.mustardtest.mapper.UserMapper;
import com.lsh.mustardtest.pojo.User;
import com.lsh.mustardtest.pojo.UserExample;
import com.lsh.mustardtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User u) {
        userMapper.insert(u);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User u) {
        userMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list() {
        UserExample example = new UserExample();
        example.setOrderByClause("id");
        return userMapper.selectByExample(example);
    }

    @Override
    public boolean isExist(long phone) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<User> result = userMapper.selectByExample(example);
        if (!result.isEmpty())
            return true;
        return false;
    }

    @Override
    public User get(Long phone, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone).andPasswordEqualTo(password);
        List<User> result = userMapper.selectByExample(example);
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

}
