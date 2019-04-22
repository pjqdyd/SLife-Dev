package com.pjqdyd.pojo.vo;

import com.pjqdyd.pojo.Shop;
import lombok.Data;

import java.util.List;

/**   
 * @Description:  [返回给前端的店铺详情列表对象]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Data
public class ShopListVO {

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
     * 附近店铺列表
     */
    private List<Shop> localList;

}
