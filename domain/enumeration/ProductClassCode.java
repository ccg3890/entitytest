package net.lotte.marketplace.domain.enumeration;

/**
 * 상품유형코드(콘텐츠 상품의 유형정보를 관리)(상품관리)
 */
public enum ProductClassCode {
    DATASET("데이터셋"),
    CUSTOMDATASET("커스텀 데이터셋"),
    REPORT("리포트"),
    CUSTOMREPORT("커스텀 리포트");

    private final String value;

    ProductClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
