package com.pjqdyd.service;

import com.pjqdyd.pojo.vo.NewsListVO;
import org.springframework.data.domain.Pageable;


/**   
 * @Description:  [动态列表服务接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface NewsListService {

    /**
     * 查询附近的动态
     * @param minLat
     * @param maxLat
     * @param minLot
     * @param maxLot
     * @param userId
     * @param pageable
     * @return 返回动态列表VO对象
     */
    NewsListVO findAllLocalNewsInfoVO(Double minLat, Double maxLat, Double minLot, Double maxLot, String userId, Pageable pageable);

    /**
     * 通过店铺id查询有关的动态
     * @param newsShopId
     * @param pageable
     * @param userId
     * @return
     */
    NewsListVO findAllNewsByNewsShopId(String newsShopId, String userId, Pageable pageable);

    /**
     * 通过发布者id查询动态
     * @param publisherId
     * @param pageable
     * @param userId
     * @return
     */
    NewsListVO findAllNewsByPublisherId(String publisherId, String userId, Pageable pageable);

}
