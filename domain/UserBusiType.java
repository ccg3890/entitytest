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
 * 회원구분별업무구분
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_busi_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserBusiType extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 회원구분ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_busi_type_id", length = 36, nullable = false, unique = true)
    private String userBusiTypeId;

    /**
     * 회원구분명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "user_busi_type_name", length = 300, nullable = false)
    private String userBusiTypeName;

    /**
     * 그룹회원승인가능여부
     */
    @NotNull
    @Column(name = "user_approve_possible_yn", nullable = false)
    private Boolean userApprovePossibleYn;

    /**
     * 판매거래가능여부
     */
    @NotNull
    @Column(name = "sale_trade_possible_yn", nullable = false)
    private Boolean saleTradePossibleYn;

    /**
     * 구매거래가능여부
     */
    @NotNull
    @Column(name = "buy_trade_possible_yn", nullable = false)
    private Boolean buyTradePossibleYn;

    /**
     * 분석가인증대상여부
     */
    @NotNull
    @Column(name = "analyst_approve_target_yn", nullable = false)
    private Boolean analystApproveTargetYn;

    /**
     * 회원구분설명
     */
    @Size(max = 4000)
    @Column(name = "user_busi_type_explain", length = 4000)
    private String userBusiTypeExplain;

    
    @OneToMany(mappedBy = "userBusiType")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "userBusiType", "contentsRoleRequests" }, allowSetters = true)
    private Set<ContentsRole> contentsRoles ;

    @OneToMany(mappedBy = "userBusiType")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<UserMaster> userMasters ;

    @OneToMany(mappedBy = "userBusiType")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sysMenu", "userBusiType" }, allowSetters = true)
    private Set<UserMenu> userMenus ;

    public Set<ContentsRole> getContentsRoles() {
        return this.contentsRoles;
    }

    public void setContentsRoles(Set<ContentsRole> contentsRoles) {
        if (this.contentsRoles != null) {
            this.contentsRoles.forEach(i -> i.setUserBusiType(null));
        }
        if (contentsRoles != null) {
            contentsRoles.forEach(i -> i.setUserBusiType(this));
        }
        this.contentsRoles = contentsRoles;
    }

    public UserBusiType contentsRoles(Set<ContentsRole> contentsRoles) {
        this.setContentsRoles(contentsRoles);
        return this;
    }

    public UserBusiType addContentsRole(ContentsRole contentsRole) {
        this.contentsRoles.add(contentsRole);
        contentsRole.setUserBusiType(this);
        return this;
    }

    public UserBusiType removeContentsRole(ContentsRole contentsRole) {
        this.contentsRoles.remove(contentsRole);
        contentsRole.setUserBusiType(null);
        return this;
    }

    public Set<UserMaster> getUserMasters() {
        return this.userMasters;
    }

    public void setUserMasters(Set<UserMaster> userMasters) {
        if (this.userMasters != null) {
            this.userMasters.forEach(i -> i.setUserBusiType(null));
        }
        if (userMasters != null) {
            userMasters.forEach(i -> i.setUserBusiType(this));
        }
        this.userMasters = userMasters;
    }

    public UserBusiType userMasters(Set<UserMaster> userMasters) {
        this.setUserMasters(userMasters);
        return this;
    }

    public UserBusiType addUserMaster(UserMaster userMaster) {
        this.userMasters.add(userMaster);
        userMaster.setUserBusiType(this);
        return this;
    }

    public UserBusiType removeUserMaster(UserMaster userMaster) {
        this.userMasters.remove(userMaster);
        userMaster.setUserBusiType(null);
        return this;
    }

    public Set<UserMenu> getUserMenus() {
        return this.userMenus;
    }

    public void setUserMenus(Set<UserMenu> userMenus) {
        if (this.userMenus != null) {
            this.userMenus.forEach(i -> i.setUserBusiType(null));
        }
        if (userMenus != null) {
            userMenus.forEach(i -> i.setUserBusiType(this));
        }
        this.userMenus = userMenus;
    }

    public UserBusiType userMenus(Set<UserMenu> userMenus) {
        this.setUserMenus(userMenus);
        return this;
    }

    public UserBusiType addUserMenu(UserMenu userMenu) {
        this.userMenus.add(userMenu);
        userMenu.setUserBusiType(this);
        return this;
    }

    public UserBusiType removeUserMenu(UserMenu userMenu) {
        this.userMenus.remove(userMenu);
        userMenu.setUserBusiType(null);
        return this;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    }
