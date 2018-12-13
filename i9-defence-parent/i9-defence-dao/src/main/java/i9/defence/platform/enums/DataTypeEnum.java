package i9.defence.platform.enums;

import java.util.ArrayList;
import java.util.List;


/** 
* 创建时间：2018年4月10日 下午4:29:45
* @author  lby
* @version  
* 
*/
public enum DataTypeEnum {
	ERROR(0,"故障报警类数据"),SHORT(2,"short类型数据"),INT(3,"int类型数据"), LONG(4,"long类型数据"),FLOAT(5,"float类型数据");
    
    private static final List<DataTypeEnum> DATATYPES = new ArrayList<DataTypeEnum>();
    
    static {
    	DATATYPES.add(ERROR);
    }
    
    private DataTypeEnum(int id,String name){
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
    
    public static List<DataTypeEnum> getDispatchs() {
        return DATATYPES;
    }

    public static String getNameById(int id) {
        for (DataTypeEnum dataTypeEnum : DataTypeEnum.values()) {
            if (dataTypeEnum.getId() == id) {
                return dataTypeEnum.getName();
            }
        }
        return "";
    }
}
