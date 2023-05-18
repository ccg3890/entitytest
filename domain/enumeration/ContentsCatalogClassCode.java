package net.lotte.marketplace.domain.enumeration;

/**
 * 콘텐츠카탈로그유형코드(콘텐츠 상품의 카탈로그유형 정보를 관리)(전시관리)
 */
public enum ContentsCatalogClassCode {
    CATEGORYTYPE("카테고리 구분"),
    BUSINESSNEEDS("사업 필수 요구"),
    PRICE("가격"),
    AVAILABILITY("가용성"),
    DATATYPE("데이터 타입"),
    PROVIDERS("공급사");

    private final String value;

    ContentsCatalogClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
