package i9.defence.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.EquipmentFault;
import i9.defence.platform.service.EquipmentFaultService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.PassagewayService;
import i9.defence.platform.service.ThirdPlatformService;
import i9.defence.platform.service.dto.DeviceInfoDto;
import i9.defence.platform.utils.BusinessException;

/*
 * @ClassName: ThirdPlatformServiceImpl 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月14日 上午10:24:40
 */
@Service
public class ThirdPlatformServiceImpl implements ThirdPlatformService {

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private PassagewayService passagewayService;
	@Autowired
	private EquipmentFaultService equipmentFaultService;
	
	@Override
	public DeviceInfoDto selectEquipmentInfo(String text) throws BusinessException {
		Equipment equipment = equipmentService.getEquipmentByIdentifier(text);
		DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
		deviceInfoDto.setId(equipment.getId());
		deviceInfoDto.setProjectId(equipment.getProjectId());
		deviceInfoDto.setRemarks(equipment.getEquipmentRemarks());
		deviceInfoDto.setEquipmentId(equipment.getEquipmentCategoryId());
		return deviceInfoDto;
	}

	@Override
	public Map<String, String> selectDeviceErrors() throws BusinessException {
		Map<String, String> map = new HashMap<String, String>();
		List<EquipmentFault> list = equipmentFaultService.getAllTypes();
		for(EquipmentFault equipmentFault :list) {
			map.put(equipmentFault.getCode() + equipmentFault.getId() , equipmentFault.getName());
		}
		return map;
	}

	@Override
	public List<Integer> selectUsefulChannel(String deviceId) throws BusinessException {
		List<Integer> list = passagewayService.selectChannelByDeviceId(deviceId);
		return list;
	}

}
