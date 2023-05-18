package net.lotte.marketplace.domain.enumeration;

/**
 * 구독유형코드(콘텐츠 상품의 구독형태 정보를 관리)(상품관리)
 */
public enum SubscribeClassCode {
    ONETIMESUBSCRIBE("일회성구독"),
    MONTHLYSUBSCRIBE("월단위구독"),
    YEARLYSUBSCRIBE("년단위구독"),
    FREESUBSCRIBE("무료구독");

    private final String value;

    SubscribeClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
