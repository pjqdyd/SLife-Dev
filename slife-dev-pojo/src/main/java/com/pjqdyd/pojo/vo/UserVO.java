package com.pjqdyd.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *    
 *
 * @Description:  [用户信息返回给前端的VO对象]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */


@Data
public class UserVO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户登录状态token令牌
     */
    private String userToken;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户的身份 0是顾客(默认), 1是店主
     */
    private Integer idStatus;

    /**
     * 用户性别 1表示男, 2表示女, 0表示未设置(默认)
     */
    private Integer sex;

    /**
     * 粉丝数量
     */
    private Integer fansCounts;

    /**
     * 关注数量
     */
    private Integer followCounts;

    /**
     * 发布动态的数量
     */
    private Integer createCounts;

    /**
     * 获赞数
     */
    @JsonProperty(value = "likeCounts")
    private Integer receiveLikeCounts;

    /**
     * 用户的头像
     */
    private String faceImage;

    /**
     * 是否关注
     */
    private Boolean isFollow = false;

}
