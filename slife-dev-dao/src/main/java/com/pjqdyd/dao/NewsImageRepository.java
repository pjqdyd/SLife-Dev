package com.pjqdyd.dao;

import com.pjqdyd.pojo.NewsImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**   
 * @Description:  [动态的图片repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface NewsImageRepository extends JpaRepository<NewsImage, Long> {

    /**
     * 通过动态id查询该动态的所有图片
     * @param newsId
     * @return
     */
    List<NewsImage> findAllByNewsId(String newsId);

}
