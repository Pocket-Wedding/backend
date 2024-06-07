package pocket.backend.common.exceptions;

public class DuplicatedException extends BusinessException{
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
