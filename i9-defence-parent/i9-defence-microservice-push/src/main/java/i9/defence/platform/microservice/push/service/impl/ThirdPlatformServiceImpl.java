package i9.defence.platform.microservice.push.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.microservice.push.repository.ThirdPlatformRepository;
import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceError;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;
import i9.defence.platform.microservice.push.vo.ProjectInfoDto;
import i9.defence.platform.utils.AliyunSMSEnum;
import i9.defence.platform.utils.AliyunUtil;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.TargetDataSource;

/**
 * 与第三方通信接口
 * 
 * @author lby
 * @version
 * 
 */
@Service
@Transactional
public class ThirdPlatformServiceImpl implements ThirdPlatformService {
	
	@Autowired
	private ThirdPlatformRepository thirdPlatformRepository;
	
	@TargetDataSource("hjxfweb")
    @Override
    public void saveAlertOrigin(DeviceInfoDto deviceInfoDto,List<ChannelData> list){
    	try {
    		if (list.size() > 0) {
    			thirdPlatformRepository.insertIntoOldPlat(deviceInfoDto,list);
    		}
		} catch (BusinessException e) {
			throw new BusinessException("建表失败");
		}
    }
    
    /**
     * 每天建当日的表
     */
    @TargetDataSource("hjxfweb")
	@Override
	public void createTableEveryday() {
    	try {
			thirdPlatformRepository.createTableEveryday();
		} catch (SQLException e) {
			throw new BusinessException("建表失败");
		}
	}

	@Override
	public DeviceInfoDto selectEquipmentInfo(String deviceId) {
		try {
			DeviceInfoDto deviceInfoDto = thirdPlatformRepository.selectEquipmentInfo(deviceId);
			return deviceInfoDto;
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"查询设备信息失败");
		}
		
	}

	@Override
	public Map<String, String> selectDeviceErrors() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			List<DeviceError> list = thirdPlatformRepository.selectDeviceErrors();
			for (DeviceError deviceError : list) {
				map.put(deviceError.getCode()+deviceError.getCatagoryId(), deviceError.getName());
			}
			return map;
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"查询设备错误失败");
		}
		
	}
	
	@TargetDataSource("xfjcxt")
	@Override
	public void updateDeviceStatus(int id,int num) throws BusinessException {
		try {
			String status = "01";
			if (num > 0) {
				status = "03";
			}
			thirdPlatformRepository.updateDeviceStatus(id,status);
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"更新设备状态失败");
		}
	}

	@Override
	public List<Integer> selectUsefulChannel(String deviceId) throws BusinessException {
		try {
			return thirdPlatformRepository.selectUsefulChannel(deviceId);
		} catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage(),"查询设备通道失败");
		}
	}

	@Override
	public void AutomaticSendMessage(String deviceId, Integer equipmentType) {
		//发送离线短信
        //1根据deviceId查找设备
        DeviceInfoDto equipment = thirdPlatformRepository.selectEquipmentInfo(deviceId); 
        //2根据ProjectId查找Project
        ProjectInfoDto project = thirdPlatformRepository.selectProjectInfo(equipment.getProjectId());
        //2.1判断发送状态 0：不发送 1：发送
		if(1==project.getSendStatus()) {
			StringBuffer clientNamesBuffer = new StringBuffer("[");
	        StringBuffer clientPhonesBuffer = new StringBuffer("[\"");
	        StringBuffer clientSignNamesBuffer = new StringBuffer("[\"");
	        String[] recipients = project.getRecipients().split(",");
	        String[] recipientphones = project.getRecipientphones().split(",");
	        for(int i=0;i<recipients.length;i++) {
	        	if(i==recipients.length-1) {
	        		clientNamesBuffer.append("{\"name\":\"").append(recipients[i]).append("\","+"\"project\":\""+project.getProjectName()+"\","+"\"postion\":\""+equipment.getRemarks()+"\"").append("}]");
	        		clientPhonesBuffer.append(recipientphones[i]).append("\"]");
	        		clientSignNamesBuffer.append("合极电气").append("\"]");
	        	}else {
	        		clientNamesBuffer.append("{\"name\":\"").append(recipients[i]).append("\","+"\"project\":\""+project.getProjectName()+"\","+"\"postion\":\""+equipment.getRemarks()+"\"},");
	        		clientPhonesBuffer.append(recipientphones[i]).append("\",\"");
	        		clientSignNamesBuffer.append("合极电气").append("\",\"");
	        	}
			}
			//2.2获取设备发送类型(0:报警，1:离线，2：隐患)
			String[] splitType = project.getSendType().split(",");
			//2.3将String数组转换成int数组
			int[] arr =new int[splitType.length];
			for(int i=0;i<splitType.length;i++) {
				arr[i] =Integer.parseInt(splitType[i]);
			}
			//2.4遍历int数组
			for(int h=0;h<arr.length;h++) {
				//2.5若SendType=0并且alertStatus=1则发送报警短信
				if(0==arr[h] && 1 == equipmentType) {
					AliyunUtil.sendInfo(AliyunSMSEnum.WANING, clientPhonesBuffer.toString(), clientNamesBuffer.toString(), clientSignNamesBuffer.toString());
				//2.6若SendType=2并且alertStatus=2则发送隐患短信
				}else if(2==arr[h] && 2 == equipmentType) {
					AliyunUtil.sendInfo(AliyunSMSEnum.HIDDENDANGER, clientPhonesBuffer.toString(), clientNamesBuffer.toString(), clientSignNamesBuffer.toString());
				//2.7若SendType=0并且status=1则发送离线短信
				}
				if(1==arr[h] && 0 == equipmentType) {
					AliyunUtil.sendInfo(AliyunSMSEnum.OUTOFLINE, clientPhonesBuffer.toString(), clientNamesBuffer.toString(), clientSignNamesBuffer.toString());
				}
				
			}
		}		
	}
}
