package com.pjqdyd.dao;

import com.pjqdyd.pojo.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 *    
 *
 * @Description:  [店铺简单实体类的repository]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */


public interface ShopRepository extends JpaRepository<Shop, String> {

    //通过店铺id查询简要信息的方法
    Shop findByShopId(String shopId);

    //根据用户的经度区间and纬度区间查询附近的店铺, 并按评分排序
    Page<Shop> findAllByShopLatitudeBetweenAndShopLongitudeBetweenAndShopStatusEquals(
            Double minLat, Double maxLat, Double minLot, Double maxLot, Integer shopStatus, Pageable pageable);


    /**
     * 使用sql来查询附近10公里的模糊分类的店铺
     * @param latitude
     * @param longitude
     * @param category
     * @param shopStatus
     * @return
     */
    @Query(value = "select * FROM tb_shop " +
            "where " +
            "ROUND(6378.137*2*ASIN(SQRT(POW(SIN((:latitude*PI()/180-shop_latitude*PI()/180)/2),2)\n" +
            "+COS(:latitude*PI()/180)*COS(shop_latitude*PI()/180)*POW(SIN((:longitude*PI()" +
            "/180-shop_longitude*PI()/180)/2),2)))*1000) <= 10000 " +
            "and " +
            "shop_status = :shopStatus " +
            "and " +
            "shop_category like CONCAT('%',:category,'%') or shop_name like CONCAT('%',:category,'%')",
            nativeQuery = true)
    List<Shop> findAllByLocalAndLikeCategory(@Param("latitude") Double latitude,
                                             @Param("longitude") Double longitude,
                                             @Param("category") String category,
                                             @Param("shopStatus") Integer shopStatus);


    /**
     * 使用sql来查询附近10公里的所有店铺, 不分类
     * @param latitude
     * @param longitude
     * @param shopStatus
     * @return
     */
    @Query(value = "select * FROM tb_shop " +
            "where " +
            "ROUND(6378.137*2*ASIN(SQRT(POW(SIN((:latitude*PI()/180-shop_latitude*PI()/180)/2),2)\n" +
            "+COS(:latitude*PI()/180)*COS(shop_latitude*PI()/180)*POW(SIN((:longitude*PI()" +
            "/180-shop_longitude*PI()/180)/2),2)))*1000) <= 10000 " +
            "and " +
            "shop_status = :shopStatus", nativeQuery = true)
    List<Shop> findAllByLocal(@Param("latitude") Double latitude,
                                             @Param("longitude") Double longitude,
                                             @Param("shopStatus") Integer shopStatus);

    /**
     * 更新店铺的评分
     * @param newScore 用户给店铺的评分
     * @param shopId 店铺Id
     */
    @Modifying
    @Transactional
    @Query(value = "update tb_shop set rate = (Round((rate + :newScore)/2, 1)) where shop_id=:shopId", nativeQuery = true)
    void updateShopScore(@Param("newScore") Integer newScore,
                         @Param("shopId") String shopId);


}
