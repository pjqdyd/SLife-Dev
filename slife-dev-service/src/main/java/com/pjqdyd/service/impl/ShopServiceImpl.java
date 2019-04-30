package com.pjqdyd.service.impl;

import com.pjqdyd.dao.ShopDetailRepository;
import com.pjqdyd.dao.ShopImageRepository;
import com.pjqdyd.dao.ShopRepository;
import com.pjqdyd.exception.SLifeException;
import com.pjqdyd.pojo.Shop;
import com.pjqdyd.pojo.ShopDetail;
import com.pjqdyd.pojo.ShopImage;
import com.pjqdyd.pojo.vo.ShopDetailVO;
import com.pjqdyd.service.ShopService;
import com.pjqdyd.utils.DeleteFileUtil;
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

    /**
     * 文件命名空间
     */
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
     * @param shopDetail
     * @param files 用户上传的图片文件
     * @return
     */
    @Override
    @Transactional
    public ShopDetail saveShopDetail(ShopDetail shopDetail, List<MultipartFile> files) {

        ShopDetail result = shopDetailRepository.save(shopDetail); //保存店铺详情

        //先将以前的图片图片清空
        List<ShopImage> shopImages = shopImageRepository.deleteAllByShopId(shopDetail.getShopId());
        if(shopImages.size() > 0){
            for (ShopImage shopImage: shopImages) {
                DeleteFileUtil.deleteLocalFile(fileSpace + shopImage.getImageUrl());
            }
        }

        String filePath = "/shop/" + shopDetail.getApplyerId() + "/shopImage"; //新店铺图片保存的相对目录
        //保存店铺图片到本地, saveFilePathMap保存后图片相对路径Map集合(key: "图片名", value: "图片相对路径")
        Map<String, String> saveFilePathMap = MultipartFileUtil.saveFileToLocal(files, fileSpace, filePath);
        if (saveFilePathMap.size() == 0){
            log.error("上传的图片为空 saveFilePathMap = {}", saveFilePathMap.toString());
            throw new SLifeException(201, "上传的图片为空");
        }

        for (String imageName : saveFilePathMap.keySet()) {
            ShopImage shopImage = new ShopImage();
            shopImage.setShopId(shopDetail.getShopId());           //设置图片所属的店铺
            shopImage.setImageUrl(saveFilePathMap.get(imageName)); //设置图片路径
            shopImageRepository.save(shopImage);
        }

        Shop shop = new Shop(); //保存店铺简要信息
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
     * 根据店铺id查询所有的图片
     * @param shopId
     * @return
     */
    @Override
    public List<ShopImage> findAllImageByShopId(String shopId) {
        return shopImageRepository.findAllByShopId(shopId);
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

    /**
     * 根据店铺id查询店铺详情VO对象
     * @param shopId
     * @return
     */
    @Override
    public ShopDetailVO findShopDetailVOByShopId(String shopId) {
        return shopDetailRepository.findShopDetailVOByShopId(shopId);
    }

    /**
     * 更新店铺的评分的方法
     * @param newScore
     * @param shopId
     */
    @Transactional
    @Override
    public void updateShopScore(Integer newScore, String shopId) {
        shopDetailRepository.updateShopScore(newScore, shopId);
        shopRepository.updateShopScore(newScore, shopId);
    }
}
