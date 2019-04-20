package com.pjqdyd.dao;

import com.pjqdyd.pojo.ShopDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [店铺详情repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface ShopDetailRepository extends JpaRepository<ShopDetail, String> {
}
