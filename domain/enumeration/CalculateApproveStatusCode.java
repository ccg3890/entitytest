package net.lotte.marketplace.domain.enumeration;

/**
 * 정산승인상태코드(정산신청에 대한 승인상태 정보를 관리)(상품관리)
 */
public enum CalculateApproveStatusCode {
    REQUESTCALCULATE("정산요청"),
    CANCELCALCULATE("정산취소"),
    APPROVECALCULATE("정산승인"),
    COMPLETEPAYMENT("지급완료");

    private final String value;

    CalculateApproveStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
