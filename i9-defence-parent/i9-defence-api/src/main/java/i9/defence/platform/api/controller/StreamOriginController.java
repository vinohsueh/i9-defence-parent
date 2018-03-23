package i9.defence.platform.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.StreamOriginSearchDto;
import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.service.StreamOriginService;
import i9.defence.platform.utils.PageBounds;

/**
 * 创建时间：2018年1月9日
 * 
 * @author gbq
 * @version
 */
@RestController
@RequestMapping("streamOrigin")
public class StreamOriginController {
	
	@Autowired
	private StreamOriginService streamOriginService;
	
	 /**
     * 分页查询设备列表
     * 
     * @param equipmentSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
//    @RequiresPermissions("streamOrgin_list")
    @RequestMapping("/pageStreamOrigin")
    public HashMap<String, Object> pageStreamOrigin(
            @RequestBody StreamOriginSearchDto streamOriginSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        streamOriginSearchDto.setOrderByClause("submitDate desc");
        PageBounds<StreamOrigin> pageBounds = streamOriginService.selectByLimitPage(streamOriginSearchDto);
        result.put("data", pageBounds);
        return result;
    }
}
