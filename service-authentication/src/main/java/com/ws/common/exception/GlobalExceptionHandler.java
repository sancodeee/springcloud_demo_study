package com.ws.common.exception;

import com.ws.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author wangsen_a
 * @date 2023/12/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常处理程序
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(Exception.class)
    public Result<?> ExceptionHandler(Exception e) {
        log.error("异常信息：{}", e.toString());
        e.printStackTrace();
        return Result.FAIL("服务器发生不明异常");
    }

    /**
     * 自定义处理程序
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(CustomException.class)
    public Result<?> CustomHandler(CustomException e) {
        //返回错误信息
        return Result.FAIL(e.getCode(), e.getMsg());
    }

}
