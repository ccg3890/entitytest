package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.QuitTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 탈퇴회원계정
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_quit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserQuit extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 사용자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * 탈퇴구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "quit_type_code", nullable = false)
    private QuitTypeCode quitTypeCode;

    /**
     * 관리자회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "admin_user_id", length = 20, nullable = false)
    private String adminUserId;

    /**
     * 즉시삭제여부
     */
    @NotNull
    @Column(name = "immediate_delete_yn", nullable = false)
    private Boolean immediateDeleteYn;

    /**
     * 탈퇴점검완료여부
     */
    @NotNull
    @Column(name = "quit_check_complete_yn", nullable = false)
    private Boolean quitCheckCompleteYn;

    
    @OneToMany(mappedBy = "userQuit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "userQuit" }, allowSetters = true)
    private Set<UserQuitCheckHist> userQuitCheckHists ;

    @JsonIgnoreProperties(
        value = {
            "userQuit",
            "userAgreements",
            "userDormantHists",
            "userVerifyHists",
            "userQuitHists",
            "userLockHists",
            "userLoginHists",
            "userApproveHists",
            "userChangeHists",
            "batchPgmLists",
            "userBusiType",
            "companyMaster",
        },
        allowSetters = true
    )
    @OneToOne(mappedBy = "userQuit")
    private UserMaster userMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserQuit setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<UserQuitCheckHist> getUserQuitCheckHists() {
        return this.userQuitCheckHists;
    }

    public void setUserQuitCheckHists(Set<UserQuitCheckHist> userQuitCheckHists) {
        if (this.userQuitCheckHists != null) {
            this.userQuitCheckHists.forEach(i -> i.setUserQuit(null));
        }
        if (userQuitCheckHists != null) {
            userQuitCheckHists.forEach(i -> i.setUserQuit(this));
        }
        this.userQuitCheckHists = userQuitCheckHists;
    }

    public UserQuit userQuitCheckHists(Set<UserQuitCheckHist> userQuitCheckHists) {
        this.setUserQuitCheckHists(userQuitCheckHists);
        return this;
    }

    public UserQuit addUserQuitCheckHist(UserQuitCheckHist userQuitCheckHist) {
        this.userQuitCheckHists.add(userQuitCheckHist);
        userQuitCheckHist.setUserQuit(this);
        return this;
    }

    public UserQuit removeUserQuitCheckHist(UserQuitCheckHist userQuitCheckHist) {
        this.userQuitCheckHists.remove(userQuitCheckHist);
        userQuitCheckHist.setUserQuit(null);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        if (this.userMaster != null) {
            this.userMaster.setUserQuit(null);
        }
        if (userMaster != null) {
            userMaster.setUserQuit(this);
        }
        this.userMaster = userMaster;
    }

    public UserQuit userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
