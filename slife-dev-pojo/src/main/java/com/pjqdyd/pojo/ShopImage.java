package com.pjqdyd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 图片所属店铺的id
     */
    @JsonIgnore
    @Column(columnDefinition = "varchar(128) not null COMMENT '图片所属的店铺id'")
    private String shopId;

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 多对一,指明要映射的实体类
     * 向ShopImage表中加入外键shopId,并将外键命名为shop_detail_id,形成一对多双向关联
     */
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "shop_detail_id", referencedColumnName = "shopId")
    //已弃用关联(过于复杂)
    //private ShopDetail shopDetail;

}
