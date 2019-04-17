package com.pjqdyd.service;

import com.pjqdyd.pojo.User;

/**
 * @Description:  [用户的Service接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface UserService {

    //通过openId查询用户是否存在
    Boolean isUserExist(String openId);

    //通过openId查询用户信息
    User findUserByOpenId(String openId);

    //保存用户
    User saveUser(User user);

}
