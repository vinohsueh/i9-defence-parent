package i9.defence.platform.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
import i9.defence.platform.model.UpStreamDecode;
import i9.defence.platform.service.UpStreamDecodeService;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月19日 上午11:20:56 
* 
*/
@RestController
@RequestMapping("upStreamDecode")
public class UpStreamDecodeController {

	@Autowired
	private UpStreamDecodeService upStreamDecodeService;
	
	 /**
     * 分页查询解密数据
     * @param upStreamDecodeSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/pageUpStreamDecode")
    public HashMap<String, Object> pageUpStreamDecode(@RequestBody UpStreamDecodeSearchDto upStreamDecodeSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<UpStreamDecode> pageBounds = upStreamDecodeService.selectByLimitPage(upStreamDecodeSearchDto);
        result.put("data",pageBounds);
        return result;
    }
    
   /**
    * 添加解密数据
    * @param upStreamDecode
    * @return
    */
    @RequestMapping("/addUpStreamDecode")
    public HashMap<String, Object> addUpStreamDecode(@Valid @RequestBody UpStreamDecode upStreamDecode,BindingResult bindingResult) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        upStreamDecodeService.addUpStreamDecode(upStreamDecode);
        return result;
    }
    /**
     * 删除
     * @param ids
     * @return
     */
     @RequestMapping("/delUpStreamDecode")
     public HashMap<String, Object> delUpStreamDecode(@Valid @NotEmpty(message = "请至少选择一个") @RequestBody List<Integer> ids) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         upStreamDecodeService.deleteUpStreamDecode(ids);
         return result;
     }
}
 