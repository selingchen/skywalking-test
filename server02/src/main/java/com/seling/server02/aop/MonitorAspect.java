package com.seling.server02.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
@Order(1)
public class MonitorAspect {

    private static Logger logger = LoggerFactory.getLogger(MonitorAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.seling.*.controller.*.*(..))")
    public void monitor() {}

	@Before("monitor()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        logger.info("MonitorAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if( null != request) {
        // 记录下请求内容
	        logger.info("URL : " + request.getRemoteAddr()+ request.getRequestURL().toString() 
	        		+ "  HTTP_METHOD : " + request.getMethod() 
	        		+"  CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."+ joinPoint.getSignature().getName() 
	        		+ " ARGS : " + Arrays.toString(joinPoint.getArgs()));
	       // 获取所有参数方法一：
	        Enumeration<String> enu = request.getParameterNames();
	        while (enu.hasMoreElements()) {
	            String paraName = (String) enu.nextElement();
	            System.out.println(paraName + ": " + request.getParameter(paraName));
	        }
        }
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning("monitor()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        logger.info("MonitorAspect.doAfterReturning()");
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
    }
}