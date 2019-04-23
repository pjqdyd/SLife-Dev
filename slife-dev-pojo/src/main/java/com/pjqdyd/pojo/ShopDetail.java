package com.pjqdyd.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 *    
 *
 * @Description:  [店铺详情实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

@Data
@Entity
@DynamicInsert
@Table(name = "tb_shop_detail")
public class ShopDetail {

    /**
     * 店铺id 主键
     */
    @Id
    private String shopId;

    /**
     * 申请者Id, 店主
     */
    @Column(unique = true, nullable = false)
    private String applyerId;

    /**
     * 店铺名称
     */
    @Column(length = 20, nullable = false)
    private String shopName;

    /**
     * 店铺和店铺的图片是一对多(mappedBy放弃维护外键,交给ShopImage的shopDetail管理外键; cascade级联保存、更新、删除、刷新;  fetch延迟加载)
     */
    //已弃用关联, jpa多表关联操作并不方便
    //@OneToMany(mappedBy = "shopDetail",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private Set<ShopImage> shopImages = new HashSet<>();

    /**
     * 店铺纬度
     */
    @Column(columnDefinition = "double(18,14) default 0")
    private Double shopLatitude = 0.000;

    /**
     * 店铺经度
     */
    @Column(columnDefinition = "double(18,14) default 0")
    private Double shopLongitude = 0.000;

    /**
     * 店铺的位置
     */
    @Column(nullable = false, length = 60)
    private String shopAddr;

    /**
     * 店铺分类
     */
    @Column(columnDefinition = "varchar(20) COMMENT '店铺分类'")
    private String shopCategory;

    /**
     * 主营信息
     */
    @Column(columnDefinition = "varchar(256) COMMENT '主营信息'")
    private String mainInfo;

    /**
     * 营业时间
     */
    @Column(columnDefinition = "varchar(50) COMMENT '营业时间'")
    private String openTime;

    /**
     * 店铺评分,默认5分
     */
    @Column(columnDefinition = "varchar(4) default 5.0 COMMENT '店铺评分'")
    private String rate = "5.0";


    /**
     * 是否支持wifi
     */
    @Column(columnDefinition = "boolean default false COMMENT '是否支持wifi'")
    private Boolean wifi = false;

    /**
     * 是否支持现金
     */
    @Column(columnDefinition = "boolean default false COMMENT '是否支持现金'")
    private Boolean cash = false;

    /**
     * 是否支持支付宝
     */
    @Column(columnDefinition = "boolean default false COMMENT '是否支持支付宝'")
    private Boolean alipay = false;

    /**
     * 是否支持微信支付
     */
    @Column(columnDefinition = "boolean default false COMMENT '是否支持微信支付'")
    private Boolean wechatpay = false;

    /**
     * 店铺创建日期
     */
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(hidden = true) //可以不填
    private Date createDate;

    /**
     * 店铺的状态
     */
    @Size(min = 0, max = 2)
    @Column(columnDefinition = "tinyint default 0 COMMENT '店铺状态 0审核中, 1审核通过, 2不通过'")
    private Integer shopStatus = 0;

}
