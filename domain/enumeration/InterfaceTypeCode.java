package net.lotte.marketplace.domain.enumeration;

/**
 * 연동구분코드	(콘텐츠 상품의 제공방식 정보를 관리)(시스템관리)
 */
public enum InterfaceTypeCode {
    SFTP("sFTP"),
    API("API"),
    AGENT("그룹사 Agent 설치 및 ECO 연동");

    private final String value;

    InterfaceTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
