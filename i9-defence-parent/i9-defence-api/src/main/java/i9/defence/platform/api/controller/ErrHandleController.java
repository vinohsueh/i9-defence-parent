package i9.defence.platform.api.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

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

import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.dao.vo.ErrHandleUnifiedDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.service.ErrHandleService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

@RestController
@RequestMapping("errHandle")
public class ErrHandleController {

    private final static Logger S_LOGER = LoggerFactory.getLogger(ErrHandleController.class);
    
	@Autowired
	private ErrHandleService errHandleService;
	
	/**
     * 分页查询
     * @param projectSearchDto
     * @return
     */
    @RequestMapping("/pageErrHandle")
    public HashMap<String, Object> pageErrHandle(
            @RequestBody ErrHandleSearchDto errHandleSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
      /*  Manager manager = managerService.getLoginManager();
        Role role = manager.getRole();
        if(Arrays.asList(Constants.S_AGENCY).contains(role.getName())){
        	errHandleSearchDto.setManagerId(manager.getId());
        }*/
        PageBounds<ErrHandle> pageBounds = errHandleService.selectByLimitPage(errHandleSearchDto);
        result.put("data", pageBounds);
        return result;
    }
    
    /**
     *批量处理 设备报警 type = 2 报警  或者 设备隐患 type = 3 隐患
     */
    @RequestMapping("/handlingErrors")
    public HashMap<String, Object> handlingErrors(@RequestBody ErrHandleUnifiedDto errHandleUnifiedDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        errHandleService.handlingErrors(errHandleUnifiedDto);
        return result;
    }
    
    /**
     * 批量更新  超过6天的  未处理的type = 2 报警  或者 设备隐患 type = 3 隐患
    * @Title: errHandleEdit 
    * @Description: TODO
    * @param errHandleUnifiedDto
    * @return
     */
    @RequestMapping("/errHandleEdit")
    public HashMap<String, Object> errHandleEdit(@RequestBody ErrHandleUnifiedDto errHandleUnifiedDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        errHandleService.errHandleEdit(errHandleUnifiedDto);
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
    
    
    /**
     * excel导出
     * @param response
     */
    @RequestMapping(value = "/excelTo", method = RequestMethod.GET)
    @ResponseBody
    public void downSaleLoadExportToExcel(HttpServletRequest request,HttpServletResponse response){  
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
            workbook = errHandleService.downLoadExportToExcel();
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
