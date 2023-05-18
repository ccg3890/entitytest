package net.lotte.marketplace.domain.enumeration;

/**
 * 정산은행코드(회원계정별 정산거래용 은행 관리)(계정관리)
 */
public enum CalculateBankCode {
    KBSTAR("KB국민"),
    IBK("기업"),
    NHBANK("NH농협"),
    KDB("산업"),
    SUHYUPBANK("수협"),
    SHINHAN("신한"),
    WOORIBANK("우리"),
    EPOSTBABK("우체국"),
    KEBHANA("하나"),
    CITIBANK("한국씨티"),
    STANDARDCHARTERED("SC제일"),
    KAKAOBANK("카카오뱅크"),
    KBANKNOW("케이뱅크"),
    TOSSBANK("토스뱅크"),
    KNBANK("경남"),
    KJBANK("광주"),
    DGB("대구"),
    BUSANBANK("부산"),
    JBBANK("전북"),
    EJEJUBANK("제주"),
    NFCF("산림조합중앙회"),
    KFCC("MG새마을금고"),
    OPENBANKCU("신협"),
    DEUTSCHE("도이치"),
    BANKOFAMERICA("뱅크오브아메리카"),
    CCB("중국건설"),
    ICBC("중국공상"),
    BANKOFCHINA("중국"),
    HSBC("HSBC"),
    BNPPARIBAS("BNP파리바"),
    JPMORGANCHASE("JP모간체이스"),
    KBSEC("KB증권"),
    IPROVEST("교보증권"),
    DAISHIN("대신증권"),
    IMERITZ("메리트증권"),
    MIRAEASSET("미래에셋증권"),
    BOOKOOK("부국증권"),
    SAMSUNGPOP("삼성증권"),
    SHINYOUNG("신영증권"),
    SHINHANSEC("신한투자증권"),
    MYASSET("유안타증권"),
    EUGENEFN("유진투자증권"),
    EBESTSEC("이베스트투자증권"),
    KAKAOPAYSEC("카카오페이증권"),
    CAPEFN("케이프투자증권"),
    KIWOOM("키움증권"),
    TOSSINVEST("토스증권"),
    HANAW("하나증권"),
    HIIB("하이투자증권"),
    KOREAINVESTMENT("한국투자증권"),
    FOSSKOREA("한국포스증권"),
    HANWHAWM("한화투자증권"),
    HMSEC("현대차증권"),
    BNKFN("BNK투자증권"),
    DBFI("DB금융투자"),
    IBKS("IBK투자증권"),
    DAOLSECURITIES("DAOL투자증권"),
    NHQV("NH투자증권"),
    SKS("SK증권");

    private final String value;

    CalculateBankCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
