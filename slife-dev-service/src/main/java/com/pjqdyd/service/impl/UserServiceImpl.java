package com.pjqdyd.service.impl;

import com.pjqdyd.dao.UserRepository;
import com.pjqdyd.pojo.User;
import com.pjqdyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**   
 * @Description:  [用户Service实现类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存用户信息
     * @param user
     * @return User
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 通过openId判断用户是否存在
     * @param openId
     * @return true/false
     */
    @Override
    public Boolean isUserExist(String openId) {
        return userRepository.existsByOpenId(openId);
    }

    /**
     * 通过openId查询用户信息
     * @param openId
     * @return User
     */
    @Override
    public User findUserByOpenId(String openId) {
        return userRepository.findByOpenId(openId);
    }
}
