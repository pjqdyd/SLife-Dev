package com.pjqdyd.pojo.vo;

import lombok.Data;

import java.util.List;

/**   
 * @Description:  [返回给前端的动态列表对象]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
public class NewsListVO {

    /**
     * 页数
     */
    private Integer page;

    /**
     * 总元素数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 附近动态列表
     */
    private List<NewsInfoVO> newsList;

}
