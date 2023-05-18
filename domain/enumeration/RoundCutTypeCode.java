package net.lotte.marketplace.domain.enumeration;

/**
 * 절사구분코드(절사금액 단위 정보를 관리)(상품관리)
 */
public enum RoundCutTypeCode {
    NONEROUNDCUT("절사미적용"),
    WONROUNDCUT("원단위절사"),
    TENWONROUNDCUT("10원단위절사"),
    HUNDREDWONROUNDCUT("100원단위절사");

    private final String value;

    RoundCutTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
