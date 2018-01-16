package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.PageListDto;
import i9.defence.platform.model.ManagerApply;
import i9.defence.platform.model.ManagerApplyExample;
import i9.defence.platform.service.ManagerApplyService;
import i9.defence.platform.utils.PageBounds;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 用户申请controller
 * 创建时间：2018年1月12日 上午9:11:59
 * @author  lby
 * @version  
 * 
 */
@RestController
@RequestMapping("managerApply")
public class ManagerApplyController {
    
    @Autowired
    private ManagerApplyService managerApplyService;
    
    /**
     * 分页查询用户申请
     * @param managerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/pageManagerApply")
    public HashMap<String, Object> pageManagerApply(@RequestBody PageListDto pageListDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        ManagerApplyExample example = new ManagerApplyExample();
        PageBounds<ManagerApply> pageBounds = managerApplyService.selectByLimitPage(example, pageListDto.getCurrentPage(), pageListDto.getPageSize());
        result.put("data",pageBounds);
        return result;
    }
    
    
    /**
     * 删除
     * @param ids
     * @return
     */
     @RequestMapping("/delManagerApply")
     public HashMap<String, Object> delRole(@Valid @NotEmpty(message = "至少选择一个")@RequestBody List<Integer> ids) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         managerApplyService.deleteManagerApply(ids);
         return result;
     }
}
