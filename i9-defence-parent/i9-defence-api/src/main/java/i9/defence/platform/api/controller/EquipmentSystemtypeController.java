package i9.defence.platform.api.controller;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.EqSystemCategorySearchDto;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.model.EquipmentSystemtype;
import i9.defence.platform.service.EqSystemCategoryService;
import i9.defence.platform.utils.PageBounds;

/**
 * 创建时间：2018年5月11日
 * 
 * @author gbq
 * @version
 */
@RestController
@RequestMapping("/eqSystemtype")
public class EquipmentSystemtypeController {
	@Autowired
	private EqSystemCategoryService eqSystemCategoryService;
	
	 /**
     * 分页查询项目分类列表
     * 
     * @param eqCategorySearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequiresPermissions("equip_classify_list")
    @RequestMapping("/pageSystemtype")
    public HashMap<String, Object> pageEqCategory(
            @RequestBody EqSystemCategorySearchDto eqSystemCategorySearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<EquipmentSystemtype> pageBounds = eqSystemCategoryService.selectByLimitPage(eqSystemCategorySearchDto);
        result.put("data", pageBounds);
        return result;
    }
    /**
     * 添加项目分类
     * 
     * @param equipment
     * @return
     */
    @RequiresPermissions("equip_classify_add")
    @RequestMapping("/addEqSystemtype")
    public HashMap<String, Object> addEqSystemtype(
            @RequestBody EquipmentSystemtype equipmentSystemtype) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        eqSystemCategoryService.addEqSystemtype(equipmentSystemtype);
        return result;
    }
    /**
     * id查找项目分类
     * @param EqCategoryId
     * @return
     */
    @RequiresPermissions("equip_classify_list")
    @RequestMapping("/getEqSystemtype")
    public HashMap<String, Object> getEqSystemtype(
            @RequestBody Integer id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        EquipmentSystemtype equipmentSystemtype = eqSystemCategoryService.getEqSystemtypeById(id);
        result.put("data", equipmentSystemtype);
        return result;
    }

    /**
     * 删除项目分类
     * 
     * @param ids
     * @return
     */
    @RequiresPermissions("equip_classify_del")
    @RequestMapping("/delEqSystemtype")
    public HashMap<String, Object> delEqSystemtype(@RequestBody Integer[] ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        eqSystemCategoryService.deleteEqSystemtype(Arrays.asList(ids));
        return result;
    }

}
