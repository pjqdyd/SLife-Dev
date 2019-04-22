package com.pjqdyd.controller;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.exception.SLifeException;
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

    /**
     * 用户申请/修改店铺接口
     * @param shopDTO
     * @param request
     * @return
     */
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

        ShopDetail shopDetail =  new ShopDetail();
        String shopId = shopService.findShopIdByApplyerId(shopDTO.getApplyerId());
        if (StringUtils.isBlank(shopId)) {//如果申请者未申请过店铺就新建一个店铺并设置shopId
            shopId = UniqueId.getNewId("s-");
        }
        //给店铺详情对象设置数据
        shopDetail.setShopId(shopId);
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

    /**
     * 查询店铺简要信息通过applyerId
     * @param applyerId
     * @return
     */
    @ApiOperation(value = "查询店铺简要信息", tags = "根据applyerId查询店铺简要信息接口")
    @GetMapping("/queryShopInfo")
    public ResponseResult queryShopInfo(@RequestParam String applyerId){

        if (StringUtils.isBlank(applyerId)){
            log.error("查询店铺简介信息, 参数为空applyerId = {}", applyerId);
            throw new SLifeException(ResultEnum.PARAM_ERROR);
        }

        ShopDetail shopDetail = shopService.findShopByApplyerId(applyerId);

        if (shopDetail != null){
            ShopDTO shopDTO = new ShopDTO();
            BeanUtils.copyProperties(shopDetail,shopDTO);
            shopDTO.setShopLatitude(String.valueOf(shopDetail.getShopLatitude()));
            shopDTO.setShopLongitude(String.valueOf(shopDetail.getShopLongitude()));
            return ResponseResult.success(shopDTO);   //返回成功的店铺简要信息
        }

        return ResponseResult.error(201,"未找到店铺");
    }

}


