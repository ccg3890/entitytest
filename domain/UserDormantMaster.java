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
 * 휴면회원계정원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_dormant_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserDormantMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

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
     * 휴면전환일자
     */
    @NotNull
    @Column(name = "dormant_transfer_date", nullable = false)
    private LocalDate dormantTransferDate;

    
    @OneToMany(mappedBy = "userDormantMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "sendInformTalkMaster", "sendMailMaster", "userMaster", "userVerifyHist", "userDormantMaster" },
        allowSetters = true
    )
    private Set<UserDormantHist> userDormantHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserDormantMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<UserDormantHist> getUserDormantHists() {
        return this.userDormantHists;
    }

    public void setUserDormantHists(Set<UserDormantHist> userDormantHists) {
        if (this.userDormantHists != null) {
            this.userDormantHists.forEach(i -> i.setUserDormantMaster(null));
        }
        if (userDormantHists != null) {
            userDormantHists.forEach(i -> i.setUserDormantMaster(this));
        }
        this.userDormantHists = userDormantHists;
    }

    public UserDormantMaster userDormantHists(Set<UserDormantHist> userDormantHists) {
        this.setUserDormantHists(userDormantHists);
        return this;
    }

    public UserDormantMaster addUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.add(userDormantHist);
        userDormantHist.setUserDormantMaster(this);
        return this;
    }

    public UserDormantMaster removeUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.remove(userDormantHist);
        userDormantHist.setUserDormantMaster(null);
        return this;
    }

    }
