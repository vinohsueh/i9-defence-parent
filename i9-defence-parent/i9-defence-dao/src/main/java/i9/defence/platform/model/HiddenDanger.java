package i9.defence.platform.model;


/**
 * 隐患提醒实体类
 * @ClassName: HiddenDanger 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月2日 下午3:09:39
 */
public class HiddenDanger {
    private Integer id;
    
    /**
     * 隐患类型名称
     */
    private String name;

    /**
     * 隐患阀值最大值
     */
    private Double hiddenMax;

    /**
     * 隐患阀值最小值
     */
    private Double hiddenMin;

    /**
     * 报警阀值最大值
     */
    private Double warningMax;

    /**
     * 报警阀值最小值
     */
    private Double warningMin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getHiddenMax() {
        return hiddenMax;
    }

    public void setHiddenMax(Double hiddenMax) {
        this.hiddenMax = hiddenMax;
    }

    public Double getHiddenMin() {
        return hiddenMin;
    }

    public void setHiddenMin(Double hiddenMin) {
        this.hiddenMin = hiddenMin;
    }

    public Double getWarningMax() {
        return warningMax;
    }

    public void setWarningMax(Double warningMax) {
        this.warningMax = warningMax;
    }

    public Double getWarningMin() {
        return warningMin;
    }

    public void setWarningMin(Double warningMin) {
        this.warningMin = warningMin;
    }
}