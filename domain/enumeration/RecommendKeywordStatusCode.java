package net.lotte.marketplace.domain.enumeration;

/**
 * 추천키워드상태코드(추천 키워드의 상태 정보를 관리)(상품관리)
 */
public enum RecommendKeywordStatusCode {
    RECOMMENDREQUEST("추천 의뢰중"),
    RECOMMENDACCEPT("추천 수락"),
    RECOMMENDDENY("추천 거절"),
    RECOMMENDCANCEL("추천 취소");

    private final String value;

    RecommendKeywordStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
