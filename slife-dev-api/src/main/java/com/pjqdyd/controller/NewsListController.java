package com.pjqdyd.controller;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.pojo.vo.NewsListVO;
import com.pjqdyd.result.ResponseResult;
import com.pjqdyd.service.NewsListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;

/**   
 * @Description:  [动态列表Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Api(value = "动态列表相关接口", tags = "动态Controller层")
@CrossOrigin
@RestController
@RequestMapping("/slife/newsList")
public class NewsListController {

    @Autowired
    private NewsListService newsListService;

    @ApiOperation(value = "最新的动态查询接口", tags = "最新附近动态")
    @GetMapping("/latestList")
    public ResponseResult queryLatestNews(

    ){
        return null;
    }


    /**
     * 推荐动态查询接口
     * @param latitude
     * @param longitude
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation(value = "推荐的动态查询接口", tags = "推荐附近动态")
    @GetMapping("/recommendList")
    public ResponseResult queryRecommendNews(@RequestParam("latitude") Double latitude,
                                             @RequestParam("longitude") Double longitude,
                                             @RequestParam("userId") String userId,
                                             @RequestParam("page") Integer page,
                                             @RequestParam(value = "size", defaultValue = "5") Integer size){

        Pageable pageable = PageRequest.of(page-1, size);

        //分页查询附近0.1经纬度度内, 也就是11.1公里范围内的动态
        NewsListVO newsListVO = newsListService.findAllLocalNewsInfoVO(
                latitude - 0.1, latitude + 0.1,
                longitude - 0.1, longitude + 0.1, userId, pageable);

        if (newsListVO == null){
            return ResponseResult.error(ResultEnum.FAIL.getCode(), "未找到附近动态");
        }

        return ResponseResult.success(newsListVO);
    }


    @ApiOperation(value = "热门的动态查询接口", tags = "热门附近动态")
    @GetMapping("/hotList")
    public ResponseResult queryHotNews(){
        return null;
    }
}
