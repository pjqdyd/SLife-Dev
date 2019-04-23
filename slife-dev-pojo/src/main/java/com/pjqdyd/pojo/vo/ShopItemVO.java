package com.pjqdyd.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pjqdyd.serialize.CustomerFloatSerialize;
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
     * 店铺评分,默认5.0分,使用自定义序列化,保留一位小数
     */
    @JsonSerialize(using = CustomerFloatSerialize.class)
    private Float rate = 5.0F;

    /**
     * 店铺距离
     */
    private String distance;
}
