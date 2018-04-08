package i9.defence.platform.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年4月8日 上午8:52:33 
* 
*/
@RestController
@RequestMapping("hiddenEdit")
public class HiddenEditController {

	@Autowired
	private EquipmentService equipmentService;
	
	/*
     *分页查询
     */
    //@RequiresPermissions("client_list")
    @RequestMapping("/pageHiddenEdit")
    public HashMap<String, Object> pageHiddenEdit(@RequestBody HiddenDangerSearchDto hiddenDangerSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<HiddenDangerDto> pageBounds = equipmentService.selectHiddenDangerByLimitPage(hiddenDangerSearchDto);
        result.put("data",pageBounds);
        return result;
    }
}
 