package com.pjqdyd.handler;

import com.pjqdyd.exception.SLifeException;
import com.pjqdyd.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Description:  [全局的异常捕获处理类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@Slf4j
@ControllerAdvice
public class SLifeExceptionHandler {

    /**
     * 捕获返回自定义异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = SLifeException.class)
    public ResponseResult handSLifeException(SLifeException ex){
        log.error(ex.getMessage());
        return new  ResponseResult(ex.getErrorCode(), ex.getMessage(),null);
    }

    /**
     * 捕获所有异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult handException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseResult(201,ex.getMessage(),null);
    }

}
