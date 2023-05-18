package net.lotte.marketplace.domain.enumeration;

/**
 * 잠김해제사유코드(회원계정별 잠김과 해제 사유를 관리)(계정관리)
 */
public enum LockedUnlockReasonCode {
    NINETEEDAYSUNLOGIN("90일 미접속"),
    FAULTPASSWORD5("비밀번호 5회 틀림"),
    SELFVERIFY("본인인증"),
    ADMINCLEAR("관리자해제처리");

    private final String value;

    LockedUnlockReasonCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
