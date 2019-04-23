package com.pjqdyd.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @Description:  [店铺的简单实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */


@Data
@Entity
@Table(name = "tb_shop")
public class Shop {

    /**
     * 店铺id 主键
     */
    @Id
    private String shopId;

    /**
     * 店铺名称
     */
    @Column(length = 20, nullable = false)
    private String shopName;

    /**
     * 店铺的门面图片
     */
    @Column(columnDefinition = "varchar(256) COMMENT '店铺的门面图片'")
    private String imageUrl;

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
    @Column(nullable =false, length = 60)
    private String shopAddr;

    /**
     * 店铺分类
     */
    @Column(columnDefinition = "varchar(20) COMMENT '店铺分类'")
    private String shopCategory;

    /**
     * 店铺评分,默认5分
     */
    @Column(columnDefinition = "varchar(4) default 5.0 COMMENT '店铺评分'")
    private String rate = "5.0";

    /**
     * 店铺的状态
     */
    @Column(columnDefinition = "tinyint default 0 COMMENT '店铺状态 0审核中, 1审核通过, 2不通过'")
    private Integer shopStatus = 0;
}
