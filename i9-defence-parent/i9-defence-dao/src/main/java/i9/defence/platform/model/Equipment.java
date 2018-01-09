package i9.defence.platform.model;

import java.util.Date;
/**
 * 设备实体类
 * 
 * 
 * @author 
 *
 * @create 2017年12月26日
 *
 */
public class Equipment {
    private Integer id;
    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 设备数量
     */
    private Integer equipmentNum;
    /**
     * 设备编号
     */
    private Integer equipmentIdentifier;
    /**
     * 设备位置
     */
    private String equipmentPosition;
    /**
     * 设备创建时间
     */
    private Date equipmentDate;
    /**
     * 设备备注
     */
    private String equipmentRemarks;
    /**
     * 设备分类id
     */
    private Integer equipmentCategoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public Integer getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(Integer equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public Integer getEquipmentIdentifier() {
        return equipmentIdentifier;
    }

    public void setEquipmentIdentifier(Integer equipmentIdentifier) {
        this.equipmentIdentifier = equipmentIdentifier;
    }

    public String getEquipmentPosition() {
        return equipmentPosition;
    }

    public void setEquipmentPosition(String equipmentPosition) {
        this.equipmentPosition = equipmentPosition == null ? null : equipmentPosition.trim();
    }

    public Date getEquipmentDate() {
        return equipmentDate;
    }

    public void setEquipmentDate(Date equipmentDate) {
        this.equipmentDate = equipmentDate;
    }

    public String getEquipmentRemarks() {
        return equipmentRemarks;
    }

    public void setEquipmentRemarks(String equipmentRemarks) {
        this.equipmentRemarks = equipmentRemarks == null ? null : equipmentRemarks.trim();
    }

    public Integer getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(Integer equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }
}