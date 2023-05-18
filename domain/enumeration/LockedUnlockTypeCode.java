package net.lotte.marketplace.domain.enumeration;

/**
 * 잠김해제구분코드(회원계정별 잠김여부 정보를 관리)(계정관리)
 */
public enum LockedUnlockTypeCode {
    LOCKED("잠김"),
    UNLOCKED("잠김해제");

    private final String value;

    LockedUnlockTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
