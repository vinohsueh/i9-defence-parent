package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.service.ReceiveMessageDataPointService;

import i9.defence.platform.datapush.service.ReceiveMessageDomainService;
import i9.defence.platform.datapush.service.ReceiveMessagePowerStateService;
import i9.defence.platform.datapush.utils.TypeEnum;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 通用消息处理服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:52:38
 */
@Service
public class ReceiveMessageDomainServiceImpl implements ReceiveMessageDomainService {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageDomainServiceImpl.class);

    @Autowired
    private ReceiveMessageDataPointService receiveMessageDataPointService;

    @Autowired
    private ReceiveMessagePowerStateService receiveMessagePowerStateService;

    /**
     * 处理上行数据消息
     * 
     * @param data
     */
    @Override
    public void dealWithReceiveMessage(JSONObject data) {
        final int type = data.getInt("type");
        TypeEnum typeEnum = TypeEnum.valueOf(type);
        if (typeEnum == TypeEnum.UPLOAD_DATA_POINT) {
            receiveMessageDataPointService.dealWithUplinkData(data);
        } else if (typeEnum == TypeEnum.UPLOAD_POWER_STATE) {
            receiveMessagePowerStateService.dealWithUplinkData(data);
        } else {
            logger.error("未找到的TypeEnum, type : {}, data : {}", type, data);
        }
    }
}
