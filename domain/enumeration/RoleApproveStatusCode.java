package net.lotte.marketplace.domain.enumeration;

/**
 * 권한승인상태코드(콘텐츠 상품의 권한승인상태 정보를 관리)(상품관리)
 */
public enum RoleApproveStatusCode {
    PENDINGROLE("권한 신청중"),
    ACTIVEROLE("권한 사용중"),
    REJECTROLE("권한부여 거절"),
    CANCELROLE("권한신청 취소"),
    FORFEITROLE("권한 몰수"),
    EXPIREROLE("권한 만료");

    private final String value;

    RoleApproveStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
