package com.pjqdyd.dao;

import com.pjqdyd.pojo.ShopDetail;
import com.pjqdyd.pojo.vo.ShopDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**   
 * @Description:  [店铺详情repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface ShopDetailRepository extends JpaRepository<ShopDetail, String> {

    /**
     * 根据applyerId判断店铺是否存在
     */
    Boolean existsByApplyerId(String applyerId);

    /**
     * 根据申请者Id查询店铺id
     */
    @Query(value = "select shop_id from tb_shop_detail sd where sd.applyer_id = ?1", nativeQuery = true)
    String findShopIdByApplyerId(String applyerId);

    /**
     * 根据申请者Id查询店铺
     */
    ShopDetail findByApplyerId(String applyerId);

    /**
     * 根据店铺id查询店铺详情信息VO,用于返回给前端
     * @return
     */
    @Query(value = "select new com.pjqdyd.pojo.vo.ShopDetailVO(sd, u.nickname ,u.faceImage) " +
            "from ShopDetail sd, User u " +
            "where sd.shopId=:shopId and sd.applyerId=u.userId")
    ShopDetailVO findShopDetailVOByShopId(@Param("shopId") String shopId);

    /**
     * 根据店主id查询店铺详情信息VO,用于返回给前端
     * @return
     */
    @Query(value = "select new com.pjqdyd.pojo.vo.ShopDetailVO(sd, u.nickname ,u.faceImage) " +
            "from ShopDetail sd, User u " +
            "where sd.applyerId=:shoperId and u.userId=:shoperId")
    ShopDetailVO findShopDetailVOByShoperId(@Param("shoperId") String shoperId);

    /**
     * 更新店铺的评分
     * @param newScore 用户给店铺的评分
     * @param shopId 店铺Id
     */
    @Modifying
    @Transactional
    @Query(value = "update tb_shop_detail set rate = (Round((rate + :newScore)/2, 1)) where shop_id=:shopId", nativeQuery = true)
    void updateShopScore(@Param("newScore") Integer newScore,
                         @Param("shopId") String shopId);

}
