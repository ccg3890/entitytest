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
 * 공통코드그룹
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sys_code_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SysCodeGroup extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 코드그룹ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "code_group_id", length = 36, nullable = false, unique = true)
    private String codeGroupId;

    /**
     * 코드그룹명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "code_group_name", length = 300, nullable = false)
    private String codeGroupName;

    /**
     * 코드그룹설명
     */
    @Size(max = 900)
    @Column(name = "code_group_explain", length = 900)
    private String codeGroupExplain;

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
     * 코드유형구분
     */
    @Size(max = 36)
    @Column(name = "code_class_type", length = 36)
    private String codeClassType;

    
    @OneToMany(mappedBy = "sysCodeGroup")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "recommendKeywordMaster", "sysCodeGroup" }, allowSetters = true)
    private Set<RecommendKeywordRelation> recommendKeywordRelations ;

    @OneToMany(mappedBy = "sysCodeGroup")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "sysCodeGroup" }, allowSetters = true)
    private Set<SysCodeDetails> sysCodeDetails ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public SysCodeGroup setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<RecommendKeywordRelation> getRecommendKeywordRelations() {
        return this.recommendKeywordRelations;
    }

    public void setRecommendKeywordRelations(Set<RecommendKeywordRelation> recommendKeywordRelations) {
        if (this.recommendKeywordRelations != null) {
            this.recommendKeywordRelations.forEach(i -> i.setSysCodeGroup(null));
        }
        if (recommendKeywordRelations != null) {
            recommendKeywordRelations.forEach(i -> i.setSysCodeGroup(this));
        }
        this.recommendKeywordRelations = recommendKeywordRelations;
    }

    public SysCodeGroup recommendKeywordRelations(Set<RecommendKeywordRelation> recommendKeywordRelations) {
        this.setRecommendKeywordRelations(recommendKeywordRelations);
        return this;
    }

    public SysCodeGroup addRecommendKeywordRelation(RecommendKeywordRelation recommendKeywordRelation) {
        this.recommendKeywordRelations.add(recommendKeywordRelation);
        recommendKeywordRelation.setSysCodeGroup(this);
        return this;
    }

    public SysCodeGroup removeRecommendKeywordRelation(RecommendKeywordRelation recommendKeywordRelation) {
        this.recommendKeywordRelations.remove(recommendKeywordRelation);
        recommendKeywordRelation.setSysCodeGroup(null);
        return this;
    }

    public Set<SysCodeDetails> getSysCodeDetails() {
        return this.sysCodeDetails;
    }

    public void setSysCodeDetails(Set<SysCodeDetails> sysCodeDetails) {
        if (this.sysCodeDetails != null) {
            this.sysCodeDetails.forEach(i -> i.setSysCodeGroup(null));
        }
        if (sysCodeDetails != null) {
            sysCodeDetails.forEach(i -> i.setSysCodeGroup(this));
        }
        this.sysCodeDetails = sysCodeDetails;
    }

    public SysCodeGroup sysCodeDetails(Set<SysCodeDetails> sysCodeDetails) {
        this.setSysCodeDetails(sysCodeDetails);
        return this;
    }

    public SysCodeGroup addSysCodeDetails(SysCodeDetails sysCodeDetails) {
        this.sysCodeDetails.add(sysCodeDetails);
        sysCodeDetails.setSysCodeGroup(this);
        return this;
    }

    public SysCodeGroup removeSysCodeDetails(SysCodeDetails sysCodeDetails) {
        this.sysCodeDetails.remove(sysCodeDetails);
        sysCodeDetails.setSysCodeGroup(null);
        return this;
    }

    }
