package net.lotte.marketplace.domain.enumeration;

/**
 * 송수신구분코드(연동업무의 송수신 형태를 관리)(시스템관리)
 */
public enum SendReceiveTypeCode {
    CALLSEND("송신 호출"),
    CALLRECEIVE("수신 호출"),
    REPLYSEND("송신 응답"),
    REPLYRECEIVE("수신 응답");

    private final String value;

    SendReceiveTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
