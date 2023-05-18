package net.lotte.marketplace.domain.enumeration;

/**
 * 알림톡목적코드(알림톡의 제공정보유형을 관리)(시스템관리)
 */
public enum InformTalkPurposeCode {
    SUPPLYINFORMATION("정보 제공"),
    SUPPLYTODOJOB("할일 제공"),
    SUPPLYSUBSCRIBE("구독 제공"),
    INFORMUSERUSE("회원계정정보이용내역 알림");

    private final String value;

    InformTalkPurposeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
