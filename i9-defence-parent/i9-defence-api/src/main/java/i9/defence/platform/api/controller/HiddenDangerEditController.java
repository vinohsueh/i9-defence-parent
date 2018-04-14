package i9.defence.platform.api.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;

import i9.defence.platform.api.components.HiddendangerChannelDataInFoComponents;
import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年4月8日 上午8:52:33 
* 
*/
@RestController
@RequestMapping("hiddenDangerEdit")
public class HiddenDangerEditController {

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EquipmentCategoryService equipmentCategoryService;
	@Autowired
    private ProjectService projectService;
	@Autowired
	private ManagerService managerService;
	/*
     *分页查询
     */
    //@RequiresPermissions("client_list")
    @RequestMapping("/pageHiddenDangerEdit")
    public HashMap<String, Object> pageHiddenDangerEdit(@RequestBody HiddenDangerSearchDto hiddenDangerSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<HiddenDangerDto> pageBounds = equipmentService.selectHiddenDangerByLimitPage(hiddenDangerSearchDto);
        List<EquipmentCategory> equipmentCategory = equipmentCategoryService.serchEqCategory();
        List<Project> project = projectService.findAllProject();
        result.put("data",pageBounds);
        result.put("equipmentCategory",equipmentCategory);
        result.put("project",project);
        return result;
    }
    
    /**
	 * 根据设备编号查找报警隐患 --隐患
	 * @Title: selectHiddenDangerChannelDtoBySid
	 * @Description: TODO
	 * @param systemId
	 * @return
	 */
	@RequestMapping("/selectHiddenDangerChannelDtoBySid")
	public HashMap<String, Object> selectHiddenDangerChannelDtoBySid(@RequestBody String systemId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HiddenDangerChannelDto> list = equipmentService.selectHiddenDangerChannelDtoBySid(systemId);
		JSONArray data = new HiddendangerChannelDataInFoComponents().setHiddenDangerChannelDto(list).build();
		result.put("data", data);
		return result;
	}
    
    /*
     *查询全部
     */
    @RequestMapping("/gatAllHiddenDanger")
    public HashMap<String, Object> gatAllHiddenDanger(@RequestBody HiddenDangerSearchDto hiddenDangerSearchDto) {
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	List<HiddenDangerDto> list = equipmentService.getAllHiddenDanger(hiddenDangerSearchDto);
    	result.put("data",list);
    	return result;
    }
    /**
     * 处理隐患
     * 
     */
    @RequestMapping("/updateDealStatus")
    public HashMap<String, Object> updateDealStatus(@RequestBody DealStatusDto [] dealStatusDtos){
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	Manager manager = managerService.getLoginManager();
    	Integer managerId = manager.getId();
    	Date nowDate = new Date();
    	equipmentService.updateDealStatus(Arrays.asList(dealStatusDtos),managerId,nowDate);
		return result;
    }
    /**
	 * 根据设备编号查找报警隐患 --报警
	 * @Title: 
	 * @Description: TODO
	 * @param systemId
	 * @return
	 */
	@RequestMapping("/selectDangerChannelDtoBySid")
	public HashMap<String, Object> selectDangerChannelDtoBySid(@RequestBody String systemId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HiddenDangerChannelDto> list = equipmentService.selectDangerChannelDtoBySid(systemId);
		JSONArray data = new HiddendangerChannelDataInFoComponents().setHiddenDangerChannelDto(list).build();
		result.put("data", data);
		return result;
	}
}
 