package com.pjqdyd.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
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
     * 用户身份 0是顾客(默认), 1是店主
     *
     */
    @Column(columnDefinition = "tinyint default 0 COMMENT '用户身份 0顾客, 1店主'")
    private Integer idStatus = 0;

    /**
     * 用户性别 1表示男, 2表示女, 0表示未设置(默认)
     */
    @Column(columnDefinition = "tinyint default 0 COMMENT '性别 1男, 2女, 0未设置'")
    private Integer sex = 0;

    /**
     * 用户的头像
     */
    private String faceImage;

    /**
     * 经度
     */
    @Column(columnDefinition = "double(18,14) default 0")
    private Double longitude = 0.000;

    /**
     * 纬度
     */
    @Column(columnDefinition = "double(18,14) default 0")
    private Double latitude = 0.000;

    /**
     * 发布动态的数量
     */
    @Column(columnDefinition = "int default 0 COMMENT '发布动态的数量'")
    private Integer createCounts = 0;

    /**
     * 粉丝数量
     */
    @Column(columnDefinition = "int default 0")
    private Integer fansCounts = 0;

    /**
     * 关注数量
     */
    @Column(columnDefinition = "int default 0")
    private Integer followCounts = 0;

    /**
     * 获赞数
     */
    @Column(columnDefinition = "int default 0 COMMENT '获赞数'")
    private Integer receiveLikeCounts = 0;

    /**
     * 用户创建日期
     */
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(hidden = true) //可以不填
    private Date createDate;
}
