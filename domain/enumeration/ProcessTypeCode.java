package net.lotte.marketplace.domain.enumeration;

/**
 * 처리구분코드(시스템 프로그램의 업무처리형태 정보를 관리)(시스템관리)
 */
public enum ProcessTypeCode {
    SEARCH("조회"),
    INPTU("입력"),
    EDIT("수정"),
    DELETE("삭제"),
    PRINT("출력"),
    DOWNLOAD("다운로드"),
    UNDEFINED("미정의");

    private final String value;

    ProcessTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
