package com.pjqdyd.dao;

import com.pjqdyd.pojo.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [店铺简单实体类的repository]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */


public interface ShopRepository extends JpaRepository<Shop, String> {

    //通过店铺id查询简要信息的方法
    Shop findByShopId(String shopId);

}
