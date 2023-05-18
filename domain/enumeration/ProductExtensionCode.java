package net.lotte.marketplace.domain.enumeration;

/**
 * 상품확장자코드(콘텐츠 상품의 파일형식 정보를 관리)(상품관리)
 */
public enum ProductExtensionCode {
    CSV("CSV 파일"),
    PDF("PDF 파일");

    private final String value;

    ProductExtensionCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
