package i9.defence.platform.aliyun.docker.controller;

import com.alibaba.fastjson.JSONObject;
import i9.defence.platform.aliyun.docker.service.UpStreamService;
import i9.defence.platform.aliyun.docker.service.model.UpStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UpStreamService upStreamService;

    @RequestMapping("/")
    public String index() {
        return "Hello Docker!";
    }

    @RequestMapping("/clear")
    public String clear() {
        this.upStreamService.deleteUpStream();
        LOGGER.info("上行数据清除成功");
        return "success";
    }

    @RequestMapping("/upstream")
    public String upstream() {
    List<UpStream> list = this.upStreamService.queryUpStreamList();
        String jsonStr = JSONObject.toJSONString(list);
        return jsonStr;
    }
}
