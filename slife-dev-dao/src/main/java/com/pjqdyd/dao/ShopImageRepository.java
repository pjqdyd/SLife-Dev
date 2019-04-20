package com.pjqdyd.dao;


import com.pjqdyd.pojo.ShopImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [店铺图片的repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface ShopImageRepository extends JpaRepository<ShopImage, Integer> {
}
