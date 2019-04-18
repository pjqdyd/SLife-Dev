package com.pjqdyd.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**   
 * @Description:  [检查参数是否为空的工具类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Slf4j
public class CheckParamIsBlank {

    /**
     * 检查多个String参数是否为空, null  ""  " "都为空
     * @param methodName 方法名
     * @param params 参数数组
     * @return true存在空字符串, false不存在
     */
    public static Boolean checkStrParams(String methodName, String... params){
        int count = 0;
        if (params != null && params.length > 0){

            for (String param: params) {
                if (StringUtils.isBlank(param)){
                    log.error(methodName + "参数不能为空 param={}", param);
                    return true;
                }
                count++;
            }
            if (count == params.length){
                return false;
            }
        }
        return true;
    }
}
