package net.lotte.marketplace.domain.enumeration;

/**
 * 탈퇴점검구분코드(탈퇴 처리를 위한 점검결과 상태를 관리)(계정관리)
 */
public enum QuitCheckTypeCode {
    NONETRADING("진행중인 거래 내역이 없는 계정"),
    PENDINGSALE("판매등록 중인 상품이 존재하는 계정"),
    PENDINGTRADE("거래가 진행중인 상품이 존재하는 계정"),
    PENDINGCALCULATE("완료되지 않은 정산 내역이 존재하는 계정"),
    CANCELCHARGING("결제취소 중인 상품이 존재하는 계정");

    private final String value;

    QuitCheckTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
