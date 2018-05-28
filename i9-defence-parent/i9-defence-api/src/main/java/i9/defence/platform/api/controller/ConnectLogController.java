package i9.defence.platform.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.ConnectLogDto;
import i9.defence.platform.service.ConnectLogService;
import i9.defence.platform.utils.PageBounds;

/**
 * 创建时间：2018年5月16日
 * 
 * @author gbq
 * @version
 */
@RestController
@RequestMapping("/connectLog")
public class ConnectLogController {
	 @Autowired
	    private ConnectLogService connectLogService;
	
	 /**
	     * 分页查询设备连接日志
	     * 
	     * @param connectLogDto
	     * @param currectPage
	     * @param pageSize
	     * @return
	     */
	    @RequestMapping("/pageConnectLog")
	    public HashMap<String, Object> pageConnectLog(@RequestBody ConnectLogDto connectLogDto) {
	        HashMap<String, Object> result = new HashMap<String, Object>();
	        PageBounds<ConnectLogDto> pageBounds = connectLogService.selectByLimitPage(connectLogDto);
	        result.put("data", pageBounds);
	        return result;
	    }
}
