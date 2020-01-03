package cn.lwydyby.valid.exception;

/**
 * @author lwydyby
 * @title: ValidRuntimeException
 * @projectName valid
 * @description: TODO
 * @date 2020-01-03 09:08
 */
public class ValidRuntimeException extends RuntimeException {

    public ValidRuntimeException() {
    }

    public ValidRuntimeException(String message) {
        super(message);
    }

    public ValidRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidRuntimeException(Throwable cause) {
        super(cause);
    }

    public ValidRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
