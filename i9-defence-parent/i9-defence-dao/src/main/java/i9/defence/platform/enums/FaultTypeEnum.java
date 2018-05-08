package i9.defence.platform.enums;

import java.util.ArrayList;
import java.util.List;


/** 
 * 故障类型枚举
* 创建时间：2018年4月10日 下午4:29:45
* @author  lby
* @version  
* 
*/
public enum FaultTypeEnum {
	ALERT(0,"报警"),FAULT(1,"故障"),NORMAL(2,"正常");
    
    private static final List<FaultTypeEnum> DATATYPES = new ArrayList<FaultTypeEnum>();
    
    static {
    	DATATYPES.add(ALERT);
    	DATATYPES.add(FAULT);
    	DATATYPES.add(NORMAL);
    }
    
    private FaultTypeEnum(int id,String name){
        this.id = id;
        this.name = name;
    }
    
    private int id;
    
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static List<FaultTypeEnum> getDispatchs() {
        return DATATYPES;
    }

    public static String getNameById(int id) {
        for (FaultTypeEnum dataTypeEnum : FaultTypeEnum.values()) {
            if (dataTypeEnum.getId() == id) {
                return dataTypeEnum.getName();
            }
        }
        return "";
    }
}
