package net.lotte.marketplace.domain.enumeration;

/**
 * 소개구분코드(그룹사 추가입력항목 또는 카탈로그유형 정보를 관리)(계정관리)
 */
public enum IntroduceTypeCode {
    ICONATTACHDOCUUID("회사 아이콘 첨부문서 UUID"),
    COMPANYEXPLAIN("회사 설명"),
    CONSUMERCOMMEMAIL("소비자 연락 담당자명"),
    CONSUMERCOMMMOBILE("소비자 연락 모바일전화번호"),
    CONSUMERCOMMLDPRT("소비자 연락 부서명"),
    CONSUMERCOMMPOSITION("소비자 연락 직책"),
    CONSUMERCOMMPHONE("소비자 연락 직장전화번호"),
    CATEGORYTYPE("카테고리 구분"),
    BUSINESSNEEDS("사업 필수 요구"),
    PRICE("가격"),
    AVAILABILITY("가용성"),
    DATATYPE("데이터 타입"),
    PROVIDERS("공급사");

    private final String value;

    IntroduceTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
