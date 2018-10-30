package i9.defence.platform.datapush.utils;

/**
 * httpResult返回结果类
 * 
 * @author R12
 * @date 2018年10月23日 09:44:48
 * @param <T>
 */
public class HttpResult<T> implements java.io.Serializable {

    private static final long serialVersionUID = 7609149241975229223L;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回code
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回对象
     */
    private T re;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRe() {
        return re;
    }

    public void setRe(T re) {
        this.re = re;
    }
}
