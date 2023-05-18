package net.lotte.marketplace.domain.enumeration;

/**
 * 알림톡유형코드(알림톡의 유형별 그룹형태 정보를 관리)(시스템관리)
 */
public enum InformTalkClassCode {
    TALKMARKETPLACE("마켓플레이스 공지성 알림톡"),
    TALKCOMPANY("기업그룹사 공지성 알림톡"),
    TALKUSERID("회원계정 관련 알림톡"),
    TALKSUBSCRIBE("콘텐츠 구독형 알림톡"),
    TALKQNA("Q&amp;A 질의/답변 알림톡"),
    TALKCONTENTS("콘텐츠 관련 알림톡"),
    TALKNEWSPLATFORM("플랫폼 소식 알림톡");

    private final String value;

    InformTalkClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
