package net.lotte.marketplace.domain.enumeration;

/**
 * 작업구분코드(첨부문서 업로드와 다운로드 정보를 관리)(시스템관리)
 */
public enum OperationTypeCode {
    UPLOADING("업로드"),
    DOWNLOADING("다운로드");

    private final String value;

    OperationTypeCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
