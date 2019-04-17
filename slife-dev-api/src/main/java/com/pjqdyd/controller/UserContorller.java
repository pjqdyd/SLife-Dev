package com.pjqdyd.controller;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.exception.SLifeException;
import com.pjqdyd.pojo.User;
import com.pjqdyd.pojo.vo.UserVO;
import com.pjqdyd.result.ResponseResult;
import com.pjqdyd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *    
 *
 * @Description:  [用户Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

@Api(value = "用户相关业务接口", tags = {"用户controller层"})
@Slf4j
@RestController
@RequestMapping("/slife/user")
public class UserContorller {

    @Autowired
    private UserService userService;

    /**
     * 用户qq登录验证
     *
     * @param openId       用户qq授权登录的openid
     * @param access_token 用于请求qq服务器的验证参数access_token
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户qq授权登录验证")
    @PostMapping("/login")
    public ResponseResult login(@RequestParam String openId,
                                @RequestParam String access_token) {

        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(access_token)) {
            log.error("登录参数不能为空 openId={} access_token={}",openId,access_token);
            throw new SLifeException(ResultEnum.PARAM_ERROR);
        } else if (userService.isUserExist(openId)) {//1.验证用户的openId是否已经存在数据库
            //获取数据库的用户信息,返回给前端
            User user = userService.findUserByOpenId(openId);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return ResponseResult.success(userVO);
        }

        return ResponseResult.success(null);
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @param bindingResult
     * @return user
     */
    @ApiOperation(value = "保存用户信息", notes = "保存单个用户信息")
    @PostMapping("/save")
    public ResponseResult saveUser(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("[保存用户信息] 参数不正确 user={}", user);
            throw new SLifeException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        return ResponseResult.success(userService.saveUser(user));
    }

}
