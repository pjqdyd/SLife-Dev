package com.pjqdyd.service;

import com.pjqdyd.pojo.NewsInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**   
 * @Description:  [动态服务service接口]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface NewsService {

    /**
     * 保存动态信息
     * @param newsInfo 动态信息
     * @param files 动态的图片文件
     * @return
     */
    NewsInfo saveNewsInfo(NewsInfo newsInfo, List<MultipartFile> files);

}
