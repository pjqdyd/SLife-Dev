package com.pjqdyd.pojo.vo;

import lombok.Data;

/**   
 * @Description:  [返回给前端的店铺简介元素]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
public class ShopItemVO {

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺的门面图片
     */
    private String imageUrl;

    /**
     * 店铺的位置
     */
    private String shopAddr;

    /**
     * 店铺分类
     */
    private String shopCategory;

    /**
     * 店铺评分,默认5分
     */
    private Integer rate = 5;

    /**
     * 店铺距离
     */
    private String distance;
}
