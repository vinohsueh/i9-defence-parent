 package i9.defence.platform.api.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.api.components.ChannelDataComponent;
import i9.defence.platform.api.components.MonthDataInfoComponent;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.dao.vo.EquipmentNewestDto;
import i9.defence.platform.dao.vo.EquipmentProjectDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
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
import i9.defence.platform.utils.StringUtil;

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
		Equipment equipment2 = equipmentService.addEquipment(equipment);
		Equipment equipmentToOldPlat = equipmentService.getEquipmentById(equipment2.getId());
		//向老平台插入设备
		equipmentService.addEquipmentToOldPlat(equipmentToOldPlat);
		return result;
	}

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
		List<EquipmentCategory> eqCategory = eqCategoryService.findEqCategoryName();
		List<EquipmentSystemtype> eqSystemCategory=eqSystemCategoryService.findEquipmentSystemCategory();
		List<Project> project = projectService.findProjectName();
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
		//查询设备创建时间和负责人，安全负责人手机号
		EquipmentProjectDto dataAndManager = equipmentService.selectDataAndManager(channelDataSearchDto.getEquipmentId());
		result.put("dataAndManager", dataAndManager);
		//根据设备编号查询
		channelDataSearchDto.setDeviceId(dataAndManager.getDeviceId());
		channelDataSearchDto.setOrderByClause("dateTime");
		channelDataSearchDto.setSystemId(dataAndManager.getSystemId());
		//只查询电流和温度的显示值
		List<Integer> typeList = new ArrayList<Integer>();
		typeList.add(DataTypeEnum.FLOAT.getId());
		typeList.add(DataTypeEnum.SHORT.getId());
		channelDataSearchDto.setTypes(typeList);
		//通道数据
		List<ChannelData> list = channelDataServicel.selectChannelData(channelDataSearchDto);
		//通道对应关系
		List<Passageway> passageWays = passagewayService.selectPassagewaysBySystemId(dataAndManager.getSystemId());
		//分通道处理后的数据
		result.put("data", null);
		if (list.size() > 0) {
			JSONObject jsonObject = new ChannelDataComponent().setChannelDataComponent(list).setPassageways(passageWays).build();
			result.put("data", jsonObject);
		}
		//设备信息
		result.put("equip", dataAndManager);
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
		//获取登录人
		Manager loginManager = managerService.getLoginManager();
		//如果为经销商和管理员
		if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
			monthDataDto.setDistributorId(loginManager.getId());
		}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
			//如果是项目管理员
			monthDataDto.setProjectManagerId(loginManager.getId());
		}
		//报警数据
		List<MonthData> warningData = equipmentService.selectMonthWarningData(monthDataDto);
		//隐患数据
		List<MonthData> hiddenData = equipmentService.selectHiddenMonthData(monthDataDto);
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
	
	/**
	 * 更新所有设备的status
	* @Title: updateAllEquipmentStatus 
	* @Description: TODO
	* @return
	 */
	@RequestMapping("/updateAllEquipmentStatus")
	public HashMap<String, Object> updateAllEquipmentStatus(){
	    HashMap<String, Object> result = new HashMap<String, Object>();
	    equipmentService.updateAllEquipmentStatus();
	    result.put("code",0);
	    return result;
	}
	
	@RequestMapping("/selectAllEquipmentNewest")
	public HashMap<String, Object> selectAllEquipmentNewest() throws ParseException{
	    HashMap<String, Object> result = new HashMap<String, Object>();
	  //查询所有设备最新事件时间
        List<EquipmentNewestDto> list = equipmentService.selectAllEquipmentNewest();
        //新建异常设备id
        ArrayList<String> eqDeviceIdList = new  ArrayList<String>(); 
        //获取当前时间
        Date nowDate = new Date();
        for(EquipmentNewestDto equipment :list) {  
            Date equipEventTime = StringUtil.StringToDateS(equipment.getNewEventTime());
            //获取相差分钟数
            long min =(nowDate.getTime()-equipEventTime.getTime())/(1000*60);
            if(min>=8) {
                eqDeviceIdList.add(equipment.getEqDeviceId());
            }
        }
        equipmentService.updateSomeStatusByDevicedIds(eqDeviceIdList);
        return result;
    }
	
}
