package com.pjqdyd.service.impl;

import com.pjqdyd.dao.NewsImageRepository;
import com.pjqdyd.dao.NewsInfoRepository;
import com.pjqdyd.pojo.NewsImage;
import com.pjqdyd.pojo.vo.NewsInfoVO;
import com.pjqdyd.pojo.vo.NewsListVO;
import com.pjqdyd.service.NewsListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
 * @Description:  [动态列表服务的实现类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
@Service
public class NewsListServiceImpl implements NewsListService {

    @Autowired
    private NewsInfoRepository newsInfoRepository;

    @Autowired
    private NewsImageRepository newsImageRepository;

    /**
     * 查询附近的动态
     * @param minLat
     * @param maxLat
     * @param minLot
     * @param maxLot
     * @param userId
     * @param pageable
     * @return 返回动态列表VO对象
     */
    @Override
    public NewsListVO findAllLocalNewsInfoVO(Double minLat, Double maxLat, Double minLot, Double maxLot, String userId, Pageable pageable) {

        //查询附近的动态
        Page<NewsInfoVO> newsInfoVOPage = newsInfoRepository.findLocalAllNewsInfoVO(minLat, maxLat, minLot, maxLot, pageable);

        return newsInfoVOPageToNewsListVO(newsInfoVOPage, userId, pageable.getPageNumber());
    }

    /**
     * 通过店铺id查询有关的动态
     * @param newsShopId
     * @param pageable
     * @param userId
     * @return
     */
    @Override
    public NewsListVO findAllNewsByNewsShopId(String newsShopId, String userId ,Pageable pageable) {

        //查询与店铺有关的动态
        Page<NewsInfoVO> newsInfoVOPage = newsInfoRepository.findAllByNewsShopId(newsShopId, pageable);

        return newsInfoVOPageToNewsListVO(newsInfoVOPage, userId, pageable.getPageNumber());
    }

    /**
     * 根据发布者id查询有关动态
     * @param publisherId
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public NewsListVO findAllNewsByPublisherId(String publisherId, String userId, Pageable pageable) {

        //查询发布者的动态
        Page<NewsInfoVO> newsInfoVOPage = newsInfoRepository.findAllByPublisherId(publisherId, pageable);

        return newsInfoVOPageToNewsListVO(newsInfoVOPage, userId, pageable.getPageNumber());
    }

    /**
     * 封装将动态VO的page对象转换为NewsListVO对象, 便于返回给前端
     * @param newsInfoVOPage
     * @param userId
     * @param page
     * @return
     */
    public NewsListVO newsInfoVOPageToNewsListVO(Page<NewsInfoVO> newsInfoVOPage, String userId, Integer page){
        if (newsInfoVOPage.getTotalElements() == 0){
            return null;
        }
        //设置数据给要返回前端的NewsListVO
        NewsListVO newsListVO = new NewsListVO();
        newsListVO.setPage(page);
        newsListVO.setTotal((int) newsInfoVOPage.getTotalElements());
        newsListVO.setTotalPage(newsInfoVOPage.getTotalPages());

        //获取附近动态List集合
        List<NewsInfoVO> newsInfoVOList = newsInfoVOPage.getContent();

        for (NewsInfoVO newsInfoVO: newsInfoVOList) { //遍历每一个动态VO对象

            if(StringUtils.isNotBlank(userId)){ //如果用户id不为空
                //TODO 查询用户是否给该动态点赞
                //newsInfoVO.setIsLike();
            }
            //设置该动态的图片
            List<NewsImage> newsImages = newsImageRepository.findAllByNewsId(newsInfoVO.getNewsId());
            newsInfoVO.setNewsImageList(newsImages);
        }

        newsListVO.setNewsList(newsInfoVOList); //设置动态列表数据

        return newsListVO;
    }
}
