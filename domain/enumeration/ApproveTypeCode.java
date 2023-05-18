package net.lotte.marketplace.domain.enumeration;

/**
 * 승인구분코드(회원계정별 승인처리 정보를 관리)(계정관리)
 */
public enum ApproveTypeCode {
    APPROVEBECOME("가입 승인"),
    REJECTBECOME("가입 거부");

    private final String value;

    ApproveTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
