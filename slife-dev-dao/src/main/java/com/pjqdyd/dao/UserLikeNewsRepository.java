package com.pjqdyd.dao;

import com.pjqdyd.pojo.UserLikeNews;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [用户点赞动态repository]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface UserLikeNewsRepository extends JpaRepository<UserLikeNews, Long> {

    /**
     * 通过动态id和用户id删除它们的点赞关系
     * @param newsId
     * @param userId
     */
    void deleteByNewsIdEqualsAndUserIdEquals(String newsId, String userId);

    /**
     * 通过用户id,和动态id来判断是否存在点赞关系
     * @param userId
     * @param newsId
     * @return
     */
    Boolean existsByUserIdEqualsAndNewsIdEquals(String userId, String newsId);

}
