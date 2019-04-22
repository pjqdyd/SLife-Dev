package com.pjqdyd.dao;


import com.pjqdyd.pojo.ShopImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *    
 *
 * @Description:  [店铺图片的repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

public interface ShopImageRepository extends JpaRepository<ShopImage, Integer> {

    /**
     * 自定义根据店铺id删除图片的方法
     * @param shopId
     */
    @Modifying
    @Query(value = "delete from tb_shop_image where shop_id=?1", nativeQuery = true)
    void removeAllByShopId(String shopId);

    /**
     * 根据店铺id删除图片的方法
     * @param shopId
     * @return
     */
    List<ShopImage> deleteAllByShopId(String shopId);

    /**
     * 通过店铺id查找所有图片的方法
     * @param shopId
     * @return
     */
    List<ShopImage> findAllByShopId(String shopId);
}
