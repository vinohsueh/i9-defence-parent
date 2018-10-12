package i9.defence.platform.datapush.service.impl;

import i9.defence.platform.datapush.controller.ReceiverServiceController;
import i9.defence.platform.datapush.entity.OriginalRecord;
import i9.defence.platform.datapush.respository.OriginalRecordRepository;
import i9.defence.platform.datapush.service.OriginalRecordService;
import i9.defence.platform.datapush.utils.StringHelper;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalRecordServiceImpl implements OriginalRecordService {

    private static Logger logger = LoggerFactory.getLogger(ReceiverServiceController.class);

    @Autowired
    private OriginalRecordRepository originalRecordRepository;

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
}
