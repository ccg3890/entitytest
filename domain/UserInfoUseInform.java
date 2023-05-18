package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.UseInformClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 회원계정정보이용내역알림
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_info_use_inform")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserInfoUseInform extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 이용내역알림ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "use_inform_id", length = 36, nullable = false, unique = true)
    private String useInformId;

    /**
     * 이용내역알림유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "use_inform_class_code", nullable = false)
    private UseInformClassCode useInformClassCode;

    /**
     * 이용내역알림일자
     */
    @NotNull
    @Column(name = "use_inform_date", nullable = false)
    private LocalDate useInformDate;

    
    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserInfoUseInform setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    }
