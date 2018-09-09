package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangfeixiang on 2018/9/4
 * @author
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllUser(@RequestParam(defaultValue="1",required=true,value="pn") Integer pn){
        Map<String, Object> map = new HashMap<>();
        /**
         * 每页显示记录数
         *
         */
        Integer pageSize=5;
        /**
         * 分页查询，注意顺序，startPage()方法放前面
         */
        PageHelper.startPage(pn, pageSize);
        /**
         * 获取所用用户信息
         */
        List<User> allUser = userService.getAllUser();
        /**
         * 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
         * 封装了详细的分页信息,传入连续显示的页数
         */
        PageInfo<User> pageInfo=new PageInfo(allUser,5);
        map.put("code",100);
        map.put("msg","成功");
        map.put("pageInfo",pageInfo);
        return map;
    }
}
