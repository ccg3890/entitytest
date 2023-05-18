package net.lotte.marketplace.domain.enumeration;

/**
 * 정보변경사유코드(회원계정별 항목이 변경된 사유를 관리)(계정관리)
 */
public enum InformationChangeReasonCode {
    EXCEED90DAYS("비밀번호변경후90일초과");

    private final String value;

    InformationChangeReasonCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
