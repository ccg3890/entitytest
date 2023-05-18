package net.lotte.marketplace.domain.enumeration;

/**
 * 인증구분코드(회원계정별 본인인증 정보를 관리)(계정관리)
 */
public enum VerifyTypeCode {
    MOBILEVERIFY("휴대폰 인증"),
    EMAILVERIFY("이메일 인증"),
    BUSINESSVERIFY("사업자 인증"),
    LOTTEGROUPVERIFY("롯데그룹사인증"),
    ANALYSTAPPROVE("분석가 인증");

    private final String value;

    VerifyTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
