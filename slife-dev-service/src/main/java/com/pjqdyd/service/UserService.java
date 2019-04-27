package com.pjqdyd.service;

import com.pjqdyd.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:  [用户的Service接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface UserService {

    /**
     * 通过openId查询用户是否存在
     * @param openId
     * @return
     */
    Boolean isUserExist(String openId);

    /**
     * 通过openId查询用户信息
     * @param openId
     * @return
     */
    User findUserByOpenId(String openId);

    /**
     * 通过用户id来查询用户信息
     * @param userId
     * @return
     */
    User findUserByUserId(String userId);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 通过openId和access_token来验证和保存用户信息
     * @param openId
     * @param access_token
     * @return
     */
    User verifyUserInfoAndSaveInfo(String openId, String access_token);

    /**
     * 用户更新用户信息
     * @param files 新头像
     * @param userId 用户id
     * @param nickname 昵称
     * @param sex 性别
     * @return
     */
    User updateUserInfo(List<MultipartFile> files, String userId, String nickname, Integer sex);

}
