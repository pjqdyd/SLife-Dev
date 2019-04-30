package com.pjqdyd.controller;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.pojo.Shop;
import com.pjqdyd.pojo.ShopImage;
import com.pjqdyd.pojo.vo.ShopDetailVO;
import com.pjqdyd.result.ResponseResult;
import com.pjqdyd.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**   
 * @Description:  [店铺详情相关业务接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Slf4j
@Api(value = "店铺详情相关接口", tags = "店铺详情Controller层")
@CrossOrigin
@RestController
@RequestMapping("/slife/shopDetail")
public class ShopDetailController {

    @Autowired
    private ShopService shopService;

    /**
     * 根据店铺id查询店铺信息
     * @param shopId
     * @return
     */
    @ApiOperation(value = "根据shopId查询店铺详情信息", tags = "查询店铺详情")
    @GetMapping("/queryShopByShopId")
    public ResponseResult queryShopDetailByShopId(@RequestParam("shopId") String shopId){

        if (StringUtils.isBlank(shopId)){
            log.error("参数shopId不能为空 shopId = {}", shopId);
            return ResponseResult.error();
        }

        //查询店铺详情VO
        ShopDetailVO shopDetailVO = shopService.findShopDetailVOByShopId(shopId);
        if (shopDetailVO == null){
            log.error("未找到shopId= {} 的店铺详情 shopDetailVO = {}", shopId, shopDetailVO);
            return ResponseResult.error();
        }

        //查询并设置店铺的图片
        List<ShopImage> shopImageList = shopService.findAllImageByShopId(shopId);
        shopDetailVO.setShopImageList(shopImageList);

        return ResponseResult.success(shopDetailVO);
    }

}
