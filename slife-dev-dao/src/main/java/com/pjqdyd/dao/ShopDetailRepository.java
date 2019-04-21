package com.pjqdyd.dao;

import com.pjqdyd.pojo.ShopDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

}
