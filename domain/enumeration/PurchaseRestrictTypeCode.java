package net.lotte.marketplace.domain.enumeration;

/**
 * 구매제한구분코드(콘텐츠 상품의 구매를 제한하는 기업형태 정보를 관리)(상품관리)
 */
public enum PurchaseRestrictTypeCode {
    COMPANYBUSINESS("기업업종단위"),
    COMPANYUNIT("기업단위");

    private final String value;

    PurchaseRestrictTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
