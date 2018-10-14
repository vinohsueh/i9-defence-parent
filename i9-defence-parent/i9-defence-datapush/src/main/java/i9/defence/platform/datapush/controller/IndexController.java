package i9.defence.platform.datapush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}