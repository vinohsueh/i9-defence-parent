package i9.defence.platform.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.EquipmentFaultSearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.EquipmentFault;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.service.EquipmentFaultService;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月29日 上午11:43:03 
* 设备故障controller
*/
@RestController
@RequestMapping("equipmentFault")
public class EquipmentFaultController {
	
	@Autowired
	private EquipmentFaultService equipmentFaultService;
	@Autowired
	private EquipmentCategoryService equipmentCategoryService;
	/*
     *分页查询
     */
    // @RequiresPermissions("equipmentFault_list")
    @RequestMapping("/pageEquipmentFault")
    public HashMap<String, Object> pageEquipmentFault(@RequestBody EquipmentFaultSearchDto equipmentFaultSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        EquipmentCategory category = new EquipmentCategory();
        List<EquipmentCategory> equipmentCategory = equipmentCategoryService.serchEqCategory(category);
        PageBounds<EquipmentFault> pageBounds = equipmentFaultService.selectByLimitPage(equipmentFaultSearchDto);
        result.put("data",pageBounds);
        result.put("equipmentCategory",equipmentCategory);
        return result;
    }
    /*
     * 添加或者更新
     */
     //@RequiresPermissions("equipmentFault_add")
     @RequestMapping("/updateAndAdd")
     public HashMap<String, Object> updateAndAdd(@Valid @RequestBody EquipmentFault equipmentFault,BindingResult bindingResult) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         equipmentFaultService.add(equipmentFault);
         return result;
     }
     /**
      * 删除
      */
     // @RequiresPermissions("equipmentFault_del")
      @RequestMapping("/deleteBatch")
      public HashMap<String, Object> deleteBatch(@RequestBody List<Integer> ids) {
          HashMap<String, Object> result = new HashMap<String, Object>();
          equipmentFaultService.deleteBatch(ids);
          return result;
      }
      /**
      * id查找
      */
     //@RequiresPermissions("equipmentFault_list")
     @RequestMapping("/getById")
     public HashMap<String, Object> getById(@RequestBody Integer id) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         EquipmentFault equipmentFault = equipmentFaultService.getById(id);
         if(equipmentFault.getActivationOne() != null){
        	 String[]  equipmentFaultStr1=equipmentFault.getActivationOne().split(",");
        	 result.put("activationOneStr", equipmentFaultStr1);
         }
         if(equipmentFault.getActivationTwo() != null){
        	 String[]  equipmentFaultStr2=equipmentFault.getActivationTwo().split(",");
        	 result.put("activationTwoStr", equipmentFaultStr2);
         }
         result.put("data",equipmentFault);
         return result;
     }
}
 