package i9.defence.platform.socket.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class HTTPUtil {

    private static Logger logger = Logger.getLogger(HTTPUtil.class);

    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> sendPost(String requestUrl, HashMap<String, String> params) throws Exception {
        HashMap<String, Object> result = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("POST");

            StringBuffer stringBuffer = new StringBuffer();
            for (Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }

            String param = stringBuffer.substring(1);
            PrintWriter writer = new PrintWriter(httpUrlConn.getOutputStream());
            writer.write(param);
            writer.flush();
            writer.close();

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            result = JSONObject.parseObject(buffer.toString(), HashMap.class);
            return result;
        } catch (Exception exception) {
            logger.error("获取远程URL地址 : " + requestUrl + ", 失败", exception);
            throw exception;
        }
    }
}
