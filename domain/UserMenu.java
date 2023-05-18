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
 * 회원구분별메뉴접근권한
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserMenu extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 회원구분메뉴ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_menu_id", length = 36, nullable = false, unique = true)
    private String userMenuId;

    /**
     * 회원구분ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_busi_type_id", length = 36, nullable = false)
    private String userBusiTypeId;

    /**
     * 메뉴구성ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "menu_id", length = 36, nullable = false)
    private String menuId;

    /**
     * 메뉴접근가능여부
     */
    @NotNull
    @Column(name = "menu_access_possible_yn", nullable = false)
    private Boolean menuAccessPossibleYn;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "pgmList", "userMenus" }, allowSetters = true)
    private SysMenu sysMenu;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsRoles", "userMasters", "userMenus" }, allowSetters = true)
    private UserBusiType userBusiType;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserMenu setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public SysMenu getSysMenu() {
        return this.sysMenu;
    }

    public void setSysMenu(SysMenu sysMenu) {
        this.sysMenu = sysMenu;
    }

    public UserMenu sysMenu(SysMenu sysMenu) {
        this.setSysMenu(sysMenu);
        return this;
    }

    public UserBusiType getUserBusiType() {
        return this.userBusiType;
    }

    public void setUserBusiType(UserBusiType userBusiType) {
        this.userBusiType = userBusiType;
    }

    public UserMenu userBusiType(UserBusiType userBusiType) {
        this.setUserBusiType(userBusiType);
        return this;
    }

    }
