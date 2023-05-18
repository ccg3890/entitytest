package net.lotte.marketplace.domain.enumeration;

/**
 * 통계진행상태코드(통계구분별 통계진행상태 정보를 관리)(상품관리)
 */
public enum StatisticProgressStatusCode {
    SALEWAITING("판매요청대기중"),
    SALEREQUEST("판매요청"),
    SALEREVIEW("판매승인검토중"),
    SALEAPPROVE("판매승인"),
    SALEDENY("판매승인거절"),
    SALECANCEL("판매요청취소"),
    REQUEST("요청"),
    CANCELREQUEST("요청취소"),
    CONRIFM("확정"),
    PAYMENT("결제"),
    EXPIRE("만료"),
    REQUESTCANCELCONFIRM("확정취소요청"),
    APPROVECANCELCONFIRM("확정취소승인"),
    CANCELPAYMENT("결제취소"),
    NONINTEREST("미관심"),
    INTEREST("관심"),
    CANCELINTEREST("관심취소"),
    RECENTLYSEEING("최근보는중"),
    RECENTLYSAW("본지오래된");

    private final String value;

    StatisticProgressStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
