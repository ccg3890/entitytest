package net.lotte.marketplace.domain.enumeration;

/**
 * 메뉴형태구분코드(대메뉴, 서브메뉴, 아이콘메뉴 정보를 관리)(시스템관리)
 */
public enum MenuFormTypeCode {
    TITLEMENU("대메뉴"),
    SUBMENU("서브메뉴"),
    ICONMENU("아이콘메뉴");

    private final String value;

    MenuFormTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
