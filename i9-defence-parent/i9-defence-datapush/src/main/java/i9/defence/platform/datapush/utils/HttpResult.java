package i9.defence.platform.datapush.utils;

public class HttpResult<T> implements java.io.Serializable {

    private static final long serialVersionUID = 7609149241975229223L;

    private String message;
    
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
