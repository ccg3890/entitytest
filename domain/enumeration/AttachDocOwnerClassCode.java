package net.lotte.marketplace.domain.enumeration;

/**
 * 첨부문서소유자유형코드(첨부문서를 소유한 자의 유형을 관리)(시스템관리)
 */
public enum AttachDocOwnerClassCode {
    AFFILIATEINTRODUCE("그룹계열사소개"),
    STUDYGUIDE("학습가이드");

    private final String value;

    AttachDocOwnerClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
