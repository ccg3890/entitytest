package net.lotte.marketplace.domain.enumeration;

/**
 * 팝업템플릿유형코드(팝업 화면의 행태 정보를 관리)(시스템관리)
 */
public enum PopupTemplateClassCode {
    INFORMPOPUP("알림형팝업"),
    LINKPOPUP("연결형팝업");

    private final String value;

    PopupTemplateClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
