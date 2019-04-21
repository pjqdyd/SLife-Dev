package com.pjqdyd.service.impl;

import com.pjqdyd.dao.ShopDetailRepository;
import com.pjqdyd.dao.ShopImageRepository;
import com.pjqdyd.dao.ShopRepository;
import com.pjqdyd.exception.SLifeException;
import com.pjqdyd.pojo.Shop;
import com.pjqdyd.pojo.ShopDetail;
import com.pjqdyd.pojo.ShopImage;
import com.pjqdyd.service.ShopService;
import com.pjqdyd.utils.MultipartFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


/**
 *    
 *
 * @Description:  [店铺service实现类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */
@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    /**
     * 注入店铺简单信息repository接口
     */
    @Autowired
    private ShopRepository shopRepository;

    @Value("${common.slife.fileSpace}")
    private String fileSpace;

    /**
     * 注入店铺详情repository接口
     */
    @Autowired
    private ShopDetailRepository shopDetailRepository;

    /**
     * 注入店铺图片repository接口
     */
    @Autowired
    private ShopImageRepository shopImageRepository;


    /**
     * 保存店铺简介信息
     *
     * @param shop
     * @return
     */
    @Override
    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    /**
     * 根据店铺id查简要信息
     * @param shopId
     * @return
     */
    @Override
    public Shop findByShopId(String shopId) {
        return shopRepository.findByShopId(shopId);
    }

    /**
     * 保存店铺详情的方法
     *
     * @param shopDetail
     * @param files      用户上传的图片文件
     * @return
     */
    @Override
    @Transactional
    public ShopDetail saveShopDetail(ShopDetail shopDetail, List<MultipartFile> files) {

        ShopDetail result = shopDetailRepository.save(shopDetail); //保存店铺详情

        String filePath = "/shop/" + shopDetail.getApplyerId() + "/shopImage"; //店铺图片保存的相对目录
        //保存店铺图片到本地, saveFilePathMap保存后图片相对路径Map集合(key: "图片名", value: "图片相对路径")
        Map<String, String> saveFilePathMap = MultipartFileUtil.saveFileToLocal(files, fileSpace, filePath);
        if (saveFilePathMap.size() == 0){
            log.error("上传的图片为空 saveFilePathMap = {}", saveFilePathMap.toString());
            throw new SLifeException(201, "上传的图片为空");
        }
        for (String imageName : saveFilePathMap.keySet()) {
            ShopImage shopImage = new ShopImage();
            shopImage.setImageUrl(saveFilePathMap.get(imageName)); //设置图片路径
            shopImage.setShopDetail(shopDetail);//设置图片关联的店铺详情
            shopImageRepository.save(shopImage);
        }

        Shop shop = findByShopId(shopDetail.getShopId()); //保存店铺简要信息
        if (shop == null){
            shop = new Shop();
        }
        BeanUtils.copyProperties(shopDetail, shop);
        shop.setImageUrl(saveFilePathMap.get("image0")); //保存第一张图片是店铺的门面
        Shop result1 = shopRepository.save(shop);

        if (result1 == null){
            return null;
        }
        return result;
    }

    /**
     * 根据店铺Id删除图片的方法
     * @param shopId
     */
    @Override
    @Transactional
    public void deleteImagesByShopId(String shopId) {
        shopImageRepository.deleteAllByShopId(shopId);
    }



    /**
     * 根据申请者id查询店铺是否已经存在
     * @param applyerId
     * @return
     */
    @Override
    public Boolean isShopExistByApplyerId(String applyerId) {
        return shopDetailRepository.existsByApplyerId(applyerId);
    }

    /**
     * 根据申请者id查询店铺
     * @param applyerId
     * @return
     */
    @Override
    public ShopDetail findShopByApplyerId(String applyerId) {
        return shopDetailRepository.findByApplyerId(applyerId);
    }

    /**
     * 根据申请者id查询店铺id
     * @param applyerId
     * @return
     */
    @Override
    public String findShopIdByApplyerId(String applyerId) {
        return shopDetailRepository.findShopIdByApplyerId(applyerId);
    }
}
