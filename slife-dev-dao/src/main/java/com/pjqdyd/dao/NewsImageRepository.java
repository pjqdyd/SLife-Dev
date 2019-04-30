package com.pjqdyd.dao;

import com.pjqdyd.pojo.NewsImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [动态的图片repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface NewsImageRepository extends JpaRepository<NewsImage, Long> {
}
