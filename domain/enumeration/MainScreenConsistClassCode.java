package net.lotte.marketplace.domain.enumeration;

/**
 * 메인화면구성유형코드(메인화면 구성을 위한 콘텐츠유형 정보를 관리)(시스템관리)
 */
public enum MainScreenConsistClassCode {
    STATISTIC("통계"),
    CONTENTS("콘텐츠"),
    AFFILIATE("계열사"),
    NEWS("소식"),
    BANNER("배너");

    private final String value;

    MainScreenConsistClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
