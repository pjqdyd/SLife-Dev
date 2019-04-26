package com.pjqdyd.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;;
import com.pjqdyd.pojo.ShopDetail;
import com.pjqdyd.pojo.ShopImage;
import com.pjqdyd.serialize.CustomerFloatSerialize;
import lombok.Data;

import java.util.List;

/**   
 * @Description:  [返回给前端的店铺详情对象]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
public class ShopDetailVO {

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 申请者Id, 店主
     */
    private String applyerId;

    /**
     * 店主昵称
     */
    private String nickname;

    /**
     * 店主头像
     */
    private String faceImage;

    /**
     * 店铺的图片列表
     */
    private List<ShopImage> shopImageList;

    /**
     * 店铺纬度
     */
    private Double shopLatitude = 0.000;

    /**
     * 店铺经度
     */
    private Double shopLongitude = 0.000;

    /**
     * 店铺的位置
     */
    private String shopAddr;

    /**
     * 店铺分类
     */
    private String shopCategory;

    /**
     * 主营信息
     */
    private String mainInfo;

    /**
     * 营业时间
     */
    private String openTime;

    /**
     * 店铺评分,默认5分,使用自定义JSON序列化,保留一位小数
     */
    @JsonSerialize(using = CustomerFloatSerialize.class)
    private Float rate = 5.0F;

    /**
     * 是否支持wifi
     */
    private Boolean wifi = false;

    /**
     * 是否支持现金
     */
    private Boolean cash = false;

    /**
     * 是否支持支付宝
     */
    private Boolean alipay = false;

    /**
     * 是否支持微信支付
     */
    private Boolean wechatpay = false;


    //关键
    //自定义构造器,用于在ShopDetailRepository的@Query中创建查询返回的对象,new com.pjqdyd.pojo.vo.ShopDetailVO(sd, u.nickname ,u.faceImage)
    public ShopDetailVO(ShopDetail shopDetail, String nickname, String faceImage){

        this.shopId = shopDetail.getShopId();
        this.shopName = shopDetail.getShopName();
        this.applyerId = shopDetail.getApplyerId();
        this.nickname = nickname;
        this.faceImage = faceImage;
        this.shopLatitude = shopDetail.getShopLatitude();
        this.shopLongitude = shopDetail.getShopLongitude();
        this.shopAddr = shopDetail.getShopAddr();
        this.shopCategory = shopDetail.getShopCategory();
        this.mainInfo = shopDetail.getMainInfo();
        this.openTime = shopDetail.getOpenTime();
        this.rate = shopDetail.getRate();
        this.wifi = shopDetail.getWifi();
        this.cash = shopDetail.getCash();
        this.alipay = shopDetail.getAlipay();
        this.wechatpay = shopDetail.getWechatpay();

    }
}
