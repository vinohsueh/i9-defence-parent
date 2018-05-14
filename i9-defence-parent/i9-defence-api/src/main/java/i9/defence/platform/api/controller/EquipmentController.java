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
import i9.defence.platform.api.components.HiddenDangerDtoInfoComponent;
import i9.defence.platform.api.components.MonthDataInfoComponent;
import i9.defence.platform.api.components.ProjcetMonitorComponent;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.MonthData;
import i9.defence.platform.dao.vo.MonthDataDto;
import i9.defence.platform.dao.vo.TotalEquipmentDto;
import i9.defence.platform.enums.DataTypeEnum;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ChannelDataService;
import i9.defence.platform.service.EqSystemCategoryService;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.PassagewayService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.Constants;
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
	@Autowired
	private PassagewayService passagewayService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private EqSystemCategoryService eqSystemCategoryService;
	
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
	 * 删除项目
	 * @param ids
	 * @return
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
	 * @return
	 */
	@RequiresPermissions("equip_list")
	@RequestMapping("/getEquipment")
	public HashMap<String, Object> getEquipment(@RequestBody Integer id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Equipment equipment = equipmentService.getEquipmentById(id);
		List<EquipmentCategory> equCategory = eqCategoryService.selectEqCategory(id);
		result.put("equCategory", equCategory);
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
	 * @Title findEquipmentSystemCategory
	 * @return
	 */
	@RequestMapping("/findEquipmentSystemCategory")
	public HashMap<String, Object> findEquipmentSystemCategory() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		EquipmentCategory equipmentCategory = new EquipmentCategory();
		List<EquipmentCategory> eqCategory = eqCategoryService.serchEqCategory(equipmentCategory);
		List<EquipmentSystemtype> eqSystemCategory=eqSystemCategoryService.findEquipmentSystemCategory();
		List<Project> project = projectService.findAllProject();
		result.put("eqCategory", eqCategory);
		result.put("eqSystemCategory", eqSystemCategory);
		result.put("projects", project);
		return result;
	}
	
	/**
	 * @Title findEquipmentSystemCategory2
	 * @return
	 */
	@RequestMapping("/findEquipmentSystemCategory2")
	public HashMap<String, Object> findEquipmentSystemCategory2(@RequestBody Integer Id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<EquipmentCategory> equipmentCategory=eqCategoryService.findEquipmentSystemCategory2(Id);
		result.put("equipmentCategory", equipmentCategory);
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
		//查询设备创建时间和负责人，安全负责人手机号
		Equipment dataAndManager = equipmentService.selectDataAndManager(equipment.getDeviceId());
		if (dataAndManager.getPhones1() != null) {
			String strings[] = dataAndManager.getPhones1().split(",");
			dataAndManager.setPhones1(strings[0]);
		}
		if (dataAndManager.getName1() != null) {
			String strings[] = dataAndManager.getName1().split(",");
			dataAndManager.setName1(strings[0]);
		}
		result.put("dataAndManager", dataAndManager);
		//根据设备编号查询
		channelDataSearchDto.setDeviceId(equipment.getDeviceId());
		channelDataSearchDto.setOrderByClause("dateTime");
		//只查询电流和温度的显示值
		List<Integer> typeList = new ArrayList<Integer>();
		typeList.add(DataTypeEnum.FLOAT.getId());
		typeList.add(DataTypeEnum.SHORT.getId());
		channelDataSearchDto.setTypes(typeList);
		//隐患报警数量
		HiddenDangerDto hiddenDangerDto = equipmentService.selectHiddenDangerDtoByDeviceId(equipment.getDeviceId());
		JSONObject jObject = new HiddenDangerDtoInfoComponent().setHiddenDangerDto(hiddenDangerDto).build();
		result.put("count", jObject);
		//通道数据
		List<ChannelData> list = channelDataServicel.selectChannelData(channelDataSearchDto);
		//通道对应关系
		List<Passageway> passageWays = passagewayService.selectPassagewaysBySystemId(equipment.getSystemId());
		//分通道处理后的数据
		result.put("data", null);
		if (list.size() > 0) {
			JSONObject jsonObject = new ChannelDataComponent().setChannelDataComponent(list).setPassageways(passageWays).build();
			result.put("data", jsonObject);
		}
		//设备信息
		result.put("equip", new EquipmentMonitorComponent().setEquipment(equipment).build());
		//项目信息
		Project project = projectService.getProjectById(equipment.getProjectId());
		result.put("project", new ProjcetMonitorComponent().setProject(project).build());
		return result;
	}
	
	/**
	 * 查询故障设备
	 * @param equipmentSearchDto
	 * @return
	 */
	@RequestMapping("/selectErrorEquipment")
	public HashMap<String, Object> selectErrorEquipment(@RequestBody EquipmentSearchDto equipmentSearchDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		PageBounds<Equipment> pageBounds = equipmentService.selectErrorEquipment(equipmentSearchDto);
		result.put("data", pageBounds);
		return result;
	}
	
	
	/**
	 * 查询故障记录
	 * @param 
	 * @return
	 */
	@RequestMapping("/selectErrorRecord")
	public HashMap<String, Object> selectErrorRecord(@RequestBody EquipmentSearchDto equipmentSearchDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<ChannelData> list = equipmentService.selectErrorRecord(equipmentSearchDto);
		Equipment equipment = equipmentService.getEquipmentByIdentifier(equipmentSearchDto.getDeviceId());
		List<Passageway> passageways = passagewayService.selectPassagewaysBySystemId(equipment.getSystemId());
		JSONObject jsonObject = new ChannelDataComponent().setChannelDataComponent(list).setPassageways(passageways).errorBuild();
		result.put("data", jsonObject);
		return result;
	}
	
	/**
	 * 查询月统计
	 * @param monthDataDto
	 * @return
	 */
	@RequestMapping("/selectMonthData")
	public HashMap<String, Object> selectMonthData(@RequestBody MonthDataDto monthDataDto){
		HashMap<String, Object> result = new HashMap<String, Object>();
		//报警数据
		List<MonthData> warningData = equipmentService.selectMonthWarningData(monthDataDto);
		//隐患数据
		//List<MonthData> hiddenData = equipmentService.selectHiddenMonthData(monthDataDto);
		List<MonthData> hiddenData = new ArrayList<MonthData>();
		JSONObject jsonObject = new MonthDataInfoComponent().setWarningData(warningData).setHiddenData(hiddenData).build();
		result.put("data", jsonObject);
		return result;
	}
	
	/**
	 * 查询设备数量
	 * @param 
	 * @return
	 */
	@RequestMapping("/selectTotalEquipmentDto")
	public HashMap<String, Object> selectEquipmentNumber(@RequestBody MonthDataDto monthDataDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		//获取登录人
		Manager loginManager = managerService.getLoginManager();
		//如果为经销商和管理员
		if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
			monthDataDto.setDistributorId(loginManager.getId());
		}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
			//如果是项目管理员
			monthDataDto.setProjectManagerId(loginManager.getId());
		}
		TotalEquipmentDto totalEquipmentDto = equipmentService.selectTotalEquipmentDto(monthDataDto);
		result.put("data", totalEquipmentDto);
		return result;
	}
}
