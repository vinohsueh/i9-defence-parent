package i9.defence.platform.datapush.utils;

public class HttpResponseUtil {

    public static <T> HttpResult<T> ok() {
        HttpResult<T> httpResult = new HttpResult<T>();
        httpResult.setMessage("success");
        return httpResult;
    }

    public static <T> HttpResult<T> ok(T re) {
        HttpResult<T> httpResult = ok();
        httpResult.setRe(re);
        return httpResult;
    }

    public static <T> HttpResult<T> error(String message) {
        HttpResult<T> httpResult = new HttpResult<T>();
        httpResult.setMessage(message);
        return httpResult;
    }
}
