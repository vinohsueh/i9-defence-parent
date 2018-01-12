package i9.defence.platform.model;

/**
 * 隐患提醒model
* @ClassName: HiddenDanger 
* @Description: TODO
* @author luobo
* @date 2018年1月9日 下午4:06:39 
*
 */
public class HiddenDanger {
    private Integer id;

    /**
     * 隐患类型名称
     */
    private String name;

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
}