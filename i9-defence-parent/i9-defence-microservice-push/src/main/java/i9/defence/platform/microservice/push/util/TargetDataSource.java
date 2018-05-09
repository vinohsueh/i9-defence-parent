package i9.defence.platform.microservice.push.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* 在方法上使用，用于指定使用哪个数据源
* 创建时间：2018年5月7日 上午9:44:54
* @author  lby
* @version  
* 
*/
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
