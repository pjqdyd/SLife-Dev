package com.pjqdyd.controller;

import com.pjqdyd.pojo.ShopDetail;
import com.pjqdyd.pojo.dto.ShopDTO;
import com.pjqdyd.result.ResponseResult;
import com.pjqdyd.service.ShopService;
import com.pjqdyd.utils.UniqueId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.ArrayList;
import java.util.List;

/**
 *    
 *
 * @Description:  [店铺Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

@Api(value = "店铺相关业务接口", tags = {"店铺controller层"})
@Slf4j
@RestController
@RequestMapping("/slife/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "申请店铺", tags = "用户申请店铺接口")
    @PostMapping(value = "/applyShop", headers = "content-type=multipart/form-data")
    public ResponseResult applyShop(@ModelAttribute ShopDTO shopDTO,
                                    MultipartRequest request) {

        List<MultipartFile> files = new ArrayList<>();//接收所有的图片文件(前端限制最多5张)
        files.add(request.getFile("image0"));
        files.add(request.getFile("image1"));
        files.add(request.getFile("image2"));
        files.add(request.getFile("image3"));
        files.add(request.getFile("image4"));

        ShopDetail shopDetail = shopService.findShopByApplyerId(shopDTO.getApplyerId());

        if (shopDetail == null) {//如果申请者未申请过店铺就新建一个店铺并设置shopId
            shopDetail = new ShopDetail();
            shopDetail.setShopId(UniqueId.getNewId("s-"));
        }

        //给店铺详情对象设置数据
        BeanUtils.copyProperties(shopDTO, shopDetail);
        shopDetail.setShopLatitude(Double.valueOf(shopDTO.getShopLatitude()));
        shopDetail.setShopLongitude(Double.valueOf(shopDTO.getShopLongitude()));

        String[] supporServerList = shopDTO.getSupportServer().split(",");  //店铺支持服务的字符串数组
        for (String serverName : supporServerList) {                           //获取店铺支持的服务名集合, 设置给店铺详情
            switch (serverName) {
                case "wifi":
                    shopDetail.setWifi(true);
                    break;
                case "cash":
                    shopDetail.setCash(true);
                    break;
                case "alipay":
                    shopDetail.setAlipay(true);
                    break;
                case "wechatpay":
                    shopDetail.setWechatpay(true);
                    break;
            }
        }

        ShopDetail result = shopService.saveShopDetail(shopDetail, files);

        if (result == null) {
            ResponseResult.error(201, "申请失败");
        }
        return ResponseResult.success(null);
    }
}


