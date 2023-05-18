package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
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
 * 탈퇴회원계정원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_quit_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserQuitMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false, unique = true)
    private String userId;

    /**
     * 탈퇴전환일자
     */
    @NotNull
    @Column(name = "quit_transfer_date", nullable = false)
    private LocalDate quitTransferDate;

    
    @OneToMany(mappedBy = "userQuitMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "userQuitMaster" }, allowSetters = true)
    private Set<UserQuitCheckHist> userQuitCheckHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserQuitMaster setIsPersisted() {
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
            this.userQuitCheckHists.forEach(i -> i.setUserQuitMaster(null));
        }
        if (userQuitCheckHists != null) {
            userQuitCheckHists.forEach(i -> i.setUserQuitMaster(this));
        }
        this.userQuitCheckHists = userQuitCheckHists;
    }

    public UserQuitMaster userQuitCheckHists(Set<UserQuitCheckHist> userQuitCheckHists) {
        this.setUserQuitCheckHists(userQuitCheckHists);
        return this;
    }

    public UserQuitMaster addUserQuitCheckHist(UserQuitCheckHist userQuitCheckHist) {
        this.userQuitCheckHists.add(userQuitCheckHist);
        userQuitCheckHist.setUserQuitMaster(this);
        return this;
    }

    public UserQuitMaster removeUserQuitCheckHist(UserQuitCheckHist userQuitCheckHist) {
        this.userQuitCheckHists.remove(userQuitCheckHist);
        userQuitCheckHist.setUserQuitMaster(null);
        return this;
    }

    }
