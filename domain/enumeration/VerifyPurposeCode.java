package net.lotte.marketplace.domain.enumeration;

/**
 * 인증목적코드(본인인증을 하려는 목적 정보를 관리)(계정관리)
 */
public enum VerifyPurposeCode {
    BECOMEMEMBER("회원계정 가입"),
    REMOVEDORMANT("휴면계정 복원"),
    SEARCHMEMBERID("아이디 찾기"),
    INITIALIZEPASSWORD("비밀번호 초기화"),
    UNLOCKMEMBER("계정잠김해제"),
    UPDATEMEMBER("회원계정정보 수정");

    private final String value;

    VerifyPurposeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
