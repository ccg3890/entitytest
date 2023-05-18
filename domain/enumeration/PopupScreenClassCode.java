package net.lotte.marketplace.domain.enumeration;

/**
 * 팝업화면유형코드(팝업 화면의 팝업유형 정보를 관리)(시스템관리)
 */
public enum PopupScreenClassCode {
    MODAL("모달 팝업"),
    MODALESS("모달리스 팝업");

    private final String value;

    PopupScreenClassCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
