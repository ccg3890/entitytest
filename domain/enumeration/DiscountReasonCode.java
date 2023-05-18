package net.lotte.marketplace.domain.enumeration;

/**
 * 할인사유코드(콘텐츠 상품의 구매할인사유 정보를 관리)(상품관리)
 */
public enum DiscountReasonCode {
    SUBSCRIBEYEARLYPAY("구독콘텐츠년간결제"),
    USEDISCOUNTCOUPON("할인쿠폰사용"),
    PROVIDERDISCOUNT("공급사할인제공"),
    MARKETDISCOUNT("마켓플레이스할인제공"),
    ADMINDISCOUNT("관리자할인제공");

    private final String value;

    DiscountReasonCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
