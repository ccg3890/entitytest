package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.InformationChangeReasonCode;
import net.lotte.marketplace.domain.enumeration.InformationChangeTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 회원계정정보변경이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_change_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserChangeHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 정보변경ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "information_change_id", length = 36, nullable = false, unique = true)
    private String informationChangeId;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * 정보변경구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "information_change_type_code", nullable = false)
    private InformationChangeTypeCode informationChangeTypeCode;

    /**
     * 정보변경사유코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "information_change_reason_code", nullable = false)
    private InformationChangeReasonCode informationChangeReasonCode;

    /**
     * 변경전항목값
     */
    @NotNull
    @Size(max = 4000)
    @Column(name = "before_change_item_value", length = 4000, nullable = false)
    private String beforeChangeItemValue;

    /**
     * 변경후항목값
     */
    @NotNull
    @Size(max = 4000)
    @Column(name = "after_change_item_value", length = 4000, nullable = false)
    private String afterChangeItemValue;

    
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

    public UserChangeHist setIsPersisted() {
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

    public UserChangeHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
