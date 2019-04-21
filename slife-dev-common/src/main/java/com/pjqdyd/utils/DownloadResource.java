package com.pjqdyd.utils;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.exception.SLifeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *    
 *
 * @Description:  [下载网络资源到本地的工具类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

@Slf4j
public class DownloadResource {

    /**
     * 下载保存资源的方法
     *
     * @param resourceUrl   请求资源的路径
     * @param saveFileSpace 保存在本地文件夹的命名空间
     * @param saveFilePath  保存在命名空间下的文件相对路径
     * @param fileName      文件命名
     * @return 文件的相对路径 saveFilePath + "/" + fileName
     */
    public String downloadResource(String resourceUrl, String saveFileSpace, String saveFilePath, String fileName) {

        if (CheckParamIsBlank.checkStrParams("downloadResource", resourceUrl, saveFileSpace, saveFilePath, fileName)) {
            throw new SLifeException(ResultEnum.PARAM_ERROR);
        }

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        String finalPath = saveFileSpace + saveFilePath + "/" + fileName; //文件上传保存的最终绝对路径
        try {
            URL url = new URL(resourceUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream(); //资源输入流
            File outFile = new File(finalPath); //文件输出对象

            //如果父文件对象不存在,或者父文件夹不是目录
            if (!outFile.getParentFile().exists() || !outFile.getParentFile().isDirectory()) {
                // 创建父文件夹
                outFile.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(outFile); //文件输出流
            IOUtils.copy(inputStream, fileOutputStream);

        } catch (IOException e) {
            log.error("IO流异常 {}", e.getMessage());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    log.error("文件IO流释放关闭异常 {}", e.getMessage());
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                }catch (IOException e){
                    log.error("文件IO流关闭异常 {}", e.getMessage());
                }
            }
        }
        return saveFilePath + "/" + fileName;
    }
}
