package com.pjqdyd.pojo;

import lombok.Data;

import javax.persistence.*;

/**   
 * @Description:  [用户点赞动态, 用来标记用户点赞动态的中间表]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Entity
@Table(name = "tb_user_like_news")
@org.hibernate.annotations.Table(appliesTo = "tb_user_like_news",comment = "用户点赞动态的中间表")
public class UserLikeNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String newsId;
}
