package com.pjqdyd.utils;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.exception.SLifeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:  [封装处理接收的文件的工具类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
public class MultipartFileUtil {

    /**
     * 保存文件到本地
     * @param files MultipartFile文件对象数组
     * @param fileSpace 文件保存本地的命名空间
     * @param filePath 文件保存在命名空间下的路径
     * @return Map<String, String> 文件的保存后相对路径Map集合 key: file.getName, value: filePath + "/" + filename
     */
    public static Map<String, String> saveFileToLocal(List<MultipartFile> files, String fileSpace, String filePath){

        if (CheckParamIsBlank.checkStrParams("saveFileToLocal", fileSpace, filePath)){
            throw new SLifeException(ResultEnum.PARAM_ERROR);
        }

        String fileDir = fileSpace + filePath + "/"; //文件上传保存在本地的目录
        String fileOrignName = null;                 //文件源名
        String finalFilePath = null;                 //文件最终的保存路径
        Map<String, String> filePathMap  = new HashMap<>(); //存放文件保存后的路径集合
        for (MultipartFile file: files) {

            if (file != null){
                FileOutputStream fileOutputStream = null;
                InputStream inputStream = null;
                try {
                    fileOrignName = file.getOriginalFilename();
                    if (StringUtils.isNotBlank(fileOrignName)){
                        finalFilePath = fileDir + fileOrignName;

                        File outFile = new File(finalFilePath);
                        //如果父文件对象不存在,或者父文件夹不是目录
                        if (!outFile.getParentFile().exists() || !outFile.getParentFile().isDirectory()) {
                            // 创建父文件夹
                            outFile.getParentFile().mkdirs();
                        }
                        fileOutputStream = new FileOutputStream(outFile);
                        inputStream = file.getInputStream();
                        IOUtils.copy(inputStream, fileOutputStream);
                        filePathMap.put(file.getName(), filePath + "/" + fileOrignName);
                    }else {
                        log.error("保存文件,读取源文件名为空 filename={}", fileOrignName);
                        throw new SLifeException( 201,"保存文件, fileName为空");
                    }
                }catch (Exception e){
                    log.error("文件IO流操作异常 {}", e.getMessage());
                }finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (Exception ex) {
                            log.error("文件输出流释放关闭异常 {}", ex.getMessage());
                        }
                    }
                    if(inputStream != null){
                        try {
                            inputStream.close();
                        }catch (Exception ex){
                            log.error("文件输入流关闭异常 {}", ex.getMessage());
                        }
                    }
                }
            }
        }

        return filePathMap;
    }

}
