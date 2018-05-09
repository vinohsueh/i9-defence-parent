package i9.defence.platform.socket.exception;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

    private int errorCode;

    private Throwable throwable;

    public BusinessException(int errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, Throwable throwable) {
        this.errorCode = errorCode;
        this.throwable = throwable;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void printStackTrace() {
        if (throwable == null) {
            return;
        }
        throwable.printStackTrace();
    }
}
