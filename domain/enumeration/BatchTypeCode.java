package net.lotte.marketplace.domain.enumeration;

/**
 * 배치구분코드(배치 또는 데몬을 구분하는 정보를 관리)(시스템관리)
 */
public enum BatchTypeCode {
    DEMONEXECUTE("데몬성실행"),
    BATCHEXECUTE("주기적실행");

    private final String value;

    BatchTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
