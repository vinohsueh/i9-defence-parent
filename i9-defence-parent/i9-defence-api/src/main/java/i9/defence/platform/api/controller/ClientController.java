package i9.defence.platform.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.api.components.ClientSearchComponent;
import i9.defence.platform.dao.vo.ClientSearchDto;
import i9.defence.platform.dao.vo.SendMessageDto;
import i9.defence.platform.enums.AliyunCodeTypeEnum;
import i9.defence.platform.model.Client;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.MessageLog;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ClientService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.service.impl.MessageLogService;
import i9.defence.platform.utils.AliyunSMSEnum;
import i9.defence.platform.utils.AliyunUtil;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.StringUtil;

/**
 * @author : JiaCe
 * @version ：2018年1月11日下午3:56:05 客户controller
 */
@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private MessageLogService messageLogService;
    
    /*
     * 分页查询
     */
    @RequiresPermissions("client_list")
    @RequestMapping("/pageClient")
    public HashMap<String, Object> pageManager(@RequestBody ClientSearchDto clientSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        clientSearchDto.setCreateId(manager.getId());
        PageBounds<Client> pageBounds = clientService.selectByLimitPage(clientSearchDto);
        result.put("data", pageBounds);
        return result;
    }

    /*
     * 添加或者更新
     */
    @RequiresPermissions("client_add")
    @RequestMapping("/updateAndAdd")
    public HashMap<String, Object> updateAndAdd(@Valid @RequestBody Client client, BindingResult bindingResult) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        client.setCreateId(manager.getId());
        clientService.updateAndAdd(client);
        return result;
    }

    /**
     * 删除
     */
    @RequiresPermissions("client_del")
    @RequestMapping("/deleteBatch")
    public HashMap<String, Object> deleteBatch(@RequestBody List<Integer> ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        clientService.deleteBatch(ids);
        return result;
    }

    /**
     * id查找
     */
    @RequiresPermissions("client_list")
    @RequestMapping("/getClientById")
    public HashMap<String, Object> getClientById(@RequestBody Integer id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Client client = clientService.getById(id);
        result.put("data", client);
        result.put("AliyunSMSEnum", AliyunSMSEnum.getShowMenuList());
        return result;
    }

    /*
     * 获取全部项目
     */
    @RequestMapping("/getAllProject")
    public HashMap<String, Object> getAllProject() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Project> list = projectService.findAllProject();
        result.put("data", list);
        return result;
    }

    /**
     * id查询所有客户（不分页查询)
     */
    @RequiresPermissions("client_list")
    @RequestMapping("/getAllClientById")
    public HashMap<String, Object> getAllClientById() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getLoginManager();
        List<Client> list = clientService.selectByCreateId(manager.getId());
        JSONArray jsonArray = new JSONArray();
        for (Client Client : list) {
            JSONObject jsonObject = new ClientSearchComponent().setClient(Client).build();
            jsonArray.add(jsonObject);
        }
        result.put("allManger", jsonArray);
        result.put("AliyunSMSEnums", AliyunSMSEnum.getShowMenuListName());
        return result;
    }
    
    @RequiresPermissions("send_message")
    @RequestMapping("/sendMessage")
    public HashMap<String, Object> sendMessage(@RequestBody SendMessageDto sendMessageDto) {
    	HashMap<String, Object> result = new HashMap<String, Object>();
    	AliyunSMSEnum aliyunSMSEnumByTemplateNum = AliyunSMSEnum.getAliyunSMSEnumByName(sendMessageDto.getTemplateNum());
    	sendMessageDto.setAliyunSMSEnum(aliyunSMSEnumByTemplateNum);
        List<String> phonsList = new ArrayList<String>();
        List<String> signNameList = new ArrayList<String>();
        List<String> namesList = new ArrayList<String>();
        List<Client> clientsByIds = clientService.selectClientsByIds(Arrays.asList(sendMessageDto.getClientIdList()));
        for (int i = 0; i < clientsByIds.size(); i++) {
        	phonsList.add(clientsByIds.get(i).getPhone());
        	signNameList.add("合极电气");
        	namesList.add("{}");
        }
        MessageLog messageLog = new MessageLog();
        try {
        	String ResultInfo = AliyunUtil.sendInfo(sendMessageDto.getAliyunSMSEnum(), JSONObject.toJSONString(phonsList),
                    JSONObject.toJSONString(namesList), JSONObject.toJSONString(signNameList));
        	messageLog.setPhones(JSONObject.toJSONString(phonsList));
            messageLog.setSendResult(null != AliyunCodeTypeEnum.getValueByKey(ResultInfo) ? AliyunCodeTypeEnum.getValueByKey(ResultInfo): ResultInfo);
            messageLog.setSendTime(StringUtil.dateToString(new Date()));
            messageLog.setTemplateNum(sendMessageDto.getAliyunSMSEnum().getTemplateNum()); 
            messageLog.setSendStatus(("OK".equals(ResultInfo)) ? 0 : 1);
            messageLog.setSignName(JSONObject.toJSONString(signNameList));
		} catch (Exception e) {
			messageLog.setSendStatus(1);
            messageLog.setSendResult(StringUtil.getStackTrace(e));
		}
        messageLogService.insert(messageLog);
        return result;
    }
}
