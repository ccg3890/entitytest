package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.PgmTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 프로그램목록
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pgm_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PgmList extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 프로그램UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "pgm_uuid", length = 36, nullable = false, unique = true)
    private String pgmUuid;

    /**
     * 프로그램ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "pgm_id", length = 100, nullable = false)
    private String pgmId;

    /**
     * 프로그램명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "pgm_name", length = 300, nullable = false)
    private String pgmName;

    /**
     * 프로그램구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "pgm_type_code", nullable = false)
    private PgmTypeCode pgmTypeCode;

    /**
     * 프로그램담당자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "pgm_user_id", length = 20, nullable = false)
    private String pgmUserId;

    /**
     * 프로그램패키지명
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "pgm_package_name", length = 1000, nullable = false)
    private String pgmPackageName;

    /**
     * 프로그램파일명
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "pgm_file_name", length = 1000, nullable = false)
    private String pgmFileName;

    /**
     * 삭제여부
     */
    @NotNull
    @Column(name = "delete_yn", nullable = false)
    private Boolean deleteYn;

    /**
     * 삭제사유
     */
    @Size(max = 4000)
    @Column(name = "delete_reason", length = 4000)
    private String deleteReason;

    
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

    @OneToMany(mappedBy = "pgmList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "pgmList" }, allowSetters = true)
    private Set<MainScreenConsist> mainScreenConsists ;

    @OneToMany(mappedBy = "pgmList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "pgmList" }, allowSetters = true)
    private Set<PgmCallList> pgmCallLists ;

    @OneToMany(mappedBy = "pgmList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "pgmList", "popupTemplate" }, allowSetters = true)
    private Set<PopupScreenConsist> popupScreenConsists ;

    @OneToMany(mappedBy = "pgmList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "pgmList", "userMenus" }, allowSetters = true)
    private Set<SysMenu> sysMenus ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public PgmList setIsPersisted() {
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

    public PgmList userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<MainScreenConsist> getMainScreenConsists() {
        return this.mainScreenConsists;
    }

    public void setMainScreenConsists(Set<MainScreenConsist> mainScreenConsists) {
        if (this.mainScreenConsists != null) {
            this.mainScreenConsists.forEach(i -> i.setPgmList(null));
        }
        if (mainScreenConsists != null) {
            mainScreenConsists.forEach(i -> i.setPgmList(this));
        }
        this.mainScreenConsists = mainScreenConsists;
    }

    public PgmList mainScreenConsists(Set<MainScreenConsist> mainScreenConsists) {
        this.setMainScreenConsists(mainScreenConsists);
        return this;
    }

    public PgmList addMainScreenConsist(MainScreenConsist mainScreenConsist) {
        this.mainScreenConsists.add(mainScreenConsist);
        mainScreenConsist.setPgmList(this);
        return this;
    }

    public PgmList removeMainScreenConsist(MainScreenConsist mainScreenConsist) {
        this.mainScreenConsists.remove(mainScreenConsist);
        mainScreenConsist.setPgmList(null);
        return this;
    }

    public Set<PgmCallList> getPgmCallLists() {
        return this.pgmCallLists;
    }

    public void setPgmCallLists(Set<PgmCallList> pgmCallLists) {
        if (this.pgmCallLists != null) {
            this.pgmCallLists.forEach(i -> i.setPgmList(null));
        }
        if (pgmCallLists != null) {
            pgmCallLists.forEach(i -> i.setPgmList(this));
        }
        this.pgmCallLists = pgmCallLists;
    }

    public PgmList pgmCallLists(Set<PgmCallList> pgmCallLists) {
        this.setPgmCallLists(pgmCallLists);
        return this;
    }

    public PgmList addPgmCallList(PgmCallList pgmCallList) {
        this.pgmCallLists.add(pgmCallList);
        pgmCallList.setPgmList(this);
        return this;
    }

    public PgmList removePgmCallList(PgmCallList pgmCallList) {
        this.pgmCallLists.remove(pgmCallList);
        pgmCallList.setPgmList(null);
        return this;
    }

    public Set<PopupScreenConsist> getPopupScreenConsists() {
        return this.popupScreenConsists;
    }

    public void setPopupScreenConsists(Set<PopupScreenConsist> popupScreenConsists) {
        if (this.popupScreenConsists != null) {
            this.popupScreenConsists.forEach(i -> i.setPgmList(null));
        }
        if (popupScreenConsists != null) {
            popupScreenConsists.forEach(i -> i.setPgmList(this));
        }
        this.popupScreenConsists = popupScreenConsists;
    }

    public PgmList popupScreenConsists(Set<PopupScreenConsist> popupScreenConsists) {
        this.setPopupScreenConsists(popupScreenConsists);
        return this;
    }

    public PgmList addPopupScreenConsist(PopupScreenConsist popupScreenConsist) {
        this.popupScreenConsists.add(popupScreenConsist);
        popupScreenConsist.setPgmList(this);
        return this;
    }

    public PgmList removePopupScreenConsist(PopupScreenConsist popupScreenConsist) {
        this.popupScreenConsists.remove(popupScreenConsist);
        popupScreenConsist.setPgmList(null);
        return this;
    }

    public Set<SysMenu> getSysMenus() {
        return this.sysMenus;
    }

    public void setSysMenus(Set<SysMenu> sysMenus) {
        if (this.sysMenus != null) {
            this.sysMenus.forEach(i -> i.setPgmList(null));
        }
        if (sysMenus != null) {
            sysMenus.forEach(i -> i.setPgmList(this));
        }
        this.sysMenus = sysMenus;
    }

    public PgmList sysMenus(Set<SysMenu> sysMenus) {
        this.setSysMenus(sysMenus);
        return this;
    }

    public PgmList addSysMenu(SysMenu sysMenu) {
        this.sysMenus.add(sysMenu);
        sysMenu.setPgmList(this);
        return this;
    }

    public PgmList removeSysMenu(SysMenu sysMenu) {
        this.sysMenus.remove(sysMenu);
        sysMenu.setPgmList(null);
        return this;
    }

    }
