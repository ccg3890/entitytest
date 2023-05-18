package net.lotte.marketplace.domain.enumeration;

/**
 * 상품구매방식코드(콘텐츠 상품의 결제방식 정보를 관리)(상품관리)
 */
public enum ProductPurchaseMethodCode {
    SIMPLENAVERPAY("간편결제네이버페이"),
    SIMPLEKAKAOPAY("간편결제카카오페이"),
    TAXINVOICE("세금계산서"),
    PGINTERFACE("PG사 연동");

    private final String value;

    ProductPurchaseMethodCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
