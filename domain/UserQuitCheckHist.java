package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.QuitCheckTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 탈퇴회원계정점검이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_quit_check_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserQuitCheckHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 탈퇴계정점검ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "quit_check_id", length = 36, nullable = false, unique = true)
    private String quitCheckId;

    /**
     * 탈퇴계정ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "quit_id", length = 36, nullable = false)
    private String quitId;

    /**
     * 탈퇴점검구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "quit_check_type_code", nullable = false)
    private QuitCheckTypeCode quitCheckTypeCode;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "userQuitCheckHists" }, allowSetters = true)
    private UserQuitMaster userQuitMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserQuitCheckHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public UserQuitMaster getUserQuitMaster() {
        return this.userQuitMaster;
    }

    public void setUserQuitMaster(UserQuitMaster userQuitMaster) {
        this.userQuitMaster = userQuitMaster;
    }

    public UserQuitCheckHist userQuitMaster(UserQuitMaster userQuitMaster) {
        this.setUserQuitMaster(userQuitMaster);
        return this;
    }

    }
