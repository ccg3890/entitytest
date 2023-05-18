package net.lotte.marketplace.domain.enumeration;

/**
 * 정산방식코드(콘텐츠 상품의 정산방법 정보를 관리)(상품관리)
 */
public enum CalculateMethodCode {
    NONECALCULATE("정산대상아님"),
    ACCOUNTTRANSFER("계좌이체"),
    NAVERPAYRECHARGE("네이버페이충전"),
    KAKAOPAYRECHARGE("카카오페이충전");

    private final String value;

    CalculateMethodCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
