package net.lotte.marketplace.domain.enumeration;

/**
 * 템플릿상세구분코드(팝업 화면의 영역별 배치되는 Object 정보를 관리)(시스템관리)
 */
public enum TemplateDetailTypeCode {
    TOPTITLE("상단제목"),
    TOPTEXT("상단텍스트"),
    TOPIMAGE("상단이미지"),
    BODYTITLE("본문제목"),
    BODYTEXT("본문텍스트"),
    BODYIMAGE("본문이미지"),
    BODYLINKTITLE("본문연결제목"),
    BODYLINKTEXT("본문연결텍스트"),
    BODYLINKIMAGE("본문연결이미지"),
    BOTTOMTITLE("하단제목"),
    BOTTOMTEXT("하단텍스트"),
    BOTTOMIMAGE("하단이미지"),
    CONFIRMBUTTON("확인버튼"),
    CANCELBUTTON("취소버튼");

    private final String value;

    TemplateDetailTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
