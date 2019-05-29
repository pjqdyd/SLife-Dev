package com.pjqdyd.service.impl;

import com.pjqdyd.dao.NewsInfoRepository;
import com.pjqdyd.dao.UserLikeNewsRepository;
import com.pjqdyd.dao.UserRepository;
import com.pjqdyd.pojo.UserLikeNews;
import com.pjqdyd.service.UserLikeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLikeNewsServiceImpl implements UserLikeNewsService {

    @Autowired
    private UserLikeNewsRepository userLikeNewsRepository;

    @Autowired
    private NewsInfoRepository newsInfoRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户给一条动态点赞了
     * @param userId
     * @param newsId
     */
    @Transactional
    @Override
    public void userLikeAnews(String userId, String newsId, String pulisherId) {

        UserLikeNews userLikeNews = new UserLikeNews();
        userLikeNews.setUserId(userId);
        userLikeNews.setNewsId(newsId);
        userLikeNewsRepository.save(userLikeNews); //保存用户点赞的动态关系

        newsInfoRepository.addLikeToNews(newsId);  //给动态添加一个赞

        userRepository.addUserAlike(pulisherId);   //给发布者一个赞
    }

    /**
     * 用户给一条动态取消点赞了
     * @param userId
     * @param newsId
     */
    @Transactional
    @Override
    public void userCancelLikeAnews(String userId, String newsId, String publisherId) {

        userLikeNewsRepository.deleteByNewsIdEqualsAndUserIdEquals(newsId, userId); //删除用户点赞的关系

        newsInfoRepository.reduceLikeToNews(newsId); //减少动态一个赞

        userRepository.reduceUserAlike(publisherId); //减少发布者一个赞
    }
}
