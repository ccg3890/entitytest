package net.lotte.marketplace.domain.enumeration;

/**
 * 상품구분코드(콘텐츠 상품의 정형 정보를 관리)(상품관리)
 */
public enum ProductTypeCode {
    FIXEDTYPE("정형"),
    ATYPICAL("비정형");

    private final String value;

    ProductTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
