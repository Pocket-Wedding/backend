package pocket.backend.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_LOCATION(404, "LO_001", "존재하지 않는 위치입니다."),
    DUPLICATED_LOCATION_NAME(400, "LO_002", "이미 존재하는 위치 이름입니다."),
    EMPTY_LOCATION_NAME(400,"LO_003", "빈 위치 이름입니다."),
    INVALID_LOCATION_NAME(400,"LO_004", "위치의 이름은 공백일 수 없습니다."),

    NOT_FOUND_CATEGORY(404, "CA_001", "존재하지 않는 카테고리입니다."),
    DUPLICATED_CATEGORY_NAME(400, "CA_002", "이미 존재하는 카테고리 이름입니다."),
    EMPTY_CATEGORY_NAME(400, "CA_003", "빈 카테고리 이름입니다."),
    INVALID_CATEGORY_NAME(400,"CA_004", "카테고리의 이름은 공백일 수 없습니다."),

    NOT_FOUND_COMPANY(404,"CO_001" ,"존재하지 않는 회사입니다." ),
    DUPLICATED_COMPANY_NAME(400, "CO_002", "이미 존재하는 회사 이름입니다."),
    EMPTY_COMPANY_NAME(400, "CO_003", "빈 회사 이름입니다."),
    INVALID_COMPANY_NAME(400,"CO_004", "회사의 이름은 공백일 수 없습니다."),

    DUPLICATED_EMAIL(400, "AU_002", "이미 존재하는 E-mail입니다."),

    INTERNAL_SERVER_ERROR(500, "SYS_001", "내부 서버 오류입니다."),
    BAD_REQUEST(400, "SYS_002", "잘못된 요청입니다."),
    SERVICE_UNAVAILABLE(503, "SYS_003", "서비스가 일시적으로 이용 불가능합니다."),
    DATABASE_ERROR(500, "SYS_004", "데이터베이스 오류가 발생했습니다."),
    DATA_INTEGRITY_VIOLATION(409, "SYS_005", "데이터 무결성 위반입니다."),
    METHOD_NOT_ALLOWED(405, "SYS_006", "허용되지 않은 메서드입니다."),
    UNSUPPORTED_MEDIA_TYPE(415, "SYS_007", "지원하지 않는 미디어 타입입니다."),
    RATE_LIMIT_EXCEEDED(429, "SYS_008", "요청 제한을 초과했습니다.");



    private final int status;
    private final String code;
    private final String message;
}
