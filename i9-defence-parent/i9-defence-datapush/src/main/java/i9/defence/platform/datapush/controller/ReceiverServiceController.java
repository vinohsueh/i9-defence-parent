package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.config.ServerConfig;
import i9.defence.platform.datapush.service.OriginalRecordService;
import i9.defence.platform.datapush.service.ReceiveMessageDomainService;
import i9.defence.platform.datapush.utils.ReceiveMessageDto;
import i9.defence.platform.datapush.utils.Util;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 接收平台数据控制器
 *
 * @author R12
 * @date 2018年10月11日 12:57:53
 */
@RestController
public class ReceiverServiceController {

    private static Logger logger = LoggerFactory.getLogger(ReceiverServiceController.class);

    @Autowired
    private OriginalRecordService originalRecordService;

    @Autowired
    private ReceiveMessageDomainService receiveMessageDomainService;

    /**
     * 功能描述：第三方平台数据接收。
     * <p>
     * <ul>
     * 注:
     * <li>1.OneNet平台为了保证数据不丢失，有重发机制，如果重复数据对业务有影响，数据接收端需要对重复数据进行排除重复处理。</li>
     * <li>2.OneNet每一次post数据请求后，等待客户端的响应都设有时限，在规定时限内没有收到响应会认为发送失败。
     * 接收程序接收到数据时，尽量先缓存起来，再做业务逻辑处理。</li>
     * </ul>
     *
     * @param body 数据消息
     * @return 任意字符串。OneNet平台接收到http 200的响应，才会认为数据推送成功，否则会重发。
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public String receive(@RequestBody String body, HttpServletResponse httpServletResponse) throws Exception {
        ReceiveMessageDto receiveMessageDto = Util.resolveBody(body, false);
        if (receiveMessageDto == null) {
            logger.error("数据接收, 内容: {}, 转换失败", body);
            httpServletResponse.setStatus(500);
            return "ERROR";
        }
        boolean dataRight = Util.checkSignature(receiveMessageDto, ServerConfig.TOKEN);
        if (!dataRight) {
            logger.error("数据接收: 内容: {}, 签名失败", receiveMessageDto.toString());
            httpServletResponse.setStatus(500);
            return "ERROR";
        }
        logger.info("数据接收, 内容: {}", receiveMessageDto.toString());
        originalRecordService.saveOriginalRecordMessage(receiveMessageDto.toString());

        JSONObject msg = new JSONObject(receiveMessageDto.getMsg().toString());
        try {
            receiveMessageDomainService.dealWithReceiveMessage(msg);
        }
        catch (Exception e) {
            logger.error("数据接收: 内容: {}, ", msg.toString());
            httpServletResponse.setStatus(500);
            return "ERROR";
        }
        return "SUCCESS";
    }

    /**
     * 功能说明： URL&Token验证接口。如果验证成功返回msg的值，否则返回其他值。
     *
     * @param msg 验证消息
     * @param nonce 随机串
     * @param signature 签名
     * @return msg值
     */
    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    @ResponseBody
    public String check(@RequestParam(value = "msg") String msg, @RequestParam(value = "nonce") String nonce,
            @RequestParam(value = "signature") String signature) throws Exception {
        logger.info("url&token check: msg:{} nonce{} signature:{}", msg, nonce, signature);
        if (Util.checkToken(msg, nonce, signature, ServerConfig.TOKEN)) {
            return msg;
        } else {
            return "error";
        }
    }
}
