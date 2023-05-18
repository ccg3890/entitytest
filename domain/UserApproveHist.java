package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ApproveTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 회원계정승인처리이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_approve_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserApproveHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 회원승인ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_approve_id", length = 36, nullable = false, unique = true)
    private String userApproveId;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * 승인구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "approve_type_code", nullable = false)
    private ApproveTypeCode approveTypeCode;

    /**
     * 가입거절사유
     */
    @Size(max = 4000)
    @Column(name = "become_reject_reason", length = 4000)
    private String becomeRejectReason;

    /**
     * 가입거절안내여부
     */
    @Column(name = "become_reject_guide_yn")
    private Boolean becomeRejectGuideYn;

    /**
     * 안내메일발송ID
     */
    @Size(max = 36)
    @Column(name = "guide_send_mail_id", length = 36)
    private String guideSendMailId;

    /**
     * 안내알림톡발송ID
     */
    @Size(max = 36)
    @Column(name = "guide_send_inform_talk_id", length = 36)
    private String guideSendInformTalkId;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "sendInformTalkHists", "userDormantHists", "userApproveHists" }, allowSetters = true)
    private SendInformTalkMaster sendInformTalkMaster;

    @ManyToOne
    @JsonIgnoreProperties(value = { "userMaster", "sendMailHists", "userDormantHists", "userApproveHists" }, allowSetters = true)
    private SendMailMaster sendMailMaster;

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

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserApproveHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public SendInformTalkMaster getSendInformTalkMaster() {
        return this.sendInformTalkMaster;
    }

    public void setSendInformTalkMaster(SendInformTalkMaster sendInformTalkMaster) {
        this.sendInformTalkMaster = sendInformTalkMaster;
    }

    public UserApproveHist sendInformTalkMaster(SendInformTalkMaster sendInformTalkMaster) {
        this.setSendInformTalkMaster(sendInformTalkMaster);
        return this;
    }

    public SendMailMaster getSendMailMaster() {
        return this.sendMailMaster;
    }

    public void setSendMailMaster(SendMailMaster sendMailMaster) {
        this.sendMailMaster = sendMailMaster;
    }

    public UserApproveHist sendMailMaster(SendMailMaster sendMailMaster) {
        this.setSendMailMaster(sendMailMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public UserApproveHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
