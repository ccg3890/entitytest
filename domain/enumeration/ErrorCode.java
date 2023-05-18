package net.lotte.marketplace.domain.enumeration;

/**
 * 오류코드(연동업무의 오류유형 정보를 관리)(시스템관리)
 */
public enum ErrorCode {
    NETWORKTROUBLE("네트웍장애"),
    SYSTEMTROUBLE("시스템장애"),
    TELEGRAMERROR("연동전문오류");

    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
