package i9.defence.platform.api.intercepter;

import i9.defence.platform.utils.BindingResultException;
import i9.defence.platform.utils.BusinessException;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * aop
 * @author lby
 *
 * @create 2017年12月14日
 *
 */
@Aspect
@Component
@Order(1)
public class LogRecordAspect {
   
    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);
    
    @Pointcut("execution(* i9.defence.platform.api.controller..*(..))")
    public void excudeService() {
    }
    
    @SuppressWarnings("unchecked")
    @Around(value = "excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info("接收, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString == null ? "" : queryString);
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            Object object = pjp.proceed();
            if (object instanceof HashMap) {
                HashMap<String, Object> data = (HashMap<String, Object>) object;
                result.put("result", 1);
                result.put("data", data);
                result.put("errorMsg", "");
            }else{
                return object;
            }
            
        } catch (BusinessException exception) {
            result = new HashMap<String, Object>();
            result.put("result", 0);
            result.put("errorMsg", exception.getErrorMessage());
            result.put("errorCode", exception.getErrorCode());
            exception.printStackTrace();
            logger.error("error, message: {}, errorMessage: {}, exception: {}",exception.getMessage(),exception.getErrorMessage(),exception.getExceptionMessage());
        } catch (BindingResultException exception) {
            result = new HashMap<String, Object>();
            result.put("result", 0);
            result.put("errorMsg", exception.toErrors());
            exception.printStackTrace();
            logger.error(exception.getMessage());
        } catch (Exception e) {
            result = new HashMap<String, Object>();
            result.put("result", 0);
            result.put("errorMsg", "系统错误");
            result.put("result", "");
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        logger.info("响应, url: {}, result: {}", url, result);
        return result;
    }
}
