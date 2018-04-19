package i9.defence.platform.api.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.EqCategorySearchDto;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.EquipmentCategory;
import i9.defence.platform.service.EquipmentCategoryService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.utils.PageBounds;

/**
 * 创建时间：2018年1月9日
 * 
 * @author gbq
 * @version
 */
@RestController
@RequestMapping("/eqCategory")
public class EquipmentCategoryController {
    @Autowired
    private EquipmentCategoryService eqCategoryService;
    
    /**
     * 分页查询项目分类列表
     * 
     * @param eqCategorySearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    @RequiresPermissions("equip_classify_list")
    @RequestMapping("/pageEqCategory")
    public HashMap<String, Object> pageEqCategory(
            @RequestBody EqCategorySearchDto eqCategorySearchDto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PageBounds<EquipmentCategory> pageBounds = eqCategoryService.selectByLimitPage(eqCategorySearchDto);
        result.put("data", pageBounds);
        return result;
    }

    /**
     * 查询项目分类全部
     * 
     * @param eqCategory
     * @return
     */
    @RequestMapping("/serchEqCategory")
    public HashMap<String, Object> serchEqCategory() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<EquipmentCategory> list = eqCategoryService.serchEqCategory();
        result.put("data", list);
        return result;
    }
    
    /**
     * 查询项目分类全部和数量
     * 
     * @param eqCategory
     * @return
//     */
//    @RequestMapping("/selectAllEqCategoryAndNum")
//    public HashMap<String, Object> selectAllEqCategoryAndNum() {
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        List<EqCategorySearchDto> list = eqCategoryService.selectAllEqCategoryAndNum();
//        result.put("data", list);
//        return result;
//    }

    /**
     * 添加项目分类
     * 
     * @param equipment
     * @return
     */
    @RequiresPermissions("equip_classify_add")
    @RequestMapping("/addEqCategory")
    public HashMap<String, Object> addEqCategory(
            @RequestBody EquipmentCategory eqCategory) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        eqCategoryService.addEqCategory(eqCategory);
        return result;
    }

    /**
     * id查找项目分类
     * @param EqCategoryId
     * @return
     */
    @RequiresPermissions("equip_classify_list")
    @RequestMapping("/getEqCategory")
    public HashMap<String, Object> getEqCategory(
            @RequestBody Integer EqCategoryId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        EquipmentCategory eqCategory = eqCategoryService.getEqCategoryById(EqCategoryId);
        result.put("data", eqCategory);
        return result;
    }

    /**
     * 删除项目分类
     * 
     * @param ids
     * @return
     */
    @RequiresPermissions("equip_classify_del")
    @RequestMapping("/delEqCategory")
    public HashMap<String, Object> delEqCategory(@RequestBody Integer[] ids) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        eqCategoryService.deleteEqCategory(Arrays.asList(ids));
        return result;
    }
}
