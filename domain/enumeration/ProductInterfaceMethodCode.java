package net.lotte.marketplace.domain.enumeration;

/**
 * 상품연동방식코드(콘텐츠 상품의 제공방식 정보를 관리)(상품관리)
 */
public enum ProductInterfaceMethodCode {
    SFTP("sFTP"),
    API("API"),
    AGENT("그룹사 Agent 설치 및 ECO 연동");

    private final String value;

    ProductInterfaceMethodCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
