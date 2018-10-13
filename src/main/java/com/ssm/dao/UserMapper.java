package com.ssm.dao;

import com.ssm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangfeixiang on 2018/9/4
 * @author smfx1314
 */
@Repository
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     *
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
     * 根据id删除用户
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
