package i9.defence.platform.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class ErrHandleToExcel implements Serializable{
    private static final long serialVersionUID = 1166000316912439837L;
    
    private Integer id;
    //处理时间
    private Date handleDate;
    //处理时间
    private String handleDateStr;
    //状态
    private String typeStr;
    //项目名称
    private String projectName;
    //设备分类
    private String eqCategoryName;
    //设备位置
    private String equipmentRemarks;
    //处理内容
    private String handleStateStr;
    //处理人
    private String managerName;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getHandleDate() {
        return handleDate;
    }
    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }
    public String getTypeStr() {
        return typeStr;
    }
    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getEqCategoryName() {
        return eqCategoryName;
    }
    public void setEqCategoryName(String eqCategoryName) {
        this.eqCategoryName = eqCategoryName;
    }
    public String getEquipmentRemarks() {
        return equipmentRemarks;
    }
    public void setEquipmentRemarks(String equipmentRemarks) {
        this.equipmentRemarks = equipmentRemarks;
    }
    public String getHandleStateStr() {
        return handleStateStr;
    }
    public void setHandleStateStr(String handleStateStr) {
        this.handleStateStr = handleStateStr;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getHandleDateStr() {
        return handleDateStr;
    }
    public void setHandleDateStr(String handleDateStr) {
        this.handleDateStr = handleDateStr;
    }
    
    
}
