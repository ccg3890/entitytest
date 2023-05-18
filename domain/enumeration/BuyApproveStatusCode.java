package net.lotte.marketplace.domain.enumeration;

/**
 * 구매승인상태코드(콘텐츠 상품의 구매승인상태 정보를 관리)(상품관리)
 */
public enum BuyApproveStatusCode {
    REQUEST("요청"),
    CONRIFM("확정"),
    PAYMENT("결제"),
    CANCELREQUEST("요청취소"),
    EXPIRE("만료"),
    REQUESTCANCELCONFIRM("확정취소요청"),
    APPROVECANCELCONFIRM("확정취소승인"),
    CANCELPAYMENT("결제취소");

    private final String value;

    BuyApproveStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
