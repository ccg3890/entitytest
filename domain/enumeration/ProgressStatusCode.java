package net.lotte.marketplace.domain.enumeration;

/**
 * 진행상태코드(맞춤형 콘텐츠 경과진행상태 정보를 관리)(상품관리)
 */
public enum ProgressStatusCode {
    REQUESTPURCHASE("구매 요청"),
    EXAMINEREQUEST("요청내용 검토"),
    DENYREQUEST("요청내용 거부"),
    MATCHINGPROVIDER("공급자 매칭"),
    CONSULTPURCHASE("구매 협의"),
    COMPLETECONTENTS("콘텐츠제공 완료"),
    COMPLETEPURCHASE("콘텐츠구매 완료"),
    DISSATISFIEDCONTENTS("콘텐츠 불만족"),
    ADDITIONCONTENTS("콘텐츠 추가제공");

    private final String value;

    ProgressStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
