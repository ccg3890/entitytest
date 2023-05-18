package net.lotte.marketplace.domain.enumeration;

/**
 * 동의구분코드(회원계정별 동의정보 관리)(계정관리)
 */
public enum AgreementTypeCode {
    ALLAGREEMENT("모든 필수 및 선택 동의"),
    FOURTEENYEARSOLD("14세 이상 동의"),
    USETERMS("이용약관 동의"),
    PERSONINFOPROCESS("개인정보 처리방침 동의"),
    PERSONINFOCOLLECTMAN("필수 개인정보 수집 및 이용 동의"),
    PERSONINFOCOLLECTOPT("선택 개인정보 수집 및 이용 동의"),
    PERSONINFOOFFER("개인정보 제3자 제공 동의"),
    ADINFOMAILRECEIVE("광고성 정보 이메일 수신 동의"),
    ADINFOSMSRECEIVE("광고성 정보 SMS 수신 동의"),
    PERSONINFOEMIGRATION("개인정보 해외이전 동의");

    private final String value;

    AgreementTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
