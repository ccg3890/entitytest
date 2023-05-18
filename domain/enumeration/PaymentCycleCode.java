package net.lotte.marketplace.domain.enumeration;

/**
 * 결제주기코드(콘텐츠 상품의 결재반복주기 정보를 관리)(상품관리)
 */
public enum PaymentCycleCode {
    ONETIMEPAY("일회성결제"),
    MONTHLYPAY("월단위결제"),
    YEARLYPAY("년단위결제");

    private final String value;

    PaymentCycleCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
