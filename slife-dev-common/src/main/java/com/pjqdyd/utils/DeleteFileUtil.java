package com.pjqdyd.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**   
 * @Description:  [封装删除本地文件的工具类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
public class DeleteFileUtil {

    /**
     * 删除本地文件
     * @param finalFilePath
     * @return true/false 是否删除
     */
    public static Boolean deleteLocalFile(String finalFilePath){

        if (StringUtils.isBlank(finalFilePath)){
            log.error("删除本地文件,文件路径为空 finalFilePath = {}", finalFilePath);
            return false;
        }

        try {
            File file = new File(finalFilePath);
            if (file.delete()){
                log.info("删除文件: " + file.getName());
                return true;
            }else {
                log.info("未删除文件: " + file.getName());
                return false;
            }
        }catch (Exception e){
            log.info("删除文件异常: {}", e.getMessage());
        }

        return false;
    }

}
