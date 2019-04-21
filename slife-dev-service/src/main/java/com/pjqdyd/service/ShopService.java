package com.pjqdyd.service;

import com.pjqdyd.pojo.Shop;
import com.pjqdyd.pojo.ShopDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**   
 * @Description:  [店铺service层接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface ShopService {

    //保存店铺简要信息
    Shop saveShop (Shop shop);

    //通过shopId查询店铺简要信息
    Shop findByShopId(String shopId);

    //保存店铺详情信息
    ShopDetail saveShopDetail(ShopDetail shopDetail, List<MultipartFile> files);

    //根据店铺Id删除所有图片
    void deleteImagesByShopId(String shopId);

    //根据申请者id查询店铺
    ShopDetail findShopByApplyerId(String applyerId);

    //根据申请者Id查询店铺Id
    String findShopIdByApplyerId(String applyerId);

    //根据申请者id判断是否已经存在申请的店铺
    Boolean isShopExistByApplyerId(String applyerId);

}
