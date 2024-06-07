package pocket.backend.common.exceptions;

public class NotFoundException extends BusinessException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
