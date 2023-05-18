package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
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
 * 공통코드상세
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sys_code_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysCodeDetails extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 코드상세UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "code_detail_uuid", length = 36, nullable = false, unique = true)
    private String codeDetailUuid;

    /**
     * 코드그룹ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "code_group_id", length = 36, nullable = false)
    private String codeGroupId;

    /**
     * 코드상세ID
     */
    @NotNull
    @Size(max = 30)
    @Column(name = "code_detail_id", length = 30, nullable = false)
    private String codeDetailId;

    /**
     * 코드상세명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "code_detail_name", length = 300, nullable = false)
    private String codeDetailName;

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
     * 코드참조인자1
     */
    @Size(max = 100)
    @Column(name = "code_refer_factor_1", length = 100)
    private String codeReferFactor1;

    /**
     * 코드참조인자2
     */
    @Size(max = 100)
    @Column(name = "code_refer_factor_2", length = 100)
    private String codeReferFactor2;

    /**
     * 코드참조인자3
     */
    @Size(max = 100)
    @Column(name = "code_refer_factor_3", length = 100)
    private String codeReferFactor3;

    /**
     * 코드참조인자4
     */
    @Size(max = 100)
    @Column(name = "code_refer_factor_4", length = 100)
    private String codeReferFactor4;

    /**
     * 코드참조인자5
     */
    @Size(max = 100)
    @Column(name = "code_refer_factor_5", length = 100)
    private String codeReferFactor5;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "recommendKeywordRelations", "sysCodeDetails" }, allowSetters = true)
    private SysCodeGroup sysCodeGroup;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SysCodeDetails setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public SysCodeGroup getSysCodeGroup() {
        return this.sysCodeGroup;
    }

    public void setSysCodeGroup(SysCodeGroup sysCodeGroup) {
        this.sysCodeGroup = sysCodeGroup;
    }

    public SysCodeDetails sysCodeGroup(SysCodeGroup sysCodeGroup) {
        this.setSysCodeGroup(sysCodeGroup);
        return this;
    }

    }
