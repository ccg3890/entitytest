package net.lotte.marketplace.domain.enumeration;

/**
 * 휴면상태코드(휴면 회원계정별 상태를 관리)(계정관리)
 */
public enum DormantStatusCode {
    PRIORNOTICE("휴면 사전고지"),
    NOTICE("휴면 고지"),
    DORMANT("휴면 완료"),
    REMOVEDORMANT("휴면 해제");

    private final String value;

    DormantStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
