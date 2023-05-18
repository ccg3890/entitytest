package net.lotte.marketplace.domain.enumeration;

/**
 * 성별코드
 */
public enum SexCode {
    MALE("남성"),
    FEMALE("여성"),
    UNKNOWN("모름");

    private final String value;

    SexCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
