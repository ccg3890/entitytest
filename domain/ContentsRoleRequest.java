package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ContentsCatalogClassCode;
import net.lotte.marketplace.domain.enumeration.ContentsCatalogClassValue;
import net.lotte.marketplace.domain.enumeration.RoleApproveStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠취급권한신청내역
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_role_request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsRoleRequest extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠취급권한신청ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_role_request_id", length = 36, nullable = false, unique = true)
    private String contentsRoleRequestId;

    /**
     * 콘텐츠취급권한ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_role_id", length = 36, nullable = false)
    private String contentsRoleId;

    /**
     * 콘텐츠카탈로그유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contents_catalog_class_code", nullable = false)
    private ContentsCatalogClassCode contentsCatalogClassCode;

    /**
     * 콘텐츠카탈로그유형값
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contents_catalog_class_value", nullable = false)
    private ContentsCatalogClassValue contentsCatalogClassValue;

    /**
     * 신청자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "request_user_id", length = 20, nullable = false)
    private String requestUserId;

    /**
     * 권한승인상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_approve_status_code", nullable = false)
    private RoleApproveStatusCode roleApproveStatusCode;

    /**
     * 권한신청사유
     */
    @Size(max = 4000)
    @Column(name = "role_request_reason", length = 4000)
    private String roleRequestReason;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "userBusiType", "contentsRoleRequests" }, allowSetters = true)
    private ContentsRole contentsRole;

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

    @OneToMany(mappedBy = "contentsRoleRequest")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsRoleRequest", "userMaster" }, allowSetters = true)
    private Set<ContentsRoleApproveHist> contentsRoleApproveHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsRoleRequest setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public ContentsRole getContentsRole() {
        return this.contentsRole;
    }

    public void setContentsRole(ContentsRole contentsRole) {
        this.contentsRole = contentsRole;
    }

    public ContentsRoleRequest contentsRole(ContentsRole contentsRole) {
        this.setContentsRole(contentsRole);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public ContentsRoleRequest userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<ContentsRoleApproveHist> getContentsRoleApproveHists() {
        return this.contentsRoleApproveHists;
    }

    public void setContentsRoleApproveHists(Set<ContentsRoleApproveHist> contentsRoleApproveHists) {
        if (this.contentsRoleApproveHists != null) {
            this.contentsRoleApproveHists.forEach(i -> i.setContentsRoleRequest(null));
        }
        if (contentsRoleApproveHists != null) {
            contentsRoleApproveHists.forEach(i -> i.setContentsRoleRequest(this));
        }
        this.contentsRoleApproveHists = contentsRoleApproveHists;
    }

    public ContentsRoleRequest contentsRoleApproveHists(Set<ContentsRoleApproveHist> contentsRoleApproveHists) {
        this.setContentsRoleApproveHists(contentsRoleApproveHists);
        return this;
    }

    public ContentsRoleRequest addContentsRoleApproveHist(ContentsRoleApproveHist contentsRoleApproveHist) {
        this.contentsRoleApproveHists.add(contentsRoleApproveHist);
        contentsRoleApproveHist.setContentsRoleRequest(this);
        return this;
    }

    public ContentsRoleRequest removeContentsRoleApproveHist(ContentsRoleApproveHist contentsRoleApproveHist) {
        this.contentsRoleApproveHists.remove(contentsRoleApproveHist);
        contentsRoleApproveHist.setContentsRoleRequest(null);
        return this;
    }

    }
