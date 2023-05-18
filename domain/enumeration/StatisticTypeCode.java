package net.lotte.marketplace.domain.enumeration;

/**
 * 통계구분코드(콘텐츠 상품에 대한 통계형태 정보를 관리)(상품관리)
 */
public enum StatisticTypeCode {
    REGIST("등록"),
    PURCHASE("구매"),
    CANCELPURCHASE("구매취소"),
    SUBSCRIBE("구독"),
    CANCELSUBSCRIBE("구독취소"),
    INTEREST("관심상품"),
    RECENTLYSEEN("최근본상품");

    private final String value;

    StatisticTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
