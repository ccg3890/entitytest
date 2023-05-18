package net.lotte.marketplace.domain.enumeration;

/**
 * 환급상태코드(환급 진행상태 정보를 관리)(상품관리)
 */
public enum RefundStatusCode {
    REQUESTREFUND("환급요청"),
    CANCELREFUND("환급취소"),
    ACCEPTREFUND("환급수용"),
    COMPLETEREFUND("환급완료");

    private final String value;

    RefundStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
