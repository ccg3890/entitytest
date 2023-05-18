package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.DormantStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 휴면회원계정이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_dormant_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserDormantHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 휴면계정ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "dormant_id", length = 36, nullable = false, unique = true)
    private String dormantId;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * 휴면상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dormant_status_code", nullable = false)
    private DormantStatusCode dormantStatusCode;

    /**
     * 고지메일발송ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "notice_send_mail_id", length = 36, nullable = false)
    private String noticeSendMailId;

    /**
     * 고지알림톡발송ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "notice_send_inform_talk_id", length = 36, nullable = false)
    private String noticeSendInformTalkId;

    /**
     * 휴면해제회원인증ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "remove_dormant_user_verify_id", length = 36, nullable = false)
    private String removeDormantUserVerifyId;

    
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

    @ManyToOne
    @JsonIgnoreProperties(value = { "userMaster", "userDormantHists" }, allowSetters = true)
    private UserVerifyHist userVerifyHist;

    @ManyToOne
    @JsonIgnoreProperties(value = { "userDormantHists" }, allowSetters = true)
    private UserDormantMaster userDormantMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserDormantHist setIsPersisted() {
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

    public UserDormantHist sendInformTalkMaster(SendInformTalkMaster sendInformTalkMaster) {
        this.setSendInformTalkMaster(sendInformTalkMaster);
        return this;
    }

    public SendMailMaster getSendMailMaster() {
        return this.sendMailMaster;
    }

    public void setSendMailMaster(SendMailMaster sendMailMaster) {
        this.sendMailMaster = sendMailMaster;
    }

    public UserDormantHist sendMailMaster(SendMailMaster sendMailMaster) {
        this.setSendMailMaster(sendMailMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public UserDormantHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public UserVerifyHist getUserVerifyHist() {
        return this.userVerifyHist;
    }

    public void setUserVerifyHist(UserVerifyHist userVerifyHist) {
        this.userVerifyHist = userVerifyHist;
    }

    public UserDormantHist userVerifyHist(UserVerifyHist userVerifyHist) {
        this.setUserVerifyHist(userVerifyHist);
        return this;
    }

    public UserDormantMaster getUserDormantMaster() {
        return this.userDormantMaster;
    }

    public void setUserDormantMaster(UserDormantMaster userDormantMaster) {
        this.userDormantMaster = userDormantMaster;
    }

    public UserDormantHist userDormantMaster(UserDormantMaster userDormantMaster) {
        this.setUserDormantMaster(userDormantMaster);
        return this;
    }

    }
