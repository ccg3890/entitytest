package net.lotte.marketplace.domain.enumeration;

/**
 * 구매구분코드(콘텐츠 상품의 구매,구독 정보를 관리)(상품관리)
 */
public enum BuyTypeCode {
    BUYCONTENTS("콘텐츠구매"),
    SUBSCRIBECONTENTS("콘텐츠구독");

    private final String value;

    BuyTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
