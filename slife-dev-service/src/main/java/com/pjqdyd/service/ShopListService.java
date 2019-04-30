package com.pjqdyd.service;

import com.pjqdyd.pojo.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**   
 * @Description:  [店铺简单列表service接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface ShopListService {

    /**
     * 查询附近的商铺列表
     * @param minLat 最小纬度
     * @param maxLat 最大纬度
     * @param minLot 最小经度
     * @param maxLot 最大经度
     * @param shopStatus 店铺的状态
     * @param pageable 分页排序对象
     * @return
     */
    Page<Shop> findLocalShop(Double minLat, Double maxLat,  Double minLot, Double maxLot,Integer shopStatus ,Pageable pageable);

    /**
     * 根据分类名模糊查询附近的店铺
     * @param lat 用户的纬度
     * @param lot 用户的经度
     * @param category 分类名
     * @return
     */
    List<Shop> findAllLocalAndLikeCategory(Double lat, Double lot, String category, Integer shopStatus);

    /**
     * 查询附近的所有店铺(不分类)
     * @param lat 用户的纬度
     * @param lot 用户的经度
     * @param shopStatus 店铺的状态
     * @return
     */
    List<Shop> findAllLocaly(Double lat, Double lot, Integer shopStatus);



}
