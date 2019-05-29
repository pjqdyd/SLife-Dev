package com.pjqdyd.service;

/**   
 * @Description:  [用户点赞动态service层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface UserLikeNewsService {

    /**
     * 用户给一条动态点赞了
     * @param userId
     * @param newsId
     */
    void userLikeAnews(String userId, String newsId, String publisherId);

    /**
     * 用户给一条动态取消点赞了
     * @param userId
     * @param newsId
     */
    void userCancelLikeAnews(String userId, String newsId, String publisherId);

}
