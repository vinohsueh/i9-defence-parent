package i9.defence.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.dao.ChannelDataDao;
import i9.defence.platform.dao.ConnectLogDao;
import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.ErrorRecordDao;
import i9.defence.platform.dao.PassageWayDao;
import i9.defence.platform.dao.UpStreamDecodeDao;
import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.ErrorRecord;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.model.UpStreamDecode;
import i9.defence.platform.service.UpStreamDecodeService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.DateUtils;
import i9.defence.platform.utils.EncryptUtils;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.SqlUtil;
import i9.defence.platform.utils.StringUtil;

/**
 * @author user: jiace
 * @version creatTime：2018年3月19日 上午11:14:14
 * 
 */
@Service
@Transactional
public class UpStreamDecodeServiceImpl implements UpStreamDecodeService {

    @Autowired
    private UpStreamDecodeDao upStreamDecodeDao;
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private ChannelDataDao channelDataDao;
    @Autowired
    private ConnectLogDao connectLogDao;
    @Autowired
    private PassageWayDao passageWayDao;
    @Autowired
    private ErrorRecordDao errorRecordDao;
    

    @Override
    public void addUpStreamDecode(UpStreamDecode upStreamDecode) throws BusinessException {
        try {
            upStreamDecode.setSubmitDate(new Date());
            upStreamDecodeDao.addUpStreamDecode(upStreamDecode);
        } catch (Exception e) {
            throw new BusinessException("添加解密数据失败", e.getMessage());
        }
    }

    @Override
    public PageBounds<UpStreamDecode> selectByLimitPage(UpStreamDecodeSearchDto upStreamDecodeSearchDto)
            throws BusinessException {
        try {
            return upStreamDecodeDao.selectByLimitPage(upStreamDecodeSearchDto,
                    upStreamDecodeSearchDto.getCurrentPage(), upStreamDecodeSearchDto.getPageSize());
        } catch (Exception e) {
            throw new BusinessException("分页查询解密数据失败", e.getMessage());
        }
    }

    @Override
    public void deleteUpStreamDecode(List<Integer> ids) throws BusinessException {
        try {
            upStreamDecodeDao.deleteUpStreamDecode(ids);
        } catch (Exception e) {
            throw new BusinessException("删除解密数据失败", e.getMessage());
        }
    }

    @Override
    public int saveUpStreamDecode(String jsonStr) throws Exception {
        // 添加解析数据
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        saveToDbStoreUpStreamDecode(jsonStr, jsonObject);
        
        //设备唯一编号
        String systemId = jsonObject.getString("systemId");
        int loop = (int)jsonObject.get("loop");
        String address = jsonObject.getString("deviceAddress");
        String deviceId = StringUtil.getDeviceId(systemId, loop, address);
        
        //根据编号id查询设备关注的所有通道
        List<Integer> channels = new ArrayList<>();
        HashMap<Integer, HiddenDanger> map = new HashMap<Integer, HiddenDanger>();
        for (Passageway passageway 
                : passageWayDao.selectPassagewaysBySystemId(systemId)) {
            map.put(passageway.getChannel(), passageway.getHiddenDanger());
            channels.add(passageway.getChannel());
        }
        
        // 获取通道数据
        List<ChannelData> channelDatas = new ArrayList<ChannelData>();
        JSONArray dataList = jsonObject.getJSONArray("dataList");
        String systemType = jsonObject.getString("systemType");
        for (int index = 0; index < dataList.size(); index++) {
            JSONObject dataItem = dataList.getJSONObject(index);
            int type = dataItem.getIntValue("type");
            int channel = dataItem.getIntValue("channel");
            String value = String.valueOf(dataItem.getString("value"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createTime = simpleDateFormat.parse((String)(dataItem.get("datetime").toString().replace("#", " ")));
            // 创建一个ChannelData对象
            ChannelData channelData = buildChannelData(systemId, systemType, address, loop, type, channel, value, createTime);
            
            // 将ChannelData数据保存到数据库
            channelDatas.add(channelData);
        }
        try {
            if (!channelDatas.isEmpty()) {
                channelDataDao.insertBatch(channelDatas);
                channelDataDao.insertEquipmentRecents(channelDatas);
            }
        } catch (Exception e) {
        }

        // 定义报警的通道数量
        int alertNum = 0;
        // 定义隐患的通道数量
        int hiddenNum = 0;

        for (ChannelData channelData : channelDatas) {
            int type = channelData.getType();
            String value = channelData.getValue();
            int channel = channelData.getChannel();
            // 如果数据类型是0 且 错误代码不为00000000 时 记录记录
            if (0 == type && !SqlUtil.NORMAL_CODE.equals(value) && channels.contains(channel)) {
                alertNum++;
            }
            // 查询是否是隐患数据
            boolean contains = Arrays.asList(Constants.DATATYPE).contains(type);
            List<Integer> asList = Arrays.asList(Constants.DATATYPE);
            System.out.println(asList);
            System.out.println(contains);
            if (contains) {
                HiddenDanger hiddenDanger = map.get(channel);
                if (hiddenDanger != null) {
                    if (Double.valueOf(value) > hiddenDanger.getHiddenMax()
                            || Double.valueOf(value) < hiddenDanger.getHiddenMin()) {
                        hiddenNum++;
                    }
                }
            }
        }
        try { 
            Equipment equipment = equipmentDao.findEquipmentDeviceId(deviceId);
            // 设置 设备当前的数据状态
            int dataStatus = 0;
            if (alertNum > 0) {
                dataStatus = 1;
            } else if (0 == alertNum && hiddenNum > 0) {
                dataStatus = 2;
            }
            // 设置设备未处理的状态
            int alertStatus = 0;
            // 设备的遗留状态
            int equipmentRemainStatus = equipment.getRemainAlert();
            
            
            // 如果遗留状态为正常
            if (0 == equipmentRemainStatus) {
                if (alertNum > 0) {
                    alertStatus = 1;
                } else if (0 == alertNum && hiddenNum > 0) {
                    alertStatus = 2;
                }
            } else if (1 == equipmentRemainStatus) {
                // 如果遗留状态为报警
                alertStatus = 1;
            } else if (2 == equipmentRemainStatus) {
                // 如果遗留状态为隐患
                if (alertNum > 0) {
                    alertStatus = 1;
                } else {
                    alertStatus = 2;
                }
            }
            String newsEventTime =null; 
            if(0 !=alertStatus) {
            	newsEventTime = StringUtil.dateToString(new Date());
            }
            // 更新设备的数据状态
            equipmentDao.updateEquipmentDataStatus(deviceId, dataStatus, alertStatus,newsEventTime);

            // 插入设备问题记录
            if (0 != dataStatus) {
                ErrorRecord errorRecord = new ErrorRecord();
                errorRecord.setCreateTime(new Date());
                errorRecord.setDeviceId(deviceId);
                errorRecord.setType(dataStatus);
                errorRecordDao.insertErrorRecord(errorRecord);
//                equipmentDao.updateEquipmentNewestTime(deviceId,StringUtil.dateToString(new Date()));
            }
            return dataStatus;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 构建一个ChannelData对象
     * 
     * @param systemId
     * @param systemType
     * @param address
     * @param loop
     * @param type
     * @param channel
     * @param value
     * @param createTime
     * @return
     */
    public ChannelData buildChannelData(String systemId, String systemType, String address, int loop, int type, int channel,
            String value, Date createTime) {
        ChannelData channelData = new ChannelData();
        channelData.setSystemId(systemId);
        channelData.setSystemType(systemType);
        channelData.setDeviceAddress(address);

        channelData.setType(type);
        channelData.setChannel(channel);
        channelData.setValue(value);
        channelData.setDateTime(createTime);

        channelData.calDeviceId(EncryptUtils.bytesToHexString(EncryptUtils.intToBytes(loop)));
        return channelData;
    }

    /**
     * 解析数据包存入数据库中
     * @param jsonStr
     * @param jsonObject
     */
    public void saveToDbStoreUpStreamDecode(String jsonStr, JSONObject jsonObject) {
        try {
            UpStreamDecode upStreamDecode = new UpStreamDecode();
            upStreamDecode.setChannelId(jsonObject.getString("channelId"));
            String submitDate = jsonObject.getString("submitDate");
            if (submitDate == null || submitDate.equals("")) {
                upStreamDecode.setSubmitDate(new Date());
            } else {
                Date date = DateUtils.parseDate(submitDate);
                upStreamDecode.setSubmitDate(date);
            }
            upStreamDecode.setHexStr(jsonStr);
            // 将数据添加到通道数据表中
            this.addUpStreamDecode(upStreamDecode);
        } catch (Exception e) {
        }
    }

    @Override
    public void updateEquipmentStatus(String deviceId, int status) {
        equipmentDao.updateEquipmentStatusByDeviceId(deviceId, status);
    }

    @Override
    public void insertConnectRecord(ConnectLog connectLog) {
        connectLogDao.add(connectLog);
    }

    @Override
    public void updateEquipmentNewestTime(String deviceId, String dateTime) {
        try {
            equipmentDao.updateEquipmentNewestTime(deviceId,dateTime);
        } catch (Exception e) {
        }
    }
}
