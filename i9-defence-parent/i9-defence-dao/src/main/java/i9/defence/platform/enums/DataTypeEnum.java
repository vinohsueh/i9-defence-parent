package i9.defence.platform.enums;

/**
 * 创建时间：2018年3月28日 下午2:35:57
 * 
 * @author lby
 * @version
 * 
 */
public enum DataTypeEnum {

	one(0, ErrorAlertEnum.class), two(1, String.class), three(2, Short.class), four(3,
			Integer.class), five(4, Long.class), six(5,
					Float.class), seven(6, String.class);

	private DataTypeEnum(Integer value, Object object) {

	}

	private Integer value;

	private Object object;

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
