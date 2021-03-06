package com.pjqdyd.dao;

import com.pjqdyd.pojo.NewsInfo;
import com.pjqdyd.pojo.vo.NewsInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description:  [动态repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface NewsInfoRepository extends JpaRepository<NewsInfo, String> {

    /**
     * 查询返回给前端的附近的动态VO
     * @param minLat 纬度区间
     * @param maxLat 纬度区间
     * @param minLot 经度区间
     * @param maxLot 经度区间
     * @param pageable
     * @return
     */
    @Query(value = "select new com.pjqdyd.pojo.vo.NewsInfoVO(user, newsInfo) " +
            "from NewsInfo newsInfo, User user " +
            "where newsInfo.newsLatitude between :minLat and :maxLat " +
            "and newsInfo.newsLongitude between :minLot and :maxLot " +
            "and newsInfo.publisherId=user.userId")
    Page<NewsInfoVO> findLocalAllNewsInfoVO(@Param("minLat") Double minLat,
                                          @Param("maxLat") Double maxLat,
                                          @Param("minLot") Double minLot,
                                          @Param("maxLot") Double maxLot, Pageable pageable);

    /**
     * 根据店铺id查询与该店铺有关的动态
     * @param newsShopId
     * @param pageable
     * @return
     */
    @Query(value = "select new com.pjqdyd.pojo.vo.NewsInfoVO(user, newsInfo) " +
            "from NewsInfo newsInfo, User user " +
            "where newsInfo.newsShopId=:newsShopId and newsInfo.publisherId=user.userId")
    Page<NewsInfoVO> findAllByNewsShopId(@Param("newsShopId") String newsShopId, Pageable pageable);


    /**
     * 根据发布者id查询动态
     * @param publisherId
     * @param pageable
     * @return
     */
    @Query(value = "select new com.pjqdyd.pojo.vo.NewsInfoVO(user, newsInfo) " +
            "from NewsInfo newsInfo, User user " +
            "where newsInfo.publisherId=:publisherId and user.userId=:publisherId")
    Page<NewsInfoVO> findAllByPublisherId(@Param("publisherId") String publisherId, Pageable pageable);


    /**根据好友id查询好友点赞的动态,
     *
     * 先从UserLikeNews中间表根据:friendId查出该好友已点赞的动态id数据,
     * 根据查出的已点赞动态id去NewsInfo表中查询动态, 最后再查动态发布者的信息
     * @param friendId
     * @param pageable
     * @return
     */
    @Query(value = "select new com.pjqdyd.pojo.vo.NewsInfoVO(user, newsInfo) " +
            "from UserLikeNews ulikeNews, NewsInfo newsInfo, User user " +
            "where ulikeNews.userId=:friendId and newsInfo.newsId=ulikeNews.newsId " +
            "and user.userId=newsInfo.publisherId")
    Page<NewsInfoVO> findAllLikeNewsByfriendId(@Param("friendId") String friendId, Pageable pageable);

    /**
     * 给动态添加一个赞
     * @param newsId
     */
    @Transactional
    @Modifying
    @Query(value = "update tb_news_info set news_like_counts=news_like_counts+1 where news_id=:newsId", nativeQuery = true)
    void addLikeToNews(@Param("newsId") String newsId);

    /**
     * 给动态减少一个赞
     * @param newsId
     */
    @Transactional
    @Modifying
    @Query(value = "update tb_news_info set news_like_counts=news_like_counts-1 where news_id=:newsId", nativeQuery = true)
    void reduceLikeToNews(@Param("newsId") String newsId);
}
