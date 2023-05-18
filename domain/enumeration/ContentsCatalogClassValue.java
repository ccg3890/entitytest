package net.lotte.marketplace.domain.enumeration;

/**
 * 콘텐츠카탈로그유형값(콘텐츠 상품의 카탈로그유형별 상세코드 정보를 관리)(전시관리)
 */
public enum ContentsCatalogClassValue {
    DISTRIBUTIONPOS("유통/POS"),
    ECOMMERCE("이커머스"),
    PRICEMARKET("가격/시세"),
    COMMERCIAL("상권"),
    FLOATINGPOPULATION("유동인구"),
    SOCIAL("소셜"),
    ENVIRONMENT("환경"),
    SOURCEDATA("원천데이터"),
    PROCESSINGDATA("가공데이터"),
    DEMANDFORECAST("수요예측(시계열"),
    ANALYSISCOMMERCIAL("상권분석(위치"),
    MARKETINTELLGENT("마켓인텔리전스"),
    ANALYSISPRICE("가격분석"),
    ANALYSISSALE("판매분석"),
    MANAGESUPPLYCHAIN("공급망관리"),
    TRENDSTATISTIC("트렌드/통계"),
    ETC("기타"),
    FREECHARGE("무료"),
    PAYCHARGE("유료"),
    CONSULTPRICE("가격협의"),
    NORMALDATA("정형 데이터 상품"),
    CUSTOMMADE("주문/제작 상품"),
    DATASET("데이터셋"),
    REPORT("리포트"),
    MARKETLINK("마켓링크"),
    BUILTON("빌트온"),
    TRIDGE("트릿지"),
    LOPLAT("로플랫"),
    BIGVALUE("빅벨류"),
    VIVE("바이브"),
    DATAMARKETING("데이터마케팅코리아"),
    WEATHERI("웨더아이");

    private final String value;

    ContentsCatalogClassValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
