package i9.defence.platform.api.config;

import i9.defence.platform.api.intercepter.UserLoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * 
 * 
 * @author lby
 *
 * @create 2017年12月18日
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/head/**").addResourceLocations("file:F:/Attachments/head/");
        super.addResourceHandlers(registry);
    }
    
    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/equipment/updateAllEquipmentStatus");
        super.addInterceptors(registry);
    }
}
