package com.pjqdyd.controller;

import com.pjqdyd.pojo.User;
import com.pjqdyd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**   
 * @Description:  [用户Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Api(value = "用户相关业务接口", tags = {"用户controller层"})
@RestController
@RequestMapping("/slife/user")
public class UserContorller {

    @Autowired
    private UserService userService;

    /**
     * 保存用户信息
     * @param user
     * @param bindingResult
     * @return user
     */
    @ApiOperation(value = "保存用户信息", notes = "保存单个用户信息")
    @PostMapping("/save")
    public User saveUser(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println("保存失败");
        }
        return userService.saveUser(user);
    }

}
