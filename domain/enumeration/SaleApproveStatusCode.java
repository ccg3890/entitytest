package net.lotte.marketplace.domain.enumeration;

/**
 * 판매승인상태코드(콘텐츠 상품의 판매승인상태 정보를 관리)(상품관리)
 */
public enum SaleApproveStatusCode {
    SALEWAITING("판매요청대기중"),
    SALEREQUEST("판매요청"),
    SALEREVIEW("판매승인검토중"),
    SALEAPPROVE("판매승인"),
    SALEDENY("판매승인거절"),
    SALECANCEL("판매요청취소");

    private final String value;

    SaleApproveStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
