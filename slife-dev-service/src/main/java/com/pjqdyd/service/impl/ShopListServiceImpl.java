package com.pjqdyd.service.impl;

import com.pjqdyd.dao.ShopRepository;
import com.pjqdyd.pojo.Shop;
import com.pjqdyd.service.ShopListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**   
 * @Description:  [店铺列表service层实现类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
@Service
public class ShopListServiceImpl implements ShopListService {

    @Autowired
    private ShopRepository shopRepository;

    /**
     * 查询附近的商铺信息
     * @param minLat 最小纬度
     * @param maxLat 最大纬度
     * @param minLot 最小经度
     * @param maxLot 最大经度
     * @param shopStatus 店铺的状态
     * @param pageable 分页排序对象
     * @return
     */
    @Override
    public Page<Shop> findLocalShop(Double minLat, Double maxLat, Double minLot, Double maxLot, Integer shopStatus ,Pageable pageable) {
        return shopRepository.findAllByShopLatitudeBetweenAndShopLongitudeBetweenAndShopStatusEquals(minLat,maxLat,minLot,maxLot,shopStatus,pageable);
    }
}
