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

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
