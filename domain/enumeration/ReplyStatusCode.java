package net.lotte.marketplace.domain.enumeration;

/**
 * 답변상태코드(문의에 대한 답변진행상태 정보를 관리)(상품관리)
 */
public enum ReplyStatusCode {
    WAITINGREPLY("답변 대기중"),
    WRITEREPLY("답변 기입"),
    ACCEPTREPLY("답변 수용"),
    DISSATISFIEDREPLY("답변 불만족"),
    ADDITIONREPLY("답변 추가기입");

    private final String value;

    ReplyStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
