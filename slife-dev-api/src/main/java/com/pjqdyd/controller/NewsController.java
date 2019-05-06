package com.pjqdyd.controller;

import com.pjqdyd.pojo.NewsInfo;
import com.pjqdyd.pojo.dto.NewsInfoDTO;
import com.pjqdyd.result.ResponseResult;
import com.pjqdyd.service.NewsService;
import com.pjqdyd.service.ShopService;
import com.pjqdyd.utils.UniqueId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.ArrayList;
import java.util.List;


/**   
 * @Description:  [动态Controller层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Api(value = "动态相关业务接口", tags = "动态Controller层")
@RestController
@RequestMapping("/slife/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private ShopService shopService;

    /**
     * 发布创建动态接口
     * @param newsInfoDTO 接收动态数据
     * @param request 接收文件请求
     * @return
     */
    @ApiOperation(value = "创建动态接口", tags = "用户发布动态接口")
    @PostMapping(value = "/createNews", headers = "content-type=multipart/form-data")
    public ResponseResult createNews(@ModelAttribute NewsInfoDTO newsInfoDTO,
                                     MultipartRequest request){

        List<MultipartFile> files = new ArrayList<>();//接收所有动态图片文件(前端限制最多4张)
        files.add(request.getFile("image0"));
        files.add(request.getFile("image1"));
        files.add(request.getFile("image2"));
        files.add(request.getFile("image3"));

        if (StringUtils.isNotBlank(newsInfoDTO.getNewsShopId())){ //如果动态有关的店铺id不为空
            //根据用户给店铺的评分,更新店铺平均评分
            shopService.updateShopScore(newsInfoDTO.getNewsShopScore(), newsInfoDTO.getNewsShopId());
        }

        NewsInfo newsInfo = new NewsInfo();
        BeanUtils.copyProperties(newsInfoDTO, newsInfo);

        newsInfo.setNewsId(UniqueId.getNewIdRandom("n-")); //给动态设置随机id

        //保存动态信息和动态图片文件
        NewsInfo result = newsService.saveNewsInfo(newsInfo, files);

        if (result == null){
            return ResponseResult.error();
        }

        return ResponseResult.success(null);
    }

}
