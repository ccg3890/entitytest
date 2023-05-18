package net.lotte.marketplace.domain.enumeration;

/**
 * 배치실행구분코드(배치 실행관련 이력정보를 관리)(시스템관리)
 */
public enum BatchExecuteTypeCode {
    STARTEXECUTE("실행시작"),
    ENDEXECUTE("실행종료"),
    ABORTQUIT("비정상종료"),
    LOGGINGPROCESS("처리이력생성"),
    LOGGINGPROCESSUNIT("처리건수단위이력생성");

    private final String value;

    BatchExecuteTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
