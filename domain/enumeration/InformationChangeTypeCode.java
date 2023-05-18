package net.lotte.marketplace.domain.enumeration;

/**
 * 정보변경구분코드(회원계정별 변경된 항목 정보를 관리)(계정관리)
 */
public enum InformationChangeTypeCode {
    CHANGEPASSWORD("비밀번호변경");

    private final String value;

    InformationChangeTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
