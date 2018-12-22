package i9.defence.platform.aliyun.docker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.aliyun.docker.service.UpStreamService;
import i9.defence.platform.aliyun.docker.service.model.UpStream;

@RestController
public class IndexController {

    @Autowired
    private UpStreamService upStreamService;

    @RequestMapping("/")
    public String index() {
        return "Hello Docker!";
    }

    public String clear() {
        this.upStreamService.deleteUpStream();
        return "success";
    }

    public String upstream() {
        List<UpStream> list = this.upStreamService.queryUpStreamList();
        String jsonStr = JSONObject.toJSONString(list);
        return jsonStr;
    }
}
