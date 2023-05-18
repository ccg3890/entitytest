package net.lotte.marketplace.domain.enumeration;

/**
 * 실행주기코드(배치가 실행되는 주기정보를 관리)(시스템관리)
 */
public enum ExecuteCycleCode {
    NONCYCLIC("비주기적"),
    DAILYCYCLIC("일단위주기"),
    WEEKENDCYCLIC("주단위주기"),
    MONTHCYCLIC("월단위주기"),
    QUARTERCYCLIC("분기별주기"),
    HALFYEARCYCLIC("반기별주기"),
    YEARCYCLIC("년단위주기");

    private final String value;

    ExecuteCycleCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
