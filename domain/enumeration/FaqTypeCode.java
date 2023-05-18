package net.lotte.marketplace.domain.enumeration;

/**
 * FAQ구분코드(FAQ 정보를 조회 가능한 그룹형태를 관리)(시스템관리)
 */
public enum FaqTypeCode {
    FAQPLATFORM("플랫폼전체FAQ"),
    FAQUSERBUSITYPE("회원구분별FAQ"),
    FAQCOMPANY("기업별FAQ");

    private final String value;

    FaqTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
