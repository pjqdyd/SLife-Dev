package com.pjqdyd.service;

import com.pjqdyd.pojo.Shop;
import com.pjqdyd.pojo.ShopDetail;
import com.pjqdyd.pojo.ShopImage;
import com.pjqdyd.pojo.vo.ShopDetailVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**   
 * @Description:  [店铺service层接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface ShopService {

    /**
     * 保存店铺简要信息
     * @param shop
     * @return
     */
    Shop saveShop (Shop shop);


    /**
     * 通过shopId查询店铺简要信息
     * @param shopId
     * @return
     */
    Shop findByShopId(String shopId);

    /**
     * 保存店铺详情信息
     * @param shopDetail
     * @param files
     * @return
     */
    ShopDetail saveShopDetail(ShopDetail shopDetail, List<MultipartFile> files);

    /**
     * 根据店铺Id删除所有图片
     * @param shopId
     */
    void deleteImagesByShopId(String shopId);

    /**
     * 根据店铺Id查询所有图片
     * @param shopId
     * @return
     */
    List<ShopImage> findAllImageByShopId(String shopId);

    /**
     * 根据申请者id查询店铺
     * @param applyerId
     * @return
     */
    ShopDetail findShopByApplyerId(String applyerId);

    /**
     * 根据申请者Id查询店铺Id
     * @param applyerId
     * @return
     */
    String findShopIdByApplyerId(String applyerId);

    /**
     * 根据申请者id判断是否已经存在申请的店铺
     * @param applyerId
     * @return
     */
    Boolean isShopExistByApplyerId(String applyerId);

    /**
     * 根据店铺id查询店铺详情VO对象
     * @param shopId
     * @return
     */
    ShopDetailVO findShopDetailVOByShopId(String shopId);
}
