package net.lotte.marketplace.domain.enumeration;

/**
 * 회원상태코드(회원계정원부의 회원계정별 상태를 관리)(계정관리)
 */
public enum UserStatusCode {
    PENDING("계정 신청중"),
    ACTIVE("계정 사용중"),
    DORMANT("계정 휴면중"),
    QUIT("계정 탈퇴중"),
    WITHDRAWAL("계정 탈퇴"),
    LOCKED("계정 잠김"),
    REJECTBECOME("가입 거부");

    private final String value;

    UserStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
