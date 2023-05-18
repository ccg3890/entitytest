package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.RoleApproveStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠취급권한승인처리이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_role_approve_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsRoleApproveHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠취급권한승인처리ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_role_approve_id", length = 36, nullable = false, unique = true)
    private String contentsRoleApproveId;

    /**
     * 콘텐츠취급권한신청ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_role_request_id", length = 36, nullable = false)
    private String contentsRoleRequestId;

    /**
     * 승인자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "approve_user_id", length = 20, nullable = false)
    private String approveUserId;

    /**
     * 권한승인상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_approve_status_code", nullable = false)
    private RoleApproveStatusCode roleApproveStatusCode;

    /**
     * 권한승인사유
     */
    @Size(max = 4000)
    @Column(name = "role_approve_reason", length = 4000)
    private String roleApproveReason;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsRole", "userMaster", "contentsRoleApproveHists" }, allowSetters = true)
    private ContentsRoleRequest contentsRoleRequest;

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

    public ContentsRoleApproveHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public ContentsRoleRequest getContentsRoleRequest() {
        return this.contentsRoleRequest;
    }

    public void setContentsRoleRequest(ContentsRoleRequest contentsRoleRequest) {
        this.contentsRoleRequest = contentsRoleRequest;
    }

    public ContentsRoleApproveHist contentsRoleRequest(ContentsRoleRequest contentsRoleRequest) {
        this.setContentsRoleRequest(contentsRoleRequest);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public ContentsRoleApproveHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
