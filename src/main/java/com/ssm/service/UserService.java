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
}
