package net.lotte.marketplace.domain.enumeration;

/**
 * 수수료유형코드(콘텐츠 공급사 등급별 수수료정책 정보를 관리)(상품관리)
 */
public enum ChargeClassCode {
    BEGINNERCHARGE("초심자수수료"),
    THIRDGRADECHARGE("3등급수수료"),
    SECONDGRADECHARGE("2등급수수료"),
    FIRSTGRADECHARGE("1등급수수료"),
    VIPCHARGE("VIP수수료"),
    VVIPCHARGE("VVIP수수료");

    private final String value;

    ChargeClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
