package i9.defence.platform.api.controller;

import java.util.Arrays;
import java.util.HashMap;

import i9.defence.platform.dao.vo.ManagerSearchDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.PageBounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 创建时间：2018年1月11日 下午3:29:17
 * @author  lby
 * @version  
 * 
 */
@RestController
@RequestMapping("account")
public class AccountController {
    
    @Autowired
    private ManagerService managerService;
    
    
    
    /**
     * 分页查询账户
     * @param managerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/pageAcount")
    public HashMap<String, Object> pageAcount(@RequestBody ManagerSearchDto managerSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (managerSearchDto.getTypes() == null) {
            managerSearchDto.setTypes(Arrays.asList(Constants.S_ACCOUNT));
        }
        PageBounds<Manager> pageBounds = managerService.selectByLimitPage(managerSearchDto);
        result.put("data",pageBounds);
        return result;
    }
}
