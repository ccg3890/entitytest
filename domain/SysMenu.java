package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.MenuFormTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 메뉴구성
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysMenu extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 메뉴구성ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "menu_id", length = 36, nullable = false, unique = true)
    private String menuId;

    /**
     * 메뉴명
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "menu_name", length = 100, nullable = false)
    private String menuName;

    /**
     * 상위메뉴구성ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "top_menu_id", length = 36, nullable = false)
    private String topMenuId;

    /**
     * 메뉴형태구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "menu_form_type_code", nullable = false)
    private MenuFormTypeCode menuFormTypeCode;

    /**
     * 화면프로그램ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "screen_pgm_id", length = 100, nullable = false)
    private String screenPgmId;

    /**
     * 실행URL주소
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "execute_url_address", length = 300, nullable = false)
    private String executeUrlAddress;

    /**
     * 현재사용여부
     */
    @NotNull
    @Column(name = "presend_use_yn", nullable = false)
    private Boolean presendUseYn;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = { "userMaster", "mainScreenConsists", "pgmCallLists", "popupScreenConsists", "sysMenus" },
        allowSetters = true
    )
    private PgmList pgmList;

    @OneToMany(mappedBy = "sysMenu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sysMenu", "userBusiType" }, allowSetters = true)
    private Set<UserMenu> userMenus ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SysMenu setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public PgmList getPgmList() {
        return this.pgmList;
    }

    public void setPgmList(PgmList pgmList) {
        this.pgmList = pgmList;
    }

    public SysMenu pgmList(PgmList pgmList) {
        this.setPgmList(pgmList);
        return this;
    }

    public Set<UserMenu> getUserMenus() {
        return this.userMenus;
    }

    public void setUserMenus(Set<UserMenu> userMenus) {
        if (this.userMenus != null) {
            this.userMenus.forEach(i -> i.setSysMenu(null));
        }
        if (userMenus != null) {
            userMenus.forEach(i -> i.setSysMenu(this));
        }
        this.userMenus = userMenus;
    }

    public SysMenu userMenus(Set<UserMenu> userMenus) {
        this.setUserMenus(userMenus);
        return this;
    }

    public SysMenu addUserMenu(UserMenu userMenu) {
        this.userMenus.add(userMenu);
        userMenu.setSysMenu(this);
        return this;
    }

    public SysMenu removeUserMenu(UserMenu userMenu) {
        this.userMenus.remove(userMenu);
        userMenu.setSysMenu(null);
        return this;
    }

    }
