package com.users.component.config.aspect;

import com.alibaba.fastjson.JSONObject;
import com.users.component.config.aspect.annotation.LogForController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP接口请求拦截类02
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut(value = "execution(* com.users.modules.*.controller.*.*(..))")
    public void logPointCut() {
    }

    @Pointcut(value = "execution(* com.users.*.*.*.*.*(..))")
    public void logPointCutOfTime() {
    }

    @Around(value = "logPointCut() && @annotation(org.springframework.web.bind.annotation.RequestMapping) && @annotation(controllerLog)", argNames = "pjp,controllerLog")
    public Object autoLogRecord(ProceedingJoinPoint pjp, LogForController controllerLog) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
        logger.info("\n");

        Object[] argsValue = pjp.getArgs();
        StringBuilder stringBuilder = new StringBuilder("02 Request Api--->#{\"Url\": {}, \"Content-Type\": {}, \"Controller\": {}, \"reqIp\": {}, \"resIp\": {}, \"Method\": {}，Param: ");
        Object[] objArray = new Object[argsValue.length + 6];
        if (argsValue.length != 0) {
            System.arraycopy(argsValue, 0, objArray, 6, argsValue.length);
            String[] parameterNames = signature.getParameterNames();
            for (String s : parameterNames) {
                stringBuilder.append(", \"").append(s).append("\": {}");
            }
        }
        stringBuilder.append("}");
        objArray[0] = request.getRequestURL();
        objArray[1] = request.getContentType();
        objArray[2] = signature.getName();
        objArray[3] = request.getRemoteAddr();
        objArray[4] = request.getLocalAddr();
        objArray[5] = request.getMethod();
        logger.info(stringBuilder.toString(), objArray);

        logger.info("02 Request Api finish...");
        Object obj = pjp.proceed();
        logger.info("02 Response Api--->#{}", JSONObject.toJSONString(obj));
        return obj;
    }

}