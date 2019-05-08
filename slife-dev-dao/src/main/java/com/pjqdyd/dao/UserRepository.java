package com.pjqdyd.dao;

import com.pjqdyd.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 通过用户id查询用户信息
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    /**
     * 给用户添加一个赞的方法
     * @param userId
     */
    @Transactional
    @Modifying
    @Query(value = "update tb_user set receive_like_counts=receive_like_counts+1 where user_id=:userId", nativeQuery = true)
    void addUserAlike(@Param("userId") String userId);

    /**
     * 给用户减去一个赞的方法
     * @param userId
     */
    @Transactional
    @Modifying
    @Query(value = "update tb_user set receive_like_counts=receive_like_counts-1 where user_id=:userId", nativeQuery = true)
    void reduceUserAlike(@Param("userId") String userId);
}
