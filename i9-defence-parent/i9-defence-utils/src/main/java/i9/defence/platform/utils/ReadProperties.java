package i9.defence.platform.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 创建时间：2017年9月22日 下午4:38:10
 * 
 * @author lby
 * @version
 * 
 */
public class ReadProperties {

    /**
     * 默认目录
     */
    private final static String ROOT = "";

    /**
     * 默认文件名字
     */
    private final static String DEFAULT_FILE = "property.properties";

    /**
     * 根据key获取值
     * 
     * @param key
     * @return
     */
    public static String getKey(String key) {

        return getKey(ROOT + "/" + DEFAULT_FILE, key);
    }

    /**
     * 根据文件路径和key获取值
     * 
     * @param fileName
     * @param key
     * @return
     */
    public static String getKey(String fileName, String key) {
        String result = "";
        PropertiesConfiguration config;
        try {
            config = new PropertiesConfiguration(fileName);
            result = config.getString(key);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return result;
    }

}
