package com.ws.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> ExceptionHandler(Exception e) {
        log.error("异常信息：{}", e.toString());
        e.printStackTrace();
        return Result.FAIL("服务器出现未知错误");
    }

    /**
     * 自定义异常处理程序
     *
     * @param e e
     * @return {@link Result}<{@link ?}>
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result<?> customExceptionHandler(CustomException e) {
        log.error("异常信息：{}", e);
        return Result.FAIL(e.getCode(), e.getMsg());
    }

}
