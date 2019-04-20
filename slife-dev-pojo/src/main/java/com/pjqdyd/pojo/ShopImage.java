package com.pjqdyd.pojo;

import lombok.Data;

import javax.persistence.*;

/**   
 * @Description:  [店铺图片实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Entity
@Table(name = "tb_shop_image")
public class ShopImage {

    /**
     * 图片id 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 多对一,指明要映射的实体类
     * 向ShopImage表中加入外键shopId,并将外键命名为shop_detail_id,形成一对多双向关联
     */
    @ManyToOne
    @JoinColumn(name = "shop_detail_id", referencedColumnName = "shopId")
    private ShopDetail shopDetail;

    /**
     * 图片路径
     */
    private String imageUrl;

}
