package com.pjqdyd.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Description:  [动态的实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Entity
@DynamicInsert
@Table(name = "tb_news_info")
public class NewsInfo {

    /**
     * 动态id
     */
    @Id
    private String newsId;

    /**
     * 发布者id
     */

    @Column(columnDefinition = "varchar(50) COMMENT '动态发布者id'")
    private String publisherId;

    /**
     * 动态的内容描述标题
     */
    @Column(columnDefinition = "varchar(256) COMMENT '动态的内容描述标题'")
    private String content;

    /**
     * 动态分类,默认分类生活街
     */
    @Column(length = 12, nullable = false)
    private String newsCategory = "生活街";

    /**
     * 动态纬度
     */
    @Column(columnDefinition = "double(18,14) default 0")
    private Double newsLatitude = 0.000;

    /**
     * 动态经度
     */
    @Column(columnDefinition = "double(18,14) default 0")
    private Double newsLongitude = 0.000;

    /**
     * 动态的点赞数
     */
    @Column(columnDefinition = "int default 0 COMMENT '动态的点赞数'")
    private Integer newsLikeCounts = 0;

    /**
     * 动态的评论数
     */
    @Column(columnDefinition = "int default 0 COMMENT '动态的评论数'")
    private Integer newsCommentCounts = 0;

    /**
     * 动态有关的店铺id
     */
    @Column(columnDefinition = "varchar(50) COMMENT '动态有关的店铺id'")
    private String newsShopId;

    /**
     * 动态有关的店铺名称
     */
    private String newsShopName;

    /**
     * 动态有关店铺的地址
     */
    private String newsShopAddr;

    /**
     * 动态发布的时间
     */
    @CreationTimestamp
    @ApiModelProperty(hidden = true) //可以不填
    private Date createDate;

}
