package i9.defence.platform.enums;

/**
 * 创建时间：2018年3月28日 下午2:35:57
 * 
 * @author lby
 * @version
 * 
 */
public enum DataTypeEnum {

	one(0,"错误类型"), two(1, String.class,"string"), three(2, Short.class ,"short"), four(3,
			Integer.class ,"integer"), five(4, Long.class ,"long"), six(5,
					Float.class,"floar"), seven(6, String.class,"string");

	private DataTypeEnum(Integer value, Object object,String name) {
		this.name = name;
		this.value = value;
		this.object = object;
	}
	
	private DataTypeEnum(Integer value,String name) {
		this.name = name;
		this.value = value;
	}

	private Integer value;

	private Object object;
	
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
