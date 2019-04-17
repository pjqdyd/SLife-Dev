package com.pjqdyd.dao;

import com.pjqdyd.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [用户repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 通过openId判断用户是否存在
     */
    Boolean existsByOpenId(String openId);

    /**
     * 通过openId查询用户信息
     */
    User findByOpenId(String openId);

}
