package i9.defence.platform.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.service.EquipmentService;
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
}
 