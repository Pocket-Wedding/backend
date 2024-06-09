package pocket.backend.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_EMAIL(400, "AU_002", "이미 존재하는 E-mail입니다."),
    DUPLICATED_CATEGORY_NAME(400, "CA_002", "이미 존재하는 카테고리 이름입니다."),
    DUPLICATED_LOCATION_NAME(400, "LO_002", "이미 존재하는 위치 이름입니다."),
    NOT_FOUND_CATEGORY(404, "CA_001", "존재하지 않는 카테고리입니다."),
    NOT_FOUND_LOCATION(404, "LO_001", "존재하지 않는 위치입니다."),
    NOT_FOUND_COMPANY(404,"CO_001" ,"존재하지 않는 회사입니다." );
    private final int status;
    private final String code;
    private final String message;
}
