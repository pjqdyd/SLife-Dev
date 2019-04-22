package com.pjqdyd.dao;

import com.pjqdyd.pojo.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [店铺简单实体类的repository]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */


public interface ShopRepository extends JpaRepository<Shop, String> {

    //通过店铺id查询简要信息的方法
    Shop findByShopId(String shopId);

    //根据用户的经度区间and纬度区间查询附近的店铺, 并按评分排序
    Page<Shop> findAllByShopLatitudeBetweenAndShopLongitudeBetweenAndShopStatusEquals(
            Double minLat, Double maxLat,  Double minLot, Double maxLot, Integer shopStatus, Pageable pageable);


}
