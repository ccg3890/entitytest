package net.lotte.marketplace.domain.enumeration;

/**
 * 공지구분코드(공지사항을 수신할 그룹형태를 관리)(시스템관리)
 */
public enum NoticeTypeCode {
    NOTICEPLATFORM("플랫폼전체공지"),
    NOTICEUSERBUSITYPE("회원구분별공지"),
    NOTICECOMPANY("기업별공지");

    private final String value;

    NoticeTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
