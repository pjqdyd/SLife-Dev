package com.pjqdyd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**   
 * @Description:  [动态的图片实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Entity
@Table(name = "tb_news_image")
public class NewsImage {


    /**
     * 动态图片id, 自增
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 动态图片所属的动态id
     */
    @JsonIgnore
    private String newsId;

    /**
     * 动态图片路径
     */
    private String imageUrl;
}
