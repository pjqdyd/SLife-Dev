package com.pjqdyd.dao;

import com.pjqdyd.pojo.NewsInfo;
import com.pjqdyd.pojo.vo.NewsInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

}
