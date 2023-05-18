package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.MainScreenConsistClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 메인화면구성
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_main_screen_consist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MainScreenConsist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 메인화면구성ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "main_screen_consist_id", length = 36, nullable = false, unique = true)
    private String mainScreenConsistId;

    /**
     * 프로그램UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "pgm_uuid", length = 36, nullable = false)
    private String pgmUuid;

    /**
     * 메인화면구성유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "main_screen_consist_class_code", nullable = false)
    private MainScreenConsistClassCode mainScreenConsistClassCode;

    /**
     * 메인화면구성순위
     */
    @NotNull
    @Column(name = "main_screen_consist_rank", nullable = false)
    private Long mainScreenConsistRank;

    /**
     * 적용시작일자
     */
    @NotNull
    @Column(name = "apply_start_date", nullable = false)
    private LocalDate applyStartDate;

    /**
     * 적용종료일자
     */
    @NotNull
    @Column(name = "apply_end_date", nullable = false)
    private LocalDate applyEndDate;

    /**
     * 프로그램호출인자
     */
    @Size(max = 4000)
    @Column(name = "pgm_call_parameter", length = 4000)
    private String pgmCallParameter;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = { "userMaster", "mainScreenConsists", "pgmCallLists", "popupScreenConsists", "sysMenus" },
        allowSetters = true
    )
    private PgmList pgmList;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public MainScreenConsist setIsPersisted() {
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

    public MainScreenConsist pgmList(PgmList pgmList) {
        this.setPgmList(pgmList);
        return this;
    }

    }
