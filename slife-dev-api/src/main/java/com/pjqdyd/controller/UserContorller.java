package com.pjqdyd.controller;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.exception.SLifeException;
import com.pjqdyd.pojo.User;
import com.pjqdyd.result.ResponseResult;
import com.pjqdyd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    public ResponseResult saveUser(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("[保存用户信息] 参数不正确 user={}", user);
            throw new SLifeException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResponseResult.success(userService.saveUser(user));
    }

}
