package net.lotte.marketplace.domain.enumeration;

/**
 * 상품소싱코드(콘텐츠 공급사의 상품제공 정보를 관리)(상품관리)
 */
public enum ProductSourcingCode {
    DIRECTPURPHASE("직매입"),
    ENTEREDSALE("입점판매"),
    CONSIGNMENTSALE("위탁판매");

    private final String value;

    ProductSourcingCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
