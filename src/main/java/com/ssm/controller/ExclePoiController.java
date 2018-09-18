package com.ssm.controller;

import com.ssm.common.FileUtil;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 姜飞祥
 * @Description: 导入导出controller
 * @Date: Create in 2018/9/17 22:43
 * @param: $params$
 * @return: $returns$
 */
@Controller
public class ExclePoiController {

    /**
     * 注入
     */
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public void export(HttpServletResponse response){
        /**
         * 查询所有用户信息
         */
        List<User> allUser = userService.getAllUser();
        //导出操作
        FileUtil.exportExcel(allUser,"用户信息","用户表",User.class,"用户表.xls",response);
    }
}
