 package i9.defence.platform.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.api.components.ChannelDataComponent;
import i9.defence.platform.api.components.EquipmentMonitorComponent;
import i9.defence.platform.api.components.ProjcetMonitorComponent;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.enums.DataTypeEnum;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ChannelDataService;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.PageBounds;

/**
 * 创建时间：2018年1月9日
 * 
 * @author gbq
 * @version
 */
@RestController
@RequestMapping("equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	EquipmentCategoryService eqCategoryService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ChannelDataService channelDataServicel;

	/**
	 * 分页查询设备列表
	 * @Title:pageEquipment
	 * @param equipmentSearchDto
	 * @param currectPage
	 * @param pageSize
	 * @return
	 */
	@RequiresPermissions("equip_list")
	@RequestMapping("/pageEquipment")
	public HashMap<String, Object> pageEquipment(@RequestBody EquipmentSearchDto equipmentSearchDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		PageBounds<Equipment> pageBounds = equipmentService.selectByLimitPage(equipmentSearchDto);
		result.put("data", pageBounds);
		return result;
	}

	/**
	 * 添加设备
	 * @Title: addEquipment
	 * @param equipment
	 * @return
	 */
	@RequiresPermissions("equip_add")
	@RequestMapping("/addEquipment")
	public HashMap<String, Object> addEquipment(@RequestBody Equipment equipment) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		equipmentService.addEquipment(equipment);
		return result;
	}

	/**
	 * 删除设备
	 * @Title delEquipment
	 * @param ids
	 * @return
	 */
	/*
	 * @RequestMapping("/delEquipment") public HashMap<String, Object>
	 * delEquipment(@RequestBody Integer[] ids) { HashMap<String, Object> result =
	 * new HashMap<String, Object>();
	 * 删除项目
	 * 
	 * @param ids
	 * @return
	 */
	/*
	 * @RequestMapping("/delEquipment") public HashMap<String, Object>
	 * delEquipment(@RequestBody Integer[] ids) { HashMap<String, Object> result
	 * = new HashMap<String, Object>();
	 * equipmentService.deleteEquipment(Arrays.asList(ids)); return result; }
	 */

	/**
	 * 申请删除设备
	 * 
	 * @Title: applyDelEquipment
	 * @Description: TODO
	 * @param ids
	 * @return
	 */
	@RequestMapping("/applyDelEquipment")
	public HashMap<String, Object> applyDelEquipment(@RequestBody List<Integer> ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String msg = equipmentService.applyDelEquipment(ids);
		result.put("msg", msg);
		return result;
	}

	/**
	 * id查找设备
	 * @Title getEquipment
	 * @param equipmentId
	 * id查找设备
	 * @param equipmentId
	 * @return
	 */
	@RequiresPermissions("equip_list")
	@RequestMapping("/getEquipment")
	public HashMap<String, Object> getEquipment(@RequestBody Integer equipmentId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Equipment equipment = equipmentService.getEquipmentById(equipmentId);
		result.put("data", equipment);
		return result;
	}

	/**
	 * 删除设备
	 * @Title delEquipment
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("equip_del")
	@RequestMapping("/delEquipment")
	public HashMap<String, Object> delEquipment(@RequestBody Integer[] ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		equipmentService.deleteEquipment(Arrays.asList(ids));
		return result;
	}

	/**
	 * @Title findEquipment
	 * @param ids
	 * @return
	 */
	@RequestMapping("/findEquipment")
	public HashMap<String, Object> findEquipment() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<EquipmentCategory> eqCategory = eqCategoryService.serchEqCategory();
		List<Project> project = projectService.findAllProject();
		result.put("equCategorys", eqCategory);
		result.put("projects", project);
		return result;
	}

	/**
	 * 根据设备Id查找通道
	 * @Title: selectPassagewayByEid
	 * @Description: TODO
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectPassagewayByEid")
	public HashMap<String, Object> selectPassagewayByEid(String systemId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Passageway> list = equipmentService.selectPassagewayByEid(systemId);
		result.put("data", list);
		return result;
	}

	/**
	 * 增加通道
	 * 
	 * @Title: InsertPassageWay
	 * @Description: TODO
	 * @param passageway
	 * @return
	 */
	@RequestMapping("/insertPassageWay")
	public HashMap<String, Object> insertPassageWay(Passageway passageway) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		equipmentService.insertPassageWay(passageway);
		return result;
	}
	
	/**
	 * 查询设备信息和通道数据
	 * @param equipmentId
	 * @return
	 */
	@RequestMapping("/selectEquipInfoAndData")
	public HashMap<String, Object> selectEquipInfoAndData(@RequestBody ChannelDataSearchDto channelDataSearchDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Equipment equipment = equipmentService.getEquipmentById(channelDataSearchDto.getEquipmentId());
		//根据设备编号查询
		channelDataSearchDto.setSystemId(equipment.getSystemId());
		//只查询电流和温度的显示值
		List<Integer> typeList = new ArrayList<Integer>();
		typeList.add(DataTypeEnum.FLOAT.getId());
		typeList.add(DataTypeEnum.SHORT.getId());
		channelDataSearchDto.setTypes(typeList);
		channelDataSearchDto.setOrderByClause("dateTime desc");
		List<ChannelData> list = channelDataServicel.selectChannelData(channelDataSearchDto);
		//分通道处理后的数据
		result.put("data", null);
		if (list.size() > 0) {
			JSONObject jsonObject = new ChannelDataComponent().setChannelDataComponent(list).build();
			result.put("data", jsonObject);
		}
		result.put("equip", new EquipmentMonitorComponent().setEquipment(equipment).build());
		Project project = projectService.getProjectById(equipment.getProjectId());
		result.put("project", new ProjcetMonitorComponent().setProject(project).build());
		return result;
	}
}
