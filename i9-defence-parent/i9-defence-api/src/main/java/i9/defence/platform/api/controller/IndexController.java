package i9.defence.platform.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 创建时间：2017年12月18日 下午4:51:53
 * @author  lby
 * @version  
 * 
 */
@Controller
public class IndexController {
    
    /**
     * 主页
     * @return
     */
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/")
    public String defaultPage() {
        return "redirect:/index.html";
    }
}
