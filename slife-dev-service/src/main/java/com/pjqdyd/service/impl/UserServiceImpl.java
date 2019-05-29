package com.pjqdyd.service.impl;

import com.pjqdyd.dao.UserRepository;
import com.pjqdyd.pojo.User;
import com.pjqdyd.service.UserService;
import com.pjqdyd.utils.DownloadResource;
import com.pjqdyd.utils.MultipartFileUtil;
import com.pjqdyd.utils.UniqueId;
import com.pjqdyd.utils.VerifyUserOpenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *    
 *
 * @Description:  [用户Service实现类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${common.slife.qqAppId}")
    private String appId;

    @Value("${common.slife.qqVerifyUrl}")
    private String verifyUrl;

    @Value("${common.slife.fileSpace}")
    private String fileSpace;

    /**
     * 保存用户信息
     *
     * @param user
     * @return User
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 通过openId判断用户是否存在
     *
     * @param openId
     * @return true/false
     */
    @Override
    public Boolean isUserExist(String openId) {
        return userRepository.existsByOpenId(openId);
    }

    /**
     * 通过openId查询用户信息
     *
     * @param openId
     * @return User
     */
    @Override
    public User findUserByOpenId(String openId) {
        return userRepository.findByOpenId(openId);
    }

    /**
     * 通过用户id来查询用户信息
     * @param userId
     * @return
     */
    @Override
    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    /**
     * 验证用户信息和保存用户信息
     *
     * @param openId
     * @param access_token
     * @return
     */
    @Override
    @Transactional
    public User verifyUserInfoAndSaveInfo(String openId, String access_token) {

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("openid", openId);
        paramsMap.put("access_token", access_token);
        paramsMap.put("oauth_consumer_key", appId);

        Map<String, String> resultMap = new VerifyUserOpenId().verifyByOpenId(verifyUrl, paramsMap);

        if (resultMap.get("ret").equals("0")) { //返回结果ret=0,表明认证成功,保存用户信息

            User user = new User();
            String userId = UniqueId.getNewId("u-");
            user.setUserId(userId);
            user.setOpenId(openId);
            user.setNickname(resultMap.get("nickname"));

            //下载用户头像到本地
            DownloadResource download = new DownloadResource();
            String url = resultMap.get("figureurl_qq"); //qq头像网络路径
            String filePath = "/user/" + userId + "/faceImage"; //头像保存的相对路径
            String faceUrl = download.downloadResource(url, fileSpace, filePath, "qqFaceImage.png");
            user.setFaceImage(faceUrl);

            //获取用户性别
            String sex = resultMap.get("gender");
            user.setSex(sex.equals("男") ? 1 : (sex.equals("女") ? 2 : 3));

            return userRepository.save(user);
        }
        return null;
    }

    /**
     * 用户更新用户信息
     * @param files 新头像
     * @param userId 用户id
     * @param nickname 昵称
     * @param sex 性别
     * @return
     */
    @Override
    @Transactional
    public User updateUserInfo(List<MultipartFile> files, String userId, String nickname, Integer sex) {

        User user = userRepository.findByUserId(userId);
        if (user == null){
            return null;
        }
        user.setNickname(nickname);
        user.setSex(sex);


        String filePath = "/user/" + userId + "/faceImage"; //头像保存的相对路径
        //保存新头像图片到本地, saveFilePathMap保存后图片相对路径Map集合(key: "图片名", value: "图片相对路径")
        Map<String, String> saveFilePathMap = MultipartFileUtil.saveFileToLocal(files, fileSpace, filePath);

        user.setFaceImage(saveFilePathMap.get("file")); //根据图片名file 拿到图片保存的相对路径

        return userRepository.save(user); //更新用户信息
    }
}
