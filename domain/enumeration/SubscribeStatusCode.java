package net.lotte.marketplace.domain.enumeration;

/**
 * 구독상태코드(콘텐츠 상품의 구독상태 정보를 관리)(상품관리)
 */
public enum SubscribeStatusCode {
    ONGOINGSUBSCRIBE("구독진행중"),
    EXPIRESUBSCRIBE("구독만료"),
    CANCELSUBSCRIBE("구독취소");

    private final String value;

    SubscribeStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
