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
 * 알림톡발송이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_send_inform_talk_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendInformTalkHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 알림톡발송UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "send_inform_talk_uuid", length = 36, nullable = false, unique = true)
    private String sendInformTalkUuid;

    /**
     * 알림톡발송ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "send_inform_talk_id", length = 36, nullable = false)
    private String sendInformTalkId;

    /**
     * 알림톡수신자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "inform_talk_receiver_id", length = 20, nullable = false)
    private String informTalkReceiverId;

    /**
     * 알림톡열람여부
     */
    @NotNull
    @Column(name = "inform_talk_reading_yn", nullable = false)
    private Boolean informTalkReadingYn;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "sendInformTalkHists", "userDormantHists", "userApproveHists" }, allowSetters = true)
    private SendInformTalkMaster sendInformTalkMaster;

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

    public SendInformTalkHist setIsPersisted() {
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

    public SendInformTalkHist sendInformTalkMaster(SendInformTalkMaster sendInformTalkMaster) {
        this.setSendInformTalkMaster(sendInformTalkMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public SendInformTalkHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
