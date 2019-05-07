package com.pjqdyd.interceptor;

import com.pjqdyd.enums.ResultEnum;
import com.pjqdyd.exception.SLifeException;
import com.pjqdyd.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**   
 * @Description:  [自定义拦截器, 用于处理contorller层的拦截验证]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
public class SLifeApiInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redis;

    //用户的redis session的key命名前缀
    public static final String USER_REDIS_SESSION = "user_redis_session:";


    /**
     * 拦截请求,在调用contorller之前
     * return false请求被拦截  /  true请求通过
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //验证前端请求的用户id 和 token
        String userId = request.getHeader("userId");
        String userToken = request.getHeader("userToken");

        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)){

            //读取用户缓存在redis中的token
            String uniqueToken = redis.get(USER_REDIS_SESSION + userId);
            if (StringUtils.isBlank(uniqueToken)){ //如果没有,说明用户还未登录

                log.info("验证用户token不存在, 未登录 userId={}", userId);
                //在handler中异常被捕获后, 会返回信息给前端
                throw new SLifeException(ResultEnum.LOGIN_ERROR.getCode(), "未登录,请先登录");

            }else {
                if (!uniqueToken.equals(userToken)){ //如果缓存的token和用户的token不同

                    log.info("验证用户token不一致, 已在别处登录 userId={}", userId);
                    throw new SLifeException(ResultEnum.LOGIN_ERROR.getCode(), "您已在别处登录");
                }
            }

            return true;
        }else {
            log.info("接收用户token为空, 未登录 userId={}", userId);
            throw new SLifeException(ResultEnum.LOGIN_ERROR.getCode(), "未登录, 请先登录");
        }

    }

    /**
     * 请求controller之后,渲染视图之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求controller之后,渲染视图之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
