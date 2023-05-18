package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.InformTalkClassCode;
import net.lotte.marketplace.domain.enumeration.InformTalkPurposeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 알림톡발송원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_send_inform_talk_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendInformTalkMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 알림톡발송ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "send_inform_talk_id", length = 36, nullable = false, unique = true)
    private String sendInformTalkId;

    /**
     * 알림톡유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "inform_talk_class_code", nullable = false)
    private InformTalkClassCode informTalkClassCode;

    /**
     * 알림톡목적코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "inform_talk_purpose_code", nullable = false)
    private InformTalkPurposeCode informTalkPurposeCode;

    /**
     * 알림톡제목
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "inform_talk_title", length = 300, nullable = false)
    private String informTalkTitle;

    /**
     * 알림톡본문
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "inform_talk_text", nullable = false)
    private String informTalkText;

    /**
     * 알림톡소유자테이블ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "inform_talk_owner_class_code", length = 100, nullable = false)
    private String informTalkOwnerClassCode;

    /**
     * 알림톡소유자ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "inform_talk_owner_id", length = 36, nullable = false)
    private String informTalkOwnerId;

    
    @OneToMany(mappedBy = "sendInformTalkMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sendInformTalkMaster", "userMaster" }, allowSetters = true)
    private Set<SendInformTalkHist> sendInformTalkHists ;

    @OneToMany(mappedBy = "sendInformTalkMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "sendInformTalkMaster", "sendMailMaster", "userMaster", "userVerifyHist", "userDormantMaster" },
        allowSetters = true
    )
    private Set<UserDormantHist> userDormantHists ;

    @OneToMany(mappedBy = "sendInformTalkMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sendInformTalkMaster", "sendMailMaster", "userMaster" }, allowSetters = true)
    private Set<UserApproveHist> userApproveHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SendInformTalkMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<SendInformTalkHist> getSendInformTalkHists() {
        return this.sendInformTalkHists;
    }

    public void setSendInformTalkHists(Set<SendInformTalkHist> sendInformTalkHists) {
        if (this.sendInformTalkHists != null) {
            this.sendInformTalkHists.forEach(i -> i.setSendInformTalkMaster(null));
        }
        if (sendInformTalkHists != null) {
            sendInformTalkHists.forEach(i -> i.setSendInformTalkMaster(this));
        }
        this.sendInformTalkHists = sendInformTalkHists;
    }

    public SendInformTalkMaster sendInformTalkHists(Set<SendInformTalkHist> sendInformTalkHists) {
        this.setSendInformTalkHists(sendInformTalkHists);
        return this;
    }

    public SendInformTalkMaster addSendInformTalkHist(SendInformTalkHist sendInformTalkHist) {
        this.sendInformTalkHists.add(sendInformTalkHist);
        sendInformTalkHist.setSendInformTalkMaster(this);
        return this;
    }

    public SendInformTalkMaster removeSendInformTalkHist(SendInformTalkHist sendInformTalkHist) {
        this.sendInformTalkHists.remove(sendInformTalkHist);
        sendInformTalkHist.setSendInformTalkMaster(null);
        return this;
    }

    public Set<UserDormantHist> getUserDormantHists() {
        return this.userDormantHists;
    }

    public void setUserDormantHists(Set<UserDormantHist> userDormantHists) {
        if (this.userDormantHists != null) {
            this.userDormantHists.forEach(i -> i.setSendInformTalkMaster(null));
        }
        if (userDormantHists != null) {
            userDormantHists.forEach(i -> i.setSendInformTalkMaster(this));
        }
        this.userDormantHists = userDormantHists;
    }

    public SendInformTalkMaster userDormantHists(Set<UserDormantHist> userDormantHists) {
        this.setUserDormantHists(userDormantHists);
        return this;
    }

    public SendInformTalkMaster addUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.add(userDormantHist);
        userDormantHist.setSendInformTalkMaster(this);
        return this;
    }

    public SendInformTalkMaster removeUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.remove(userDormantHist);
        userDormantHist.setSendInformTalkMaster(null);
        return this;
    }

    public Set<UserApproveHist> getUserApproveHists() {
        return this.userApproveHists;
    }

    public void setUserApproveHists(Set<UserApproveHist> userApproveHists) {
        if (this.userApproveHists != null) {
            this.userApproveHists.forEach(i -> i.setSendInformTalkMaster(null));
        }
        if (userApproveHists != null) {
            userApproveHists.forEach(i -> i.setSendInformTalkMaster(this));
        }
        this.userApproveHists = userApproveHists;
    }

    public SendInformTalkMaster userApproveHists(Set<UserApproveHist> userApproveHists) {
        this.setUserApproveHists(userApproveHists);
        return this;
    }

    public SendInformTalkMaster addUserApproveHist(UserApproveHist userApproveHist) {
        this.userApproveHists.add(userApproveHist);
        userApproveHist.setSendInformTalkMaster(this);
        return this;
    }

    public SendInformTalkMaster removeUserApproveHist(UserApproveHist userApproveHist) {
        this.userApproveHists.remove(userApproveHist);
        userApproveHist.setSendInformTalkMaster(null);
        return this;
    }

    }
