package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ReplyStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * Q&A문의원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_qna_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QnaMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 문의ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "qna_id", length = 36, nullable = false, unique = true)
    private String qnaId;

    /**
     * 문의제목
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "qna_title", length = 1000, nullable = false)
    private String qnaTitle;

    /**
     * 문의내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "qna_content", nullable = false)
    private String qnaContent;

    /**
     * 답변자ID
     */
    @Size(max = 20)
    @Column(name = "reply_user_id", length = 20)
    private String replyUserId;

    /**
     * 답변상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reply_status_code", nullable = false)
    private ReplyStatusCode replyStatusCode;

    /**
     * 답변종료예정일자
     */
    @NotNull
    @Column(name = "reply_end_expect_date", nullable = false)
    private LocalDate replyEndExpectDate;

    /**
     * 답변종료실제일자
     */
    @Column(name = "reply_end_actual_date")
    private LocalDate replyEndActualDate;

    /**
     * 답변만족도점수
     */
    @Column(name = "reply_satisfaction_score")
    private Long replySatisfactionScore;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "userAgreements",
            "companyMaster",
            "userBusiType",
            "userDormantHists",
            "userQuitHists",
            "userAgreements",
            "userLockHists",
            "userChangeHists",
            "userVerifyHists",
            "userApproveHists",
            "userLoginHists",
            "batchPgmLists",
            "recommendContentsMasters",
            "recommendContentsHists",
            "sendMailMasters",
            "sendMailHists",
            "pgmLists",
            "contentsSubscribeHists",
            "contentsRoleRequests",
            "contentsRoleApproveHists",
            "qnaMasters",
            "contentsInquiryMasters",
            "customContentsInquiryMasters",
            "contentsStatisticMasters",
            "userAttentionContents",
            "sendInformTalkHists",
        },
        allowSetters = true
    )
    private UserMaster userMaster;

    @OneToMany(mappedBy = "qnaMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "qnaMaster" }, allowSetters = true)
    private Set<QnaHist> qnaHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public QnaMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public QnaMaster userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<QnaHist> getQnaHists() {
        return this.qnaHists;
    }

    public void setQnaHists(Set<QnaHist> qnaHists) {
        if (this.qnaHists != null) {
            this.qnaHists.forEach(i -> i.setQnaMaster(null));
        }
        if (qnaHists != null) {
            qnaHists.forEach(i -> i.setQnaMaster(this));
        }
        this.qnaHists = qnaHists;
    }

    public QnaMaster qnaHists(Set<QnaHist> qnaHists) {
        this.setQnaHists(qnaHists);
        return this;
    }

    public QnaMaster addQnaHist(QnaHist qnaHist) {
        this.qnaHists.add(qnaHist);
        qnaHist.setQnaMaster(this);
        return this;
    }

    public QnaMaster removeQnaHist(QnaHist qnaHist) {
        this.qnaHists.remove(qnaHist);
        qnaHist.setQnaMaster(null);
        return this;
    }

    }
