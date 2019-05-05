package com.pjqdyd.pojo.vo;

import com.pjqdyd.pojo.NewsImage;
import com.pjqdyd.pojo.NewsInfo;
import com.pjqdyd.pojo.User;
import com.pjqdyd.utils.TimeAgoUtils;
import lombok.Data;

import java.util.List;

/**   
 * @Description:  [返回给前端的动态对象]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Data
public class NewsInfoVO {

    /**
     * 动态id
     */
    private String newsId;

    /**
     * 发布者id
     */
    private String publisherId;

    /**
     * 发布者昵称
     */
    private String nickname;

    /**
     * 发布者的性别
     */
    private Integer sex;

    /**
     * 发布者的头像
     */
    private String faceUrl;

    /**
     * 发布者的身份, 0表示顾客, 1表示店主
     */
    private Integer idStatus;

    /**
     * 动态的内容描述标题
     */
    private String content;

    /**
     * 动态分类,默认分类生活街
     */
    private String newsCategory;

    /**
     * 动态纬度
     */
    private Double newsLatitude;

    /**
     * 动态经度
     */
    private Double newsLongitude;

    /**
     * 动态的点赞数
     */
    private Integer newsLikeCounts;

    /**
     * 动态的评论数
     */
    private Integer newsCommentCounts;

    /**
     * 动态有关的店铺id
     */
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
    private String dateTime;

    /**
     * 是否点赞, 0表示未点赞, 1表示已点赞
     */
    private Integer isLike = 0;

    /**
     * 动态的图片
     */
    private List<NewsImage> newsImageList;


    //自定义构造器, 用于在NewsInfoRepository中的@Query创建自定义查询返回对象
    public NewsInfoVO(User user, NewsInfo newsInfo){
        this.newsId = newsInfo.getNewsId();
        this.publisherId = newsInfo.getPublisherId();
        this.nickname = user.getNickname();
        this.sex = user.getSex();
        this.idStatus = user.getIdStatus();
        this.faceUrl = user.getFaceImage();
        this.content = newsInfo.getContent();
        this.newsCategory = newsInfo.getNewsCategory();
        this.newsLatitude = newsInfo.getNewsLatitude();
        this.newsLongitude = newsInfo.getNewsLongitude();
        this.newsLikeCounts = newsInfo.getNewsLikeCounts();
        this.newsCommentCounts = newsInfo.getNewsCommentCounts();
        this.newsShopId = newsInfo.getNewsShopId();
        this.newsShopName = newsInfo.getNewsShopName();
        this.newsShopAddr = newsInfo.getNewsShopAddr();
        this.dateTime = TimeAgoUtils.format(newsInfo.getCreateDate()); //转换一下日期的表达方式
    }

}
