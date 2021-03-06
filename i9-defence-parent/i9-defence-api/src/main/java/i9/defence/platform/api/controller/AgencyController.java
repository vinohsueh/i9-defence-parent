package i9.defence.platform.api.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.AgencyParamDto;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Role;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.Constants;

/**
 * Created by 姜哲 on 2018/1/16--11:24
 * 经销商Controller
 */
@RestController
@RequestMapping("agency")
public class AgencyController {
    @Autowired
    private ManagerService managerService;
 
    /**
     * 初始化查询经销商
     * @return
     */
    @RequiresPermissions("agency_list")
    @RequestMapping("/pageAgency")
    public HashMap<String, Object> pageAgency() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager loginManager = managerService.getLoginManager();
        Role role = loginManager.getRole();
        Integer partentId = null;
        
        int grade = 0;
        //查询当前的人员的等级(管理员可看3级  1级经销商可以看下面2级  2级经销商可以看到下面1级)
        if(Arrays.asList(Constants.S_AGENCY).contains(role.getName())){
            partentId = loginManager.getId();
            grade = managerService.selectAgencyGrade(loginManager.getId());
        }
        List<Manager> agencys = managerService.selectAllAgency(partentId);
        result.put("data",agencys);
        result.put("grade",grade);
        return result;
    }

    /**
     * 管理经销商之  给一级分配二级  或者给二级分配三级  -- 并且查询出来未建立关系的默认经销商
     * （左侧）    (右侧)----为此ID下此经销商的下属们
     * @return
     */
    @RequiresPermissions("agency_list_manager")
    @RequestMapping("/allotAgency")
    public HashMap<String,Object> allotAgency(@RequestBody Integer agencyId){
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Manager> agencyLeftList = managerService.selectPartAgency(agencyId);
        Manager agencyRightList = managerService.getManagerById(agencyId);
        result.put("data1", agencyLeftList);
        result.put("data2", agencyRightList);
        return result;
    }

    /**
     * Integer[] managerIdS,Integer parentId
    * 往经销商关系表中增加关系分配二级三级经销商   左侧---->右侧（可以批量增加）
    */
    @RequiresPermissions("agency_list_manager")
    @RequestMapping("/insertAgency")
    public HashMap<String,Object> insertAgency(@RequestBody AgencyParamDto agencyParamDto){
        HashMap<String,Object> result = new HashMap<String, Object>();
        managerService.insertManagerGrade(Arrays.asList(agencyParamDto.getManagerIdS()),agencyParamDto.getParentId());
        return result;
    }


    /**
     * 撤销一级下的二级或者二级下的三级     右侧---->左侧(一个一个地撤销  因为会对二级判断)
     * */
    @RequiresPermissions("agency_list_manager")
    @RequestMapping("/deleteAgencyById")
    public HashMap<String,Object> deleteAgencyById(@RequestBody AgencyParamDto agencyParamDto){
        HashMap<String,Object> result = new HashMap<String,Object>();
        managerService.deleteAgencyById(agencyParamDto.getManagerId(),agencyParamDto.getParentId());
        return result;
    }


    /**
     * 根据经销商ID查询经销商实体类
     * */
    @RequiresPermissions("agency_list")
    @RequestMapping("/selectByAgencyId")
    public HashMap<String,Object> selectByAgencyId(@RequestBody Integer agencyId){
        HashMap<String, Object> result = new HashMap<String, Object>();
        Manager manager = managerService.getManagerById(agencyId);
        result.put("data", manager);
        return result;
    }
    
    /**
     * 查询已经建立关系的一级经销商们
     * */
    @RequiresPermissions("agency_list")
    @RequestMapping("/selectAagency")
    public HashMap<String,Object> selectAagency(){
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Manager>managers= managerService.selectAagency();
        result.put("data", managers);
        return result;
    }
    
    /**
     * 查询已经建立关系的二级经销商们
     * */
    @RequiresPermissions("agency_list")
    @RequestMapping("/selectBagency")
    public HashMap<String,Object> selectBagency(){
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Manager>managers= managerService.selectBagency();
        result.put("data", managers);
        return result;
    }
    
    /**
     *把此二级经销商的parentId 修改为新一级经销商ID
     * */
    @RequiresPermissions("agency_list")
    @RequestMapping("/updateBagency")
    public HashMap<String,Object> updateBagency(@RequestBody AgencyParamDto agencyParamDto){
        HashMap<String, Object> result = new HashMap<String, Object>();
        managerService.updateBagency(agencyParamDto.getManagerId(),agencyParamDto.getParentId());
        return result;
    }
    
    /**
     *把此二级经销商下的全部下属三级经销商的parentId修改为新的二级经销商ID
     * */
    @RequiresPermissions("agency_list")
    @RequestMapping("/updateCagency")
    public HashMap<String,Object> updateCagency(@RequestBody AgencyParamDto agencyParamDto){
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Integer> cIds = managerService.selectCIdsByBid(agencyParamDto.getSpareId());
        managerService.updateCagency(cIds,agencyParamDto.getParentId());
        return result;
    }

    /**
     * 当查看一级经销商的时候，展现一级经销商的下属为二级经销商，类似查看二级经销商时候，
     * 展现三级经销商。但是把一级的下属二级经销商进行更换他的领导的时候，会面临两个选择：
     * 1、直接把这个二级换一个领导（一级），此二级下的全部三级下属也跟随此二级。
     * 2、从新分配这个二级（比如这个二级不干了），这个二级下的三级下属此时也需要从新分配领导了。
     * 所以：结合实际--方法---->
     *      1、执行撤销二级经销商之前先根据此二级经销商ID查询他的下面是否有三级经销商存在。
     *          1、如果不存在-----》就直接该怎么操作，就怎么操作。
     *          2、如果存在-----》就要依据上面的两个选择进行操作了。
     *          以上判断调用selectByAgencyId方法查出这个二级的详细信息，判断LIST字段的长度
     * 1、当遇到第一个选择时候，直接把此项目中已经建立关系的一级经销商查询出来 把这个二级
     * 分配给相应的一级经销商。先调用查询已经建立关系的一级经销商LIST，方法名（selectAagency）
     *       上面这种方式的话  调用修改的方法  把此二级经销商的parentId 修改为新一级经销商ID
     *       方法名 updateBagency
     * 2、当遇到第二个选择的时候，此时就需要打包把这些三级经销商分配给一个查询出来的除自己之外
     * 的二级经销商。方法名(selectBagency)
     *       上面这种方式的话  调用修改的方法 把此二级经销商下的全部下属三级经销商的parentId
     *       修改为新的二级经销商ID  方法名(updateCagency)
     * ------------------------------------总结---------------------------------
     *  以上处理好了后，调用insertAgency方法  分配相应的经销商
     * */
}
