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
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


/**
 *    
 *
 * @Description:  [动态列表Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */
@Api(value = "动态列表相关接口", tags = "动态Controller层")
@CrossOrigin
@RestController
@RequestMapping("/slife/newsList")
public class NewsListController {

    @Autowired
    private NewsListService newsListService;

    /**
     * 最新动态查询接口
     *
     * @param latitude
     * @param longitude
     * @param userId
     * @param page
     * @param size
     * @return
     */

    @ApiOperation(value = "最新的动态查询接口", tags = "最新附近动态")
    @GetMapping("/latestList")
    public ResponseResult queryLatestNews(@RequestParam("latitude") Double latitude,
                                          @RequestParam("longitude") Double longitude,
                                          @RequestParam("userId") String userId,
                                          @RequestParam("page") Integer page,
                                          @RequestParam(value = "size", defaultValue = "3") Integer size) {

        //按时间倒序, 即最新的在前面
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        //分页查询附近0.1经纬度度内, 也就是11.1公里范围内的动态
        NewsListVO newsListVO = newsListService.findAllLocalNewsInfoVO(
                latitude - 0.1, latitude + 0.1,
                longitude - 0.1, longitude + 0.1, userId, pageable);

        if (newsListVO == null) {
            return ResponseResult.error(ResultEnum.FAIL.getCode(), "未找到附近动态");
        }
        return ResponseResult.success(newsListVO);
    }


    /**
     * 推荐动态查询接口
     *
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
                                             @RequestParam(value = "size", defaultValue = "3") Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        //分页查询附近0.1经纬度度内, 也就是11.1公里范围内的动态
        NewsListVO newsListVO = newsListService.findAllLocalNewsInfoVO(
                latitude - 0.1, latitude + 0.1,
                longitude - 0.1, longitude + 0.1, userId, pageable);

        if (newsListVO == null) {
            return ResponseResult.error(ResultEnum.FAIL.getCode(), "未找到附近动态");
        }
        return ResponseResult.success(newsListVO);
    }


    /**
     * 热门的动态查询接口
     *
     * @param latitude
     * @param longitude
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "热门的动态查询接口", tags = "热门附近动态")
    @GetMapping("/hotList")
    public ResponseResult queryHotNews(@RequestParam("latitude") Double latitude,
                                       @RequestParam("longitude") Double longitude,
                                       @RequestParam("userId") String userId,
                                       @RequestParam("page") Integer page,
                                       @RequestParam(value = "size", defaultValue = "3") Integer size) {

        //按点赞数倒序, 即点赞多的在前面
        Sort sort = new Sort(Sort.Direction.DESC, "newsLikeCounts");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        //分页查询附近0.1经纬度度内, 也就是11.1公里范围内的动态
        NewsListVO newsListVO = newsListService.findAllLocalNewsInfoVO(
                latitude - 0.1, latitude + 0.1,
                longitude - 0.1, longitude + 0.1, userId, pageable);

        if (newsListVO == null) {
            return ResponseResult.error(ResultEnum.FAIL.getCode(), "未找到附近动态");
        }
        return ResponseResult.success(newsListVO);
    }

    /**
     * 店铺有关的动态
     *
     * @param newsShopId
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "店铺有关动态查询接口", tags = "店铺有关的动态")
    @GetMapping("/shopNewsList")
    public ResponseResult queryNewsByNewsShopId(@RequestParam("newsShopId") String newsShopId,
                                                @RequestParam("userId") String userId,
                                                @RequestParam("page") Integer page,
                                                @RequestParam(value = "size", defaultValue = "3") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        NewsListVO newsListVO = newsListService.findAllNewsByNewsShopId(newsShopId, userId, pageable);
        if (newsListVO == null) {
            return ResponseResult.error(ResultEnum.FAIL.getCode(), "未找到店铺有关的动态");
        }
        return ResponseResult.success(newsListVO);
    }

    /**
     * 查询发布者的动态
     *
     * @param publisherId
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "发布者动态查询接口", tags = "发布者的动态")
    @GetMapping("/createNewsList")
    public ResponseResult queryNewsByPublisherId(@RequestParam("publisherId") String publisherId,
                                                 @RequestParam("userId") String userId,
                                                 @RequestParam("page") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        NewsListVO newsListVO = newsListService.findAllNewsByPublisherId(publisherId, userId, pageable);
        if (newsListVO == null) {
            return ResponseResult.error(ResultEnum.FAIL.getCode(), "未找到发布者有关的动态");
        }
        return ResponseResult.success(newsListVO);
    }

    /**
     * 查询好友点赞的动态
     * @param friendId
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "好友点赞动态查询接口", tags = "好友点赞的动态")
    @GetMapping("/likeNewsList")
    public ResponseResult queryLikeNewsByFriendId(@RequestParam("friendId") String friendId,
                                                  @RequestParam("userId") String userId,
                                                  @RequestParam("page") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "3") Integer size){

        Pageable pageable = PageRequest.of(page - 1, size);

        NewsListVO newsListVO = newsListService.findAllLikeNewsByFriendId(friendId, userId, pageable);
        if (newsListVO == null) {
            return ResponseResult.error(ResultEnum.FAIL.getCode(), "未找到好友点赞的动态");
        }
        return ResponseResult.success(newsListVO);
    }


}
