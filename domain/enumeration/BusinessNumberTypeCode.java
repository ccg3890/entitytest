package net.lotte.marketplace.domain.enumeration;

/**
 * 사업자번호구분코드(기업형태에 따른 사업자 또는 법인번호 정보를 관리)(계정관리)
 */
public enum BusinessNumberTypeCode {
    CORPORATENUMBER("법인번호"),
    BUSINESSNUMBER("사업자등록번호");

    private final String value;

    BusinessNumberTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
