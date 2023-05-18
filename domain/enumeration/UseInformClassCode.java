package net.lotte.marketplace.domain.enumeration;

/**
 * 이용내역알림유형코드(회원계정별 동의정보 이용내역을 통지하는 유형정보를 관리)(계정관리)
 */
public enum UseInformClassCode {
    USEINFORMEMAIL("이메일 이용내역알림"),
    USEINFORMTALK("알림톡 이용내역알림");

    private final String value;

    UseInformClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
