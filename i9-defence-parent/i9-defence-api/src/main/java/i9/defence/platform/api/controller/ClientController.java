package i9.defence.platform.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.ClientSearchDto;
import i9.defence.platform.model.Client;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.ClientService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.PageBounds;

/**
* @author : JiaCe
* @version ：2018年1月11日下午3:56:05
* 客户controller
*/
@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ProjectService projectService;
    /*
     *分页查询
     */
    @RequestMapping("/pageClient")
    public HashMap<String, Object> pageManager(@RequestBody ClientSearchDto clientSearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<Client> pageBounds = clientService.selectByLimitPage(clientSearchDto);
        result.put("data",pageBounds);
        return result;
    }
    /*
     * 添加或者更新
     */
     @RequestMapping("/updateAndAdd")
     public HashMap<String, Object> updateAndAdd(@Valid @RequestBody Client client,BindingResult bindingResult) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         clientService.updateAndAdd(client);
         return result;
     }
     /**
      * 删除
      */
      @RequestMapping("/deleteBatch")
      public HashMap<String, Object> deleteBatch(@RequestBody List<Integer> ids) {
          HashMap<String, Object> result = new HashMap<String, Object>();
          clientService.deleteBatch(ids);
          return result;
      }
      /**
      * id查找
      */
     @RequestMapping("/getClientById")
     public HashMap<String, Object> getClientById(@RequestBody Integer id) {
         HashMap<String, Object> result = new HashMap<String, Object>();
         Client client = clientService.getById(id);
         result.put("data",client);
         return result;
     }
     /*
      * 获取全部项目
      */
     @RequestMapping("/getAllProject")
     public HashMap<String, Object> getAllProject() {
         HashMap<String, Object> result = new HashMap<String, Object>();
         List<Project> list = projectService.findAllProject();
         result.put("data",list);
         return result;
     }
}
