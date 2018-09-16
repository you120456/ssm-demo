package com.ssm.service;

import com.ssm.entity.User;

import java.util.List;

/**
 * Created by jiangfeixiang on 2018/9/4
 */
public interface UserService {

    /**
     * 返回所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 校验用户名
     * @param username
     * @return
     */
    User checkUserName(String username);

    /**
     * 保存用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 删除用户根据id
     * @param id
     * @return
     */
    int deleteUser(Integer id);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);
}
