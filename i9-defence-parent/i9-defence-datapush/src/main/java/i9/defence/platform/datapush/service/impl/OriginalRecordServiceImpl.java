package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.controller.ReceiverServiceController;
import i9.defence.platform.datapush.entity.OriginalRecord;
import i9.defence.platform.datapush.respository.OriginalRecordRepository;
import i9.defence.platform.datapush.service.OriginalRecordService;
import i9.defence.platform.datapush.utils.DateUtil;
import i9.defence.platform.datapush.utils.StringHelper;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 原始消息服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:51:12
 */
@Service
public class OriginalRecordServiceImpl implements OriginalRecordService {

    private static Logger logger = LoggerFactory.getLogger(ReceiverServiceController.class);

    @Autowired
    private OriginalRecordRepository originalRecordRepository;

    /**
     * 记录原始消息
     * 
     * @param message
     */
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    @Override
    public void saveOriginalRecordMessage(String message) {
        try {
            OriginalRecord originalRecord = new OriginalRecord();
            originalRecord.setId(StringHelper.randomUUIDStr());
            originalRecord.setMessage(message);
            originalRecord.setSubmitDate(new Date());
            this.originalRecordRepository.saveAndFlush(originalRecord);
            logger.info("保存原始数据成功, {}", message);
        } catch (Exception e) {
            logger.error("保存原始数据失败, {}", message, e);
        }
    }

    @Override
    public List<OriginalRecord> getOriginalRecordList(String startDate, String endDate) throws Exception {
        Date sDate = DateUtil.parse(startDate);
        Date eDate = DateUtil.parse(endDate);
        return this.originalRecordRepository.queryOriginalRecordList(sDate, eDate);
    }
}
