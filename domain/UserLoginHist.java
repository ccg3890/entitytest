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
 * 회원계정로그인이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_login_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserLoginHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 회원로그인ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_login_id", length = 36, nullable = false, unique = true)
    private String userLoginId;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * 트랜잭션ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "global_transaction_id", length = 100, nullable = false)
    private String globalTransactionId;

    /**
     * 세션ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "global_session_id", length = 100, nullable = false)
    private String globalSessionId;

    /**
     * 접속IP주소
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "connect_ip_address", length = 20, nullable = false)
    private String connectIpAddress;

    /**
     * 접근구분코드
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "access_type_code", length = 20, nullable = false)
    private String accessTypeCode;

    /**
     * 접근구분ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "access_type_id", length = 36, nullable = false)
    private String accessTypeId;

    
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

    public UserLoginHist setIsPersisted() {
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

    public UserLoginHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
