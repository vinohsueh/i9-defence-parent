package i9.defence.platform.utils;
/** 
 * 常量
 * 创建时间：2018年1月11日 下午3:48:29
 * @author  lby
 * @version  
 * 
 */
public class Constants {
    
    /**
     * 网站管理员的type
     */
    public static final Byte [] S_NET_MANAGER = {(byte)0};
    
    /**
     * 用户的type
     */
    public static final Byte [] S_ACCOUNT = {(byte)1,(byte)2};

    /**
     * 经销商的type
     */
    public static final Byte [] S_AGENCY_TYPE = {(byte)1};
    
    /**
     * 登录url
     */
    public static final String S_LOGIN_URL = "/login";
    
    /**
     * 注册url
     */
    public static final String S_REGIST_URL = "/regist";
    
    /**
     * 经销商列表
     */
    public static final String [] S_AGENCY = {"省级经销商","地市级经销商"};
    
    /**
     * 项目管理员列表
     */
    public static final String [] S_PROJ_MANAGER = {"主管领导","值班人员","管理人员"};
}
