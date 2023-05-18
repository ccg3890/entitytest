package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.MailClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 메일발송원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_send_mail_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendMailMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 메일발송ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "send_mail_id", length = 36, nullable = false, unique = true)
    private String sendMailId;

    /**
     * 메일유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mail_class_code", nullable = false)
    private MailClassCode mailClassCode;

    /**
     * 메일제목
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "mail_title", length = 300, nullable = false)
    private String mailTitle;

    /**
     * 메일본문
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "mail_text", nullable = false)
    private String mailText;

    /**
     * 메일발송소유자테이블ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "send_mail_owner_class_code", length = 100, nullable = false)
    private String sendMailOwnerClassCode;

    /**
     * 메일발송소유자ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "send_mail_owner_id", length = 36, nullable = false)
    private String sendMailOwnerId;

    
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

    @OneToMany(mappedBy = "sendMailMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sendMailMaster", "userMaster" }, allowSetters = true)
    private Set<SendMailHist> sendMailHists ;

    @OneToMany(mappedBy = "sendMailMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "sendInformTalkMaster", "sendMailMaster", "userMaster", "userVerifyHist", "userDormantMaster" },
        allowSetters = true
    )
    private Set<UserDormantHist> userDormantHists ;

    @OneToMany(mappedBy = "sendMailMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sendInformTalkMaster", "sendMailMaster", "userMaster" }, allowSetters = true)
    private Set<UserApproveHist> userApproveHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SendMailMaster setIsPersisted() {
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

    public SendMailMaster userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<SendMailHist> getSendMailHists() {
        return this.sendMailHists;
    }

    public void setSendMailHists(Set<SendMailHist> sendMailHists) {
        if (this.sendMailHists != null) {
            this.sendMailHists.forEach(i -> i.setSendMailMaster(null));
        }
        if (sendMailHists != null) {
            sendMailHists.forEach(i -> i.setSendMailMaster(this));
        }
        this.sendMailHists = sendMailHists;
    }

    public SendMailMaster sendMailHists(Set<SendMailHist> sendMailHists) {
        this.setSendMailHists(sendMailHists);
        return this;
    }

    public SendMailMaster addSendMailHist(SendMailHist sendMailHist) {
        this.sendMailHists.add(sendMailHist);
        sendMailHist.setSendMailMaster(this);
        return this;
    }

    public SendMailMaster removeSendMailHist(SendMailHist sendMailHist) {
        this.sendMailHists.remove(sendMailHist);
        sendMailHist.setSendMailMaster(null);
        return this;
    }

    public Set<UserDormantHist> getUserDormantHists() {
        return this.userDormantHists;
    }

    public void setUserDormantHists(Set<UserDormantHist> userDormantHists) {
        if (this.userDormantHists != null) {
            this.userDormantHists.forEach(i -> i.setSendMailMaster(null));
        }
        if (userDormantHists != null) {
            userDormantHists.forEach(i -> i.setSendMailMaster(this));
        }
        this.userDormantHists = userDormantHists;
    }

    public SendMailMaster userDormantHists(Set<UserDormantHist> userDormantHists) {
        this.setUserDormantHists(userDormantHists);
        return this;
    }

    public SendMailMaster addUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.add(userDormantHist);
        userDormantHist.setSendMailMaster(this);
        return this;
    }

    public SendMailMaster removeUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.remove(userDormantHist);
        userDormantHist.setSendMailMaster(null);
        return this;
    }

    public Set<UserApproveHist> getUserApproveHists() {
        return this.userApproveHists;
    }

    public void setUserApproveHists(Set<UserApproveHist> userApproveHists) {
        if (this.userApproveHists != null) {
            this.userApproveHists.forEach(i -> i.setSendMailMaster(null));
        }
        if (userApproveHists != null) {
            userApproveHists.forEach(i -> i.setSendMailMaster(this));
        }
        this.userApproveHists = userApproveHists;
    }

    public SendMailMaster userApproveHists(Set<UserApproveHist> userApproveHists) {
        this.setUserApproveHists(userApproveHists);
        return this;
    }

    public SendMailMaster addUserApproveHist(UserApproveHist userApproveHist) {
        this.userApproveHists.add(userApproveHist);
        userApproveHist.setSendMailMaster(this);
        return this;
    }

    public SendMailMaster removeUserApproveHist(UserApproveHist userApproveHist) {
        this.userApproveHists.remove(userApproveHist);
        userApproveHist.setSendMailMaster(null);
        return this;
    }

    }
