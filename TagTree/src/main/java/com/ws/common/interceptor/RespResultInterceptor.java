package com.ws.common.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 客户端http请求拦截器
 *
 * @author wangsen_a
 * @date 2023/12/13
 */
public class RespResultInterceptor implements HandlerInterceptor {

    /**
     * 用于记录是否标注了RespResult注解
     */
    public static final String RESPONSE_RESULTVO_ATTR = "RESPONSE_RESULTVO_ATTR";


    /**
     * 处理器handler执行前执行
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @return boolean
     * @throws Exception 异常
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            // 判断是否在类对象上添加了注解，如果添加了该注解，就将RESPONSE_RESULTVO_ATTR添加到请求属性字段中
            if (clazz.isAnnotationPresent(RespResult.class)) {
                // 设置属性，值为该注解
                request.setAttribute(RESPONSE_RESULTVO_ATTR, clazz.getAnnotation(RespResult.class));
            } else if (method.isAnnotationPresent(RespResult.class)) {
                // 判断是否在方法上添加了注解
                request.setAttribute(RESPONSE_RESULTVO_ATTR, method.getAnnotation(RespResult.class));
            }
        }
        return true;
    }

}
