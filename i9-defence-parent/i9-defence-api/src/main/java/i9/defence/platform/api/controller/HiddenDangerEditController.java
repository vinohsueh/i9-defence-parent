package i9.defence.platform.api.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;

import i9.defence.platform.api.components.HiddendangerChannelDataInFoComponents;
import i9.defence.platform.dao.vo.ChannelDataQuery;
import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年4月8日 上午8:52:33 
* 
*/
@RestController
@RequestMapping("hiddenDangerEdit")
public class HiddenDangerEditController {

    private final static Logger S_LOGER = LoggerFactory.getLogger(HiddenDangerEditController.class);
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private ManagerService managerService;
	/*
     *分页查询
     */
    @RequestMapping("/pageHiddenDangerEdit")
    public HashMap<String, Object> pageHiddenDangerEdit(@RequestBody HiddenDangerSearchDto hiddenDangerSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<HiddenDangerDto> pageBounds = equipmentService.selectHiddenDangerByLimitPage(hiddenDangerSearchDto);
        result.put("data",pageBounds);
        return result;
    }
    
    
	/*
     *分页查询
     */
    @RequestMapping("/pageHiddenDangerEdit2")
    public HashMap<String, Object> pageHiddenDangerEdit2(@RequestBody HiddenDangerSearchDto hiddenDangerSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<HiddenDangerDto> pageBounds = equipmentService.selectHiddenDangerByLimitPage2(hiddenDangerSearchDto);
        result.put("data",pageBounds);
        return result;
    }
    
    /**
     * 查询全部
     * @param hiddenDangerSearchDto
     * @return 
     */
    @RequestMapping("/selectAllHiddenDangerEdit")
    public HashMap<String, Object> selectAllHiddenDangerEdit(@RequestBody HiddenDangerSearchDto hiddenDangerSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<HiddenDangerDto> list = equipmentService.selectAllHiddenDangerEdit(hiddenDangerSearchDto);
        result.put("data",list);
        return result;
    }
    /**
	 * 根据设备编号查找报警隐患 --隐患
	 * @Title: selectHiddenDangerChannelDtoBySid
	 * @Description: TODO
	 * @param systemId
	 * @return
	 */
	@RequestMapping("/selectHiddenDangerChannelDtoBySid")
	public HashMap<String, Object> selectHiddenDangerChannelDtoBySid(@RequestBody ChannelDataQuery channelDataQuery) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HiddenDangerChannelDto> list = equipmentService.selectHiddenDangerChannelDtoBySid(channelDataQuery.getDeviceId(),channelDataQuery.getCount());
		JSONArray data = new HiddendangerChannelDataInFoComponents().setHiddenDangerChannelDto(list).build();
		result.put("data", data);
		return result;
	}
    
    /*@RequestMapping("/gatAllHiddenDanger")
    public HashMap<String, Object> gatAllHiddenDanger(@RequestBody HiddenDangerSearchDto hiddenDangerSearchDto) {
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	List<HiddenDangerDto> list = equipmentService.getAllHiddenDanger(hiddenDangerSearchDto);
    	result.put("data",list);
    	return result;
    }*/
    /**
     * 处理隐患
     * 
     */
    @RequestMapping("/updateDealStatus")
    public HashMap<String, Object> updateDealStatus(@RequestBody DealStatusDto [] dealStatusDtos){
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	Manager manager = managerService.getLoginManager();
    	Integer managerId = manager.getId();
    	Date nowDate = new Date();
    	equipmentService.updateDealStatus(Arrays.asList(dealStatusDtos),managerId,nowDate);
		return result;
    }
    /**
	 * 根据设备编号查找报警隐患 --报警
	 * @Title: 
	 * @Description: TODO
	 * @param systemId
	 * @return
	 */
	@RequestMapping("/selectDangerChannelDtoBySid")
	public HashMap<String, Object> selectDangerChannelDtoBySid(@RequestBody ChannelDataQuery channelDataQuery) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HiddenDangerChannelDto> list = equipmentService.selectDangerChannelDtoBySid(channelDataQuery.getDeviceId(),channelDataQuery.getCount());
		for(HiddenDangerChannelDto dto : list){
		    String channelValue = dto.getChannelValue();
		    System.out.println(channelValue);
		}
		JSONArray data = new HiddendangerChannelDataInFoComponents().setHiddenDangerChannelDto(list).build();
		result.put("data", data);
		return result;
	}
	
	
	 /**
     * excel导出
     * @param response
     */
    @RequestMapping(value = "/excelTo", method = RequestMethod.GET)
    @ResponseBody
    public void downSaleLoadExportToExcel(HiddenDangerSearchDto hiddenDangerSearchDto,HttpServletRequest request,HttpServletResponse response){  
        response.reset();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssms");
        String dateStr = sdf.format(new Date());
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + dateStr + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook = null;
        // 导出Excel对象
        try {
            workbook = equipmentService.downLoadExportToExcel(hiddenDangerSearchDto);
        } catch (BusinessException exception) {
            S_LOGER.error(exception.getErrorMessage());
        }
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 