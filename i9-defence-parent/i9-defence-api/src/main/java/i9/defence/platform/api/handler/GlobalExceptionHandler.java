package i9.defence.platform.api.handler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/** 
* 创建时间：2018年4月12日 下午1:39:49
* @author  lby
* @version  
* 
*/
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = AuthorizationException.class)
    public HashMap<String, Object> authorizationErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		logger.info("---authorizationErrorHandler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
		map.put("result", -2);
		map.put("errorMsg", "您没有权限!");
		return map;
    }
	
	@ExceptionHandler(value = UnavailableSecurityManagerException.class)
    public HashMap<String, Object> baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		logger.info("---BaseException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
		return map;
    }
	
}
