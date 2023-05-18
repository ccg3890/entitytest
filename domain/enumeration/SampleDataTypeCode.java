package net.lotte.marketplace.domain.enumeration;

/**
 * 샘플데이터구분코드(샘플 데이터의 DataType 정보를 관리)(시스템관리)
 */
public enum SampleDataTypeCode {
    BOOLEAN("bpchar"),
    LOCALDATE("date"),
    LONG("numeric"),
    ZONEDDATETIME("timestamp"),
    STRING("varchar");

    private final String value;

    SampleDataTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
