package com.pjqdyd.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Description:  [用户实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */


@Data
@Entity
@Table(name = "tb_user")
@DynamicInsert
@ApiModel(value = "用户对象", description = "用户信息对象")
public class User {

    /**
     * Id 主键
     */
    @ApiModelProperty(hidden = true) //可以不填
    @Id
    private String userId;

    /**
     * 用户的唯一openId
     */
    @Column(unique = true, nullable = false, length = 60)
    private String openId;

    /**
     * 用户的昵称
     */
    @Column(length = 20)
    private String nickname;

    /**
     * 用户身份 0是顾客, 1是店主
     */
    @Column(nullable = false, columnDefinition = "tinyint default 0 COMMENT '用户身份 0顾客, 1店主'")
    private Integer idStatus;

    /**
     * 用户性别 1表示男, 2表示女, 0表示未设置(默认)
     */
    @Column(nullable = false, columnDefinition = "tinyint default 0 COMMENT '性别 1男, 2女, 3未设置'")
    private Integer sex;

    /**
     * 用户的头像
     */
    private String faceImage;

    /**
     * 经度
     */
    @Column(columnDefinition = "double(12,8)")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(columnDefinition = "double(12,8)")
    private Double latitude;

    /**
     * 发布动态的数量
     */
    @Column(nullable = false, columnDefinition = "int default 0 COMMENT '发布动态的数量'")
    private Integer createCounts;

    /**
     * 粉丝数量
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer fansCounts;

    /**
     * 关注数量
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer followCounts;

    /**
     * 获赞数
     */
    @Column(nullable = false, columnDefinition = "int default 0 COMMENT '获赞数'")
    private Integer receiveLikeCounts;

    /**
     * 用户创建日期
     */
    @Column(columnDefinition = "COMMENT '用户创建日期'")
    private Date createDate;
}
