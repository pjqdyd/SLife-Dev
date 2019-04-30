package com.pjqdyd.service.impl;

import com.pjqdyd.dao.NewsImageRepository;
import com.pjqdyd.dao.NewsInfoRepository;
import com.pjqdyd.exception.SLifeException;
import com.pjqdyd.pojo.NewsImage;
import com.pjqdyd.pojo.NewsInfo;
import com.pjqdyd.service.NewsService;
import com.pjqdyd.utils.MultipartFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**   
 * @Description:  [动态服务service实现类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
@Service
public class NewsServiceImpl implements NewsService {

    /**
     * 注入动态信息repository
     */
    @Autowired
    private NewsInfoRepository newsInfoRepository;

    /**
     * 注入动态图片repository
     */
    @Autowired
    private NewsImageRepository newsImageRepository;

    /**
     * 文件命名空间
     */
    @Value("${common.slife.fileSpace}")
    private String fileSpace;

    /**
     * 保存动态信息
     * @param newsInfo 动态信息
     * @param files 动态的图片文件
     * @return
     */
    @Override
    @Transactional
    public NewsInfo saveNewsInfo(NewsInfo newsInfo, List<MultipartFile> files) {

        NewsInfo result = newsInfoRepository.save(newsInfo); //保存动态信息

        String filePath = "/news/" + newsInfo.getPublisherId() + "/newsImage"; //动态图片保存的相对目录

        //保存店铺图片到本地, saveFilePathMap保存后图片相对路径Map集合(key: "图片名", value: "图片相对路径")
        Map<String, String> saveFilePathMap = MultipartFileUtil.saveFileToLocal(files, fileSpace, filePath);
        if (saveFilePathMap.size() == 0){
            log.error("上传的图片为空 saveFilePathMap = {}", saveFilePathMap.toString());
            throw new SLifeException(201, "动态上传的图片为空");
        }

        //保存图片对象到数据库
        for (String imageName: saveFilePathMap.keySet()){
            NewsImage newsImage = new NewsImage();
            newsImage.setNewsId(newsInfo.getNewsId()); //设置图片所属的动态id
            newsImage.setImageUrl(saveFilePathMap.get(imageName));//设置图片路径
            newsImageRepository.save(newsImage);
        }

        if (result == null){
            return null;
        }
        return result;
    }
}
