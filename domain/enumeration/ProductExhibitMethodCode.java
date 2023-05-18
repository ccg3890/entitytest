package net.lotte.marketplace.domain.enumeration;

/**
 * 상품전시방식코드(콘텐츠 상품의 전시 정보를 관리)(상품관리)
 */
public enum ProductExhibitMethodCode {
    REGISTBO("BO 등록"),
    REGISTAPI("API 등록");

    private final String value;

    ProductExhibitMethodCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
