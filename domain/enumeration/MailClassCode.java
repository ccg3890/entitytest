package net.lotte.marketplace.domain.enumeration;

/**
 * 메일유형코드(메일발송의 업무유형 정보를 관리)(시스템관리)
 */
public enum MailClassCode {
    NOTICEMARKETPLACE("마켓플레이스 공지성 메일"),
    NOTICECOMPANY("기업그룹사 공지성 메일"),
    NOTICEUSERID("회원계정 관련 메일"),
    NOTICESUBSCRIBE("콘텐츠 구독형 메일"),
    NOTICEQNA("Q&amp;A 질의/답변 메일"),
    NOTICECONTENTS("콘텐츠 관련 메일"),
    NOTICENEWSPLATFORM("플랫폼 소식 메일");

    private final String value;

    MailClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
