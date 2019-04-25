package com.pjqdyd.controller;

import com.pjqdyd.pojo.Shop;
import com.pjqdyd.pojo.vo.ShopListVO;
import com.pjqdyd.result.ResponseResult;
import com.pjqdyd.service.ShopListService;
import com.pjqdyd.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *    
 *
 * @Description:  [店铺列表controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */
@Slf4j
@Api(value = "店铺列表Controller层", tags = "查询店铺列表")
@CrossOrigin
@RestController
@RequestMapping("/slife/shopList")
public class ShopListController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopListService shopListService;

    /**
     * 获取附近的商铺
     *
     * @param latitude  用户所在位置的纬度
     * @param longitude 用户所在位置的经度
     * @param page      当前要查询的页数
     * @param size      当前要查询的数据条数
     * @return 包含
     */
    @ApiOperation(value = "查询附近的商铺", tags = "主页查询附近商铺")
    @GetMapping("/localShop")
    public ResponseResult getLocalShopList(@RequestParam("latitude") Double latitude,
                                           @RequestParam("longitude") Double longitude,
                                           @RequestParam("page") Integer page,
                                           @RequestParam(value = "size", defaultValue = "5") Integer size) {

        //Sort sort = new Sort(Sort.Direction.DESC, "rate"); //按评分高底排序
        //Pageable pageable = PageRequest.of(page-1, size, sort); //分页查询第page-1页的5条数据

        Pageable pageable = PageRequest.of(page - 1, size);
        //查询附近0.1经纬度度内, 也就是11.1公里范围内的店铺
        Page<Shop> shopPage = shopListService.findLocalShop(
                latitude - 0.1, latitude + 0.1,
                longitude - 0.1, longitude + 0.1,
                1, pageable);

        //设置数据给前端
        ShopListVO shopListVO = new ShopListVO();
        shopListVO.setPage(page);
        shopListVO.setTotal((int) shopPage.getTotalElements());
        shopListVO.setTotalPage(shopPage.getTotalPages());

        List<Shop> shopList = shopPage.getContent();
        shopListVO.setLocalList(shopList);

        return ResponseResult.success(shopListVO);
    }


    @ApiOperation(value = "查询附近的分类商铺", tags = "根据分类查询附近的商铺")
    @GetMapping("/localCateShop")
    public ResponseResult getLocalCateShopList(@RequestParam("latitude") Double latitude,
                                               @RequestParam("longitude") Double longitude,
                                               @RequestParam("category") String category) {

        //根据分类模糊查询附近的店铺
        List<Shop> shopList = shopListService.findAllLocalAndLikeCategory(latitude, longitude, category);

        return ResponseResult.success(shopList);
    }

}
