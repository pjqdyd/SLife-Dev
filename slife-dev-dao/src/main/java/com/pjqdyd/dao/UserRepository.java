package com.pjqdyd.dao;

import com.pjqdyd.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [用户repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface UserRepository extends JpaRepository<User, String> {
}
