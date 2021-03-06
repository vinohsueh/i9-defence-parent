package i9.defence.platform.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import i9.defence.platform.api.datasource.DynamicDataSourceRegister;

/**
 * 为了打包war包执行，需要继承SpringBootServletInitializer 
 * @author lby
 * @create 2017年12月18日
 */

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("i9.defence.platform")
@EnableCaching
@ServletComponentScan // 扫描使用注解方式的servlet 
@EnableScheduling//定时器
@Import({DynamicDataSourceRegister.class})//注册动态多数据源
public class Application extends SpringBootServletInitializer{
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
