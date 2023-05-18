package net.lotte.marketplace.domain.enumeration;

/**
 * 프로그램구분코드(시스템의 프로그램 그룹유형을 관리)(시스템관리)
 */
public enum PgmTypeCode {
    MAINSCREEN("메인화면"),
    FRONTJS("화면 JavaScript 프로그램"),
    FRONTVUE("화면 Vue 프로그램"),
    FRONTENV("화면 환경설정 프로그램"),
    FRONTCONF("화면 통신환경설정"),
    FRONTICON("화면 아이콘 프로그램"),
    FRONTHTML("화면 웹문서 프로그램"),
    FRONTCERTS("화면 인증서 프로그램"),
    FRONTETC("화면 기타 프로그램"),
    BACKENDJAVA("기능 Java 프로그램"),
    BACKENDCERTS("기능 인증서 프로그램"),
    BACKENDXML("기능 Xml 프로그램"),
    BACKENDPROPERTIES("기능 환경설정 프로그램"),
    BACKENDYML("기능 Yml 프로그램"),
    BACKENDETC("기능 기타 프로그램");

    private final String value;

    PgmTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
