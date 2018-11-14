package i9.defence.platform.utils;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class BookFacadeCglib implements MethodInterceptor {

    /**
     * 相当于JDK动态代理中的绑定
     * 
     * @param target
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Class<T> target) {
        // 创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(target);
        // 设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return (T) enhancer.create();
    }

    /**
     * 实现回调方法
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        try {
            System.out.println("方法执行前处理过程");
            // 调用业务类（父类中）的方法
            return proxy.invokeSuper(obj, args);
        } finally {
            System.out.println("方法执行后处理过程");
        }
    }

}
