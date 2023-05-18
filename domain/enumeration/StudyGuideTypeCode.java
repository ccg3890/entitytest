package net.lotte.marketplace.domain.enumeration;

/**
 * 학습가이드구분코드(학습가이드 형태를 관리)(시스템관리)
 */
public enum StudyGuideTypeCode {
    MARKETPLACEUSER("마켓플레이스 사용자 가이드"),
    AISERVICEUSEFUL("AI서비스 활용 가이드"),
    DATACOMBINE("데이터 결합센터 가이드");

    private final String value;

    StudyGuideTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
