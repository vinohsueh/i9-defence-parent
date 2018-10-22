package i9.defence.platform.datapush.utils;

/**
 * httpResult响应封装类
 * 
 * @author jiangtao
 *
 */
public class HttpResponseUtil {

    public static <T> HttpResult<T> ok() {
        HttpResult<T> httpResult = new HttpResult<T>();
        httpResult.setMessage("操作成功");
        httpResult.setCode(0);
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
        httpResult.setCode(1);
        return httpResult;
    }
}
