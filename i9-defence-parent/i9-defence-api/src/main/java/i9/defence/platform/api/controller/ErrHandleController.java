package i9.defence.platform.api.controller;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.dao.vo.ErrHandleUnifiedDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ErrHandleService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;

@RestController
@RequestMapping("errHandle")
public class ErrHandleController {

	@Autowired
	private ErrHandleService errHandleService;
	
	@Autowired
	private ManagerService managerService;
	
	/**
     * 分页查询
     * @param projectSearchDto
     * @return
     */
    @RequestMapping("/pageErrHandle")
    public HashMap<String, Object> pageErrHandle(
            @RequestBody ErrHandleSearchDto errHandleSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        Role role = manager.getRole();
        if(Arrays.asList(Constants.S_AGENCY).contains(role.getName())){
        	errHandleSearchDto.setManagerId(manager.getId());
        }
        PageBounds<ErrHandle> pageBounds = errHandleService
                .selectByLimitPage(errHandleSearchDto);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     *批量处理设备故障 type = 1 故障 或者 设备报警 type = 2 报警  或者 设备隐患 type = 2 隐患
     */
    @RequestMapping("/handlingErrors")
    public HashMap<String, Object> handlingErrors(@RequestBody ErrHandleUnifiedDto errHandleUnifiedDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        errHandleService.handlingErrors(errHandleUnifiedDto);
        return result;
    }
    
    //批量删除记录
    @RequestMapping("/deleteErrHandle")
    public HashMap<String, Object> deleteErrHandle(@RequestBody Integer[] ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        errHandleService.deleteErrHandle(Arrays.asList(ids));
        return result;
    }
    
    //根据ID查询记录
    @RequestMapping("/getErrHandleById")
    public HashMap<String, Object> getErrHandleById(@RequestBody Integer id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        ErrHandle errHandle = errHandleService.getErrHandleById(id);
        result.put("data", errHandle);
        return result;
    }
}
