package com.pjqdyd.pojo.dto;

import lombok.Data;


/**   
 * @Description:  [店铺数据传输对象, 用于接收前端传入的店铺申请信息]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
public class ShopDTO {

    private String applyerId;

    private String shopName;

    private String shopLatitude;

    private String shopLongitude;

    private String shopCategory;

    private String mainInfo;

    private String shopAddr;

    private String openTime;

    private String supportServer = ""; //店铺支持的服务字符串数组
}
