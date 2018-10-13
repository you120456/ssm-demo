package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.ResponseData;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     *这里统一返回ResultData封装的json数据格式，不在用Map<String,Object>形式了
     * @return
     */
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getAllUser(@RequestParam(defaultValue="1",required=true,value="pn") Integer pn){
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
        PageInfo<User> pageInfo=new PageInfo(allUser);

       return ResponseData.success(pageInfo);

    }

    /**
     * 校验用户名
     * @param username
     * @return
     */
    @RequestMapping(value = "/checkUser/{username}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData checkUserName(@PathVariable("username") String username){
        //校验用户名
        User user = userService.checkUserName(username);
        System.out.println(user);
        return ResponseData.success(user);
    }

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData saveUser(@RequestBody User user){
       int i = userService.saveUser(user);
        return ResponseData.success();
    }

    /**
     * 修改员工信息（更新）
     */

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateUser(@RequestBody User user){
        System.out.print(user);
        userService.updateUser(user);
        return ResponseData.success();
    }

    /**
     * 根据id删除用户
     */
    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseData deleteUser(@PathVariable("id")Integer id){
        //保存用户
        int i = userService.deleteUser(id);
        System.out.println(i);
        return ResponseData.success();
    }
}
