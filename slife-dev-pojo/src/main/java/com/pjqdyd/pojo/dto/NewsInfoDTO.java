package com.pjqdyd.pojo.dto;

import lombok.Data;

/**   
 * @Description:  [动态数据传输对象, 用来接收前端发布动态的参数]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
public class NewsInfoDTO {

    private String publisherId;

    private String content;

    private String newsCategory = "生活街";

    private Double newsLatitude = 0.000;

    private Double newsLongitude = 0.000;

    private String newsShopId;

    private String newsShopName;

    private String newsShopAddr;

    private Integer newsShopScore;

}
