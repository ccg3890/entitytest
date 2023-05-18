package net.lotte.marketplace.domain.enumeration;

/**
 * 템플릿상세영역코드(팝업 화면의 영역위치 정보를 관리)(시스템관리)
 */
public enum TemplateDetailAreaCode {
    LEFTTOP("좌측상단"),
    MIDDLETOP("중앙상단"),
    RIGHTTOP("우측상단"),
    WHOLETOP("상단전체"),
    LEFTBODY("좌측본문"),
    MIDDLEBODY("중앙본문"),
    RIGHTBODY("우측본문"),
    WHOLEBODY("본문전체"),
    LEFTBOTTOM("좌측하단"),
    MIDDLEBOTTOM("중앙하단"),
    RIGHTBOTTOM("우측하단"),
    WHOLEBOTTOM("하단전체");

    private final String value;

    TemplateDetailAreaCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
