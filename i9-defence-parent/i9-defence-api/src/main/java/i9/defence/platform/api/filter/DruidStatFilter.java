package i9.defence.platform.api.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 配置druid监控统计功能 配置Filter
 * 
 * @author lby
 * @create 2017年11月14日
 */
// /* 拦截所有
// 支持异步操作
// 要排除的资源文件类型
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", asyncSupported = true, initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})

public class DruidStatFilter extends WebStatFilter {
}
