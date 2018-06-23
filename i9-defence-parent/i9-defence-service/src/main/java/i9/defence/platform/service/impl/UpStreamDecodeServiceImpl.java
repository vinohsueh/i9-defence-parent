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
    public void saveUpStreamDecode(String jsonStr) throws Exception {
    	//添加解析数据
        UpStreamDecode upStreamDecode = new UpStreamDecode();
        upStreamDecode.setHexStr(jsonStr);
        
        //将数据添加到通道数据表中
        
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String systemId = jsonObject.getString("systemId");
        int loop = (int)jsonObject.get("loop");
        String address = jsonObject.getString("deviceAddress");
        upStreamDecode.setChannelId(jsonObject.getString("channelId"));
        //设备唯一编号
        String deviceId = StringUtil.getDeviceId(systemId, loop, address);
        //根据编号id查询设备关注的所有通道
        List<Passageway> passageways = passageWayDao.selectPassagewaysBySystemId(systemId);
        List<Integer> channels = new ArrayList<>();
        HashMap<Integer, HiddenDanger> map = new HashMap<Integer, HiddenDanger>();
        for (Passageway passageway : passageways) {
        	map.put(passageway.getChannel(), passageway.getHiddenDanger());
        	channels.add(passageway.getChannel());
		}
        
        //定义报警的通道数量
        int alertNum = 0;
        //定义隐患的通道数量 
        int hiddenNum = 0;
		//获取通道数据
		List<ChannelData> list = new ArrayList<ChannelData>();
		JSONArray object = (JSONArray) jsonObject.get("dataList");
		for (Object object2 : object) {
			ChannelData channelData = new ChannelData();
			channelData.setSystemId(systemId);
			JSONObject jsonObject2 = (JSONObject)object2;
			int type = (int)jsonObject2.get("type");
			channelData.setType(type);
			int channel = (int)jsonObject2.get("channel");
			channelData.setChannel(channel);
			String value = String.valueOf(jsonObject2.get("value"));
			channelData.setValue(value);
			//如果数据类型是0 且 错误代码不为00000000  时  记录记录
			if (0 == type && !SqlUtil.NORMAL_CODE.equals(value) && channels.contains(channel)) {
				alertNum++;
			}
			//查询是否是隐患数据
			if (Arrays.asList(Constants.DATATYPE).contains(type)) {
				HiddenDanger hiddenDanger = map.get(channel);
				if (Double.valueOf(value)>hiddenDanger.getHiddenMax() || Double.valueOf(value)<hiddenDanger.getHiddenMin()){
					hiddenNum++;
				}
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date createTime = simpleDateFormat.parse((String)(jsonObject2.get("datetime").toString().replace("#", " ")));
			channelData.setDateTime(createTime);
			
			channelData.setSystemType((String)jsonObject.get("systemType"));
			channelData.setDeviceAddress(address);
			
			channelData.calDeviceId(EncryptUtils.bytesToHexString(EncryptUtils.intToBytes(loop)));
			list.add(channelData);
		}
		String submitDate = jsonObject.getString("submitDate");
		if (submitDate == null || submitDate.equals("")) {
		    upStreamDecode.setSubmitDate(new Date());
		}
		else {
            Date date = DateUtils.parseDate(submitDate);
		    upStreamDecode.setSubmitDate(date);
		}
		try {
			this.addUpStreamDecode(upStreamDecode);
			channelDataDao.insertBatch(list);
			Equipment equipment = equipmentDao.findEquipmentDeviceId(deviceId);
			//设置 设备当前的 数据状态
			int datastatus = 0;
			if (alertNum > 0) {
				datastatus = 1;
			}else if (0 == alertNum && hiddenNum > 0) {
				datastatus = 2;
			}
			//设置 设备未处理的状态   
			int alertStatus = 0;
			//设备的遗留状态
			int equipmentRemainStatus = equipment.getRemainAlert();
			//如果遗留状态为正常
			if (0 == equipmentRemainStatus) {
				if (alertNum > 0) {
					alertStatus = 1;
				}else if (0 == alertNum && hiddenNum > 0) {
					alertStatus = 2;
				}
			}else if (1 == equipmentRemainStatus){
				//如果遗留状态为报警
				alertStatus = 1;
			}else if (2 == equipmentRemainStatus){
				//如果遗留状态为隐患
				if (alertNum > 0) {
					alertStatus = 1;
				}else{
					alertStatus = 2;
				}
			}
			//更新设备的数据状态
			equipmentDao.updateEquipmentDataStatus(deviceId,datastatus,alertStatus);
			
			//插入设备问题记录
			if (0 != datastatus) {
				ErrorRecord errorRecord = new ErrorRecord();
				errorRecord.setCreateTime(new Date());
				errorRecord.setDeviceId(deviceId);
				errorRecord.setType(datastatus);
				errorRecordDao.insertErrorRecord(errorRecord);
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
    }

	@Override
	public void updateEquipmentStatus(String deviceId, int status) {
		equipmentDao.updateEquipmentStatusByDeviceId(deviceId,status);
	}

	@Override
	public void insertConnectRecord(ConnectLog connectLog) {
		connectLogDao.add(connectLog);
	}
}
