package com.pjqdyd.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.exception.SLifeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**   
 * @Description:  [通过向QQ服务器请求用户信息,来验证用户的openid是否合法的工具类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
public class VerifyUserOpenId {

    /**
     * 通过http请求验证用户信息的方法
     * @param url 请求验证api接口的url, 后面要有参数占位符如http://abc.com/api?param1={param1}&param2={param2}
     * @param paramsMap 参数Map<String,String>,用Map集合装载参数,如map.put("param1" : "123456")
     * @return Map集合 请求api返回的response的body的内容
     */
    public static Map<String, String> verifyByOpenId(String url, Map<String,String> paramsMap){

        //使用spring的restTemplate来发送http请求
        RestTemplate restTemplate = new RestTemplate();

        //拿到http请求的response的所有内容
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, paramsMap);

        //拿到body的内容
        String jsonResult = responseEntity.getBody();

        //使用jackson来完成类型转换
        ObjectMapper objectMapper = new ObjectMapper();
        //获取map类型是Map<String, String>
        MapType mapType = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, String.class);
        //将json格式的字符串转换为Map
        Map<String, String> resultMap = null;
        try {
            resultMap = objectMapper.readValue(jsonResult, mapType);
        } catch (IOException e) {
            log.error("验证openId的http返回的body内容json转Map转换异常 jsonResult={}", jsonResult);
            throw new SLifeException(ResultEnum.TYPE_CONVER_ERROR);
        }

        return resultMap;
    }

}
