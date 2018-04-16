package i9.defence.platform.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.ChannelDataLimitPageDto;
import i9.defence.platform.dao.vo.ChannelDataSearchDto;
import i9.defence.platform.service.ChannelDataService;
import i9.defence.platform.utils.PageBounds;

/**
 * 通道数据
 * @ClassName: ChannelDateController 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月13日 下午11:41:00
 */
@RestController
@RequestMapping("/channel")
public class ChannelDataController {
	
	@Autowired
	private ChannelDataService channelDataService;
	
    @RequestMapping("/pageChannel")
    public HashMap<String, Object> pageChannel(@RequestBody ChannelDataSearchDto channelDataSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<ChannelDataLimitPageDto> pageBounds = channelDataService.selectByLimitPage(channelDataSearchDto,channelDataSearchDto.getCurrentPage(),channelDataSearchDto.getPageSize());
        result.put("data",pageBounds);
        return result;
    }

}
