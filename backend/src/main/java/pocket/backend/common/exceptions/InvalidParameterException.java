package pocket.backend.common.exceptions;

public class InvalidParameterException extends BusinessException {
    public InvalidParameterException(ErrorCode errorCode) {
        super(errorCode);
    }
}
