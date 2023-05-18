package net.lotte.marketplace.domain.enumeration;

/**
 * 상품가격코드(콘텐츠 상품의 가격 정보를 관리)(상품관리)
 */
public enum ProductPriceCode {
    FREE("무료"),
    CHARGED("유료"),
    CONSULTPRICE("가격협의"),
    SUBSCRIBE("구독");

    private final String value;

    ProductPriceCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
