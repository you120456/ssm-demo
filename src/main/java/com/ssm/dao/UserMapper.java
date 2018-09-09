package com.ssm.dao;

import com.ssm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangfeixiang on 2018/9/4
 */
@Repository
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<User> getAllUser();
}
