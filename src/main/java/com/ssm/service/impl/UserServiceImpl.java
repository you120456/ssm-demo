package com.ssm.service.impl;

import com.ssm.dao.UserMapper;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangfeixiang on 2018/9/4
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> getAllUser() {
        List<User> allUser = userMapper.getAllUser();
        return allUser;
    }

    /**
     * 校验用户名
     * @param username
     * @return
     */
    @Override
    public User checkUserName(String username) {

        return userMapper.checkUserName(username);
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @Override
    public int saveUser(User user) {
        int i = userMapper.saveUser(user);
        return i;
    }

    /**
     * 修改用户
     */
    @Override
    public void updateUser(User user) {

        userMapper.updateUser(user);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Integer id) {

        return  userMapper.deleteUser(id);
    }

}
