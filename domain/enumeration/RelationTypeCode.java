package net.lotte.marketplace.domain.enumeration;

/**
 * 연관구분코드(추천 키워드별 연관 콘텐츠 카테고리 정보를 관리)(상품관리)
 */
public enum RelationTypeCode {
    CONTENTSRELATION("콘텐츠별 연관"),
    CATAGORYRELATION("카테고리별 연관");

    private final String value;

    RelationTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
