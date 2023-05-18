package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
 * 콘텐츠취급권한그룹
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsRole extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠취급권한ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_role_id", length = 36, nullable = false, unique = true)
    private String contentsRoleId;

    /**
     * 콘텐츠취급권한명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "contents_role_name", length = 300, nullable = false)
    private String contentsRoleName;

    /**
     * 회원구분ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_busi_type_id", length = 36, nullable = false)
    private String userBusiTypeId;

    /**
     * 콘텐츠취급권한설명
     */
    @Size(max = 4000)
    @Column(name = "contents_role_explain", length = 4000)
    private String contentsRoleExplain;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsRoles", "userMasters", "userMenus" }, allowSetters = true)
    private UserBusiType userBusiType;

    @OneToMany(mappedBy = "contentsRole")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsRole", "userMaster", "contentsRoleApproveHists" }, allowSetters = true)
    private Set<ContentsRoleRequest> contentsRoleRequests ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsRole setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public UserBusiType getUserBusiType() {
        return this.userBusiType;
    }

    public void setUserBusiType(UserBusiType userBusiType) {
        this.userBusiType = userBusiType;
    }

    public ContentsRole userBusiType(UserBusiType userBusiType) {
        this.setUserBusiType(userBusiType);
        return this;
    }

    public Set<ContentsRoleRequest> getContentsRoleRequests() {
        return this.contentsRoleRequests;
    }

    public void setContentsRoleRequests(Set<ContentsRoleRequest> contentsRoleRequests) {
        if (this.contentsRoleRequests != null) {
            this.contentsRoleRequests.forEach(i -> i.setContentsRole(null));
        }
        if (contentsRoleRequests != null) {
            contentsRoleRequests.forEach(i -> i.setContentsRole(this));
        }
        this.contentsRoleRequests = contentsRoleRequests;
    }

    public ContentsRole contentsRoleRequests(Set<ContentsRoleRequest> contentsRoleRequests) {
        this.setContentsRoleRequests(contentsRoleRequests);
        return this;
    }

    public ContentsRole addContentsRoleRequest(ContentsRoleRequest contentsRoleRequest) {
        this.contentsRoleRequests.add(contentsRoleRequest);
        contentsRoleRequest.setContentsRole(this);
        return this;
    }

    public ContentsRole removeContentsRoleRequest(ContentsRoleRequest contentsRoleRequest) {
        this.contentsRoleRequests.remove(contentsRoleRequest);
        contentsRoleRequest.setContentsRole(null);
        return this;
    }

    }
