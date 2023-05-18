package net.lotte.marketplace.domain.enumeration;

/**
 * 탈퇴구분코드(본인의사 탈퇴 또는 관리자 탈퇴의 정보를 관리)(계정관리)
 */
public enum QuitTypeCode {
    SELFMINDQUIT("본인의사탈퇴"),
    ADMINQUIT("관리자탈퇴");

    private final String value;

    QuitTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
