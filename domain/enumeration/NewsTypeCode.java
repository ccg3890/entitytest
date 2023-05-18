package net.lotte.marketplace.domain.enumeration;

/**
 * 소식구분코드(시스템의 뉴스 그룹형태를 관리)(시스템관리)
 */
public enum NewsTypeCode {
    NEWSMARKETPLACE("마켓플레이스 소식"),
    NEWSAISERVICE("AI서비스 소식"),
    NEWSDATACOMBINED("데이터결합센터 소식"),
    NEWSGOVERNMENTPOLICY("정부정책 소식"),
    NEWSAFFILIATE("그룹계열사 소식"),
    NEWSPROVIDERS("공급사 소식"),
    NEWSCOMPANY("기업사 소식");

    private final String value;

    NewsTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
