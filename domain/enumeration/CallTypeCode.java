package net.lotte.marketplace.domain.enumeration;

/**
 * 호출구분코드(콘텐츠 상품에 대한 임의호출형태 정보를 관리)(상품관리)
 */
public enum CallTypeCode {
    CALLBATCH("배치 호출"),
    CALLINTERFACE("인터페이스 호출"),
    CALLPGM("프로그램 호출");

    private final String value;

    CallTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
