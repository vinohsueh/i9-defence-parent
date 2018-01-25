package i9.defence.platform.utils;
/** 
 * 创建时间：2018年1月24日 下午4:02:47
 * @author  lby
 * @version  
 * 
 */
public class UserLoginException extends RuntimeException {
    /**
     * <code>serialVersionUID</code> - 
     */
    private static final long serialVersionUID = 8777717633246288996L;
    private String errorMessage;
    
    public UserLoginException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
