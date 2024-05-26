package pocket.backend.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_EMAIL(400, "AU_002", "이미 존재하는 E-mail입니다.");

    private final int status;
    private final String code;
    private final String message;
}
