package com.pjqdyd.dao;

import com.pjqdyd.pojo.NewsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
 * @Description:  [动态repository接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface NewsInfoRepository extends JpaRepository<NewsInfo, String> {
}
