package net.lotte.marketplace.domain.enumeration;

/**
 * 환급대상사유코드(정산에 대한 환급발생사유 정보를 관리)(상품관리)
 */
public enum RefundTargetReasonCode {
    MISTAKECALCULATE("과오 정산"),
    ABORTSUBSCRIBE("구독 중지");

    private final String value;

    RefundTargetReasonCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
