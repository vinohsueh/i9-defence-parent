package i9.defence.platform.api.intercepter;

import i9.defence.platform.utils.BindingResultException;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;


@Aspect
@Component
@Order(2)
public class HttpMethodParamValidAspect {
    
    @Pointcut("execution(* i9.defence.platform.api.controller..*(..))")
    public void valid() {
    }
    
  //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("valid()")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //取参数，如果没参数，那肯定不校验了
        Object[] params = proceedingJoinPoint.getArgs();
        if (params.length == 0) {
            return proceedingJoinPoint.proceed();
        }
        //寻找带BindingResult参数的方法，然后判断是否有error，如果有则是校验不通过
        for (Object object : params) {
            if (object instanceof BeanPropertyBindingResult) {
                //有校验
                BeanPropertyBindingResult bindingResult = (BeanPropertyBindingResult) object;
                if (bindingResult.hasErrors()) {
                    BindingResultException bindingResultException = new BindingResultException(bindingResult);
                    throw bindingResultException;
                }
            }
        }
        //  获得切入目标对象
        Object target = proceedingJoinPoint.getThis();
        // 获得切入的方法
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, params);
        //如果有校验不通过的
        if (!validResult.isEmpty()) {
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method); // 获得方法的参数名称
            BindingResultException bindingResultException = new BindingResultException();
            for(ConstraintViolation<Object> constraintViolation : validResult) {
                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
                int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
                String paramName = parameterNames[paramIndex];  // 获得校验的参数名称
                bindingResultException.addError(paramName, constraintViolation.getMessage());
            }
            //返回第一条
            throw bindingResultException;
        }
        return proceedingJoinPoint.proceed();
    }

    private Set<ConstraintViolation<Object>> validMethodParams(Object target, Method method, Object[] params) {
        return executableValidator.validateParameters(target, method, params);
    }

    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    
    private final ExecutableValidator executableValidator = validatorFactory.getValidator().forExecutables();
}
