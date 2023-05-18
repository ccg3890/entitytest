package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 메일발송이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_send_mail_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendMailHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 메일발송UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "send_mail_uuid", length = 36, nullable = false, unique = true)
    private String sendMailUuid;

    /**
     * 메일발송ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "send_mail_id", length = 36, nullable = false)
    private String sendMailId;

    /**
     * 메일수신자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "mail_receiver_id", length = 20, nullable = false)
    private String mailReceiverId;

    /**
     * 메일열람여부
     */
    @NotNull
    @Column(name = "mail_reading_yn", nullable = false)
    private Boolean mailReadingYn;

    
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

    public SendMailHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public SendMailMaster getSendMailMaster() {
        return this.sendMailMaster;
    }

    public void setSendMailMaster(SendMailMaster sendMailMaster) {
        this.sendMailMaster = sendMailMaster;
    }

    public SendMailHist sendMailMaster(SendMailMaster sendMailMaster) {
        this.setSendMailMaster(sendMailMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public SendMailHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
