package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.service.ApplyService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.PageBounds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 创建时间：2018年1月9日
 * @author gbq
 * @version  
 */
@RestController
@RequestMapping("equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private ApplyService applyService;
	@Autowired
	private ManagerService managerService;
	
	/**
     * 分页查询项目列表
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
	@RequestMapping("/pageEquipment")
	public HashMap<String, Object> pageEquipment(@RequestBody EquipmentSearchDto equipmentSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<Equipment> pageBounds = equipmentService.selectByLimitPage(equipmentSearchDto);
        result.put("data",pageBounds);
        return result;
    }
	
	/**
	    * 添加项目
	    * @param equipment
	    * @return
	    */
	    @RequestMapping("/addEquipment")
	    public HashMap<String, Object> addEquipment(@RequestBody Equipment equipment) {
	        HashMap<String, Object> result = new HashMap<String, Object>();
	        equipmentService.addEquipment(equipment);
	        return result;
	    }
    /**
	     * id查找项目
	     * @param equipmentId
	     * @return
	     */
	    @RequestMapping("/getEquipment")
	    public HashMap<String, Object> getEquipment(@RequestBody Integer equipmentId) {
	        HashMap<String, Object> result = new HashMap<String, Object>();
	        Equipment equipment = equipmentService.getEquipmentById(equipmentId);
	        result.put("data",equipment);
	        return result;
	    }
    /**
	    * 删除项目
	    * @param ids
	    * @return
	    */
	    @RequestMapping("/delEquipment")
	    public HashMap<String, Object> delEquipment(@RequestBody Integer[] ids) {
	        HashMap<String, Object> result = new HashMap<String, Object>();
		    equipmentService.deleteEquipment(Arrays.asList(ids));
	        return result;
	    }
	    
	    /**
	     * 申请删除设备
	    * @Title: applyDelEquipment 
	    * @Description: TODO
	    * @param ids
	    * @return
	     */
	    @RequestMapping("/applyDelEquipment")
	    public HashMap<String, Object> applyDelEquipment(@RequestBody List<Integer> ids){
	    	 HashMap<String, Object> result = new HashMap<String, Object>();
	    	 equipmentService.applyDelEquipment(ids);
	    	 return result;
	    }
}