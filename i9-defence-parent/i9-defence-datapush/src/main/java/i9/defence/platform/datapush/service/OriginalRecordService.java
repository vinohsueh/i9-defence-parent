package i9.defence.platform.datapush.service;

import i9.defence.platform.datapush.entity.OriginalRecord;

import java.util.List;

/**
 * 原始消息服务类
 * 
 * @author R12
 * @date 2018年10月22日 14:51:12
 */
public interface OriginalRecordService {

    /**
     * 记录原始消息
     * 
     * @param message
     */
    void saveOriginalRecordMessage(String message);

    /**
     * 查询原始消息列表
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception 
     */
    List<OriginalRecord> getOriginalRecordList(String startDate, String endDate) throws Exception;
}
