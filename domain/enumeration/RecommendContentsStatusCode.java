package net.lotte.marketplace.domain.enumeration;

/**
 * 추천콘텐츠상태코드(콘텐츠 상품의 콘텐츠상태 정보를 관리)(상품관리)
 */
public enum RecommendContentsStatusCode {
    RECOMMENDREQUEST("추천 의뢰중"),
    RECOMMENDACCEPT("추천 수락"),
    RECOMMENDDENY("추천 거절"),
    RECOMMENDCANCEL("추천 취소");

    private final String value;

    RecommendContentsStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
