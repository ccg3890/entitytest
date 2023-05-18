package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.RelationTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 추천키워드연관정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_recommend_keyword_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RecommendKeywordRelation extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 추천키워드연관ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_keyword_relation_id", length = 36, nullable = false, unique = true)
    private String recommendKeywordRelationId;

    /**
     * 추천키워드ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_keyword_id", length = 36, nullable = false)
    private String recommendKeywordId;

    /**
     * 연관구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "relation_type_code", nullable = false)
    private RelationTypeCode relationTypeCode;

    /**
     * 연관구분그룹값
     */
    @Size(max = 36)
    @Column(name = "relation_type_group_value", length = 36)
    private String relationTypeGroupValue;

    /**
     * 연관구분값
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "relation_type_value", length = 36, nullable = false)
    private String relationTypeValue;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "recommendKeywordHists", "recommendKeywordRelations" }, allowSetters = true)
    private RecommendKeywordMaster recommendKeywordMaster;

    @ManyToOne
    @JsonIgnoreProperties(value = { "recommendKeywordRelations", "sysCodeDetails" }, allowSetters = true)
    private SysCodeGroup sysCodeGroup;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public RecommendKeywordRelation setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public RecommendKeywordMaster getRecommendKeywordMaster() {
        return this.recommendKeywordMaster;
    }

    public void setRecommendKeywordMaster(RecommendKeywordMaster recommendKeywordMaster) {
        this.recommendKeywordMaster = recommendKeywordMaster;
    }

    public RecommendKeywordRelation recommendKeywordMaster(RecommendKeywordMaster recommendKeywordMaster) {
        this.setRecommendKeywordMaster(recommendKeywordMaster);
        return this;
    }

    public SysCodeGroup getSysCodeGroup() {
        return this.sysCodeGroup;
    }

    public void setSysCodeGroup(SysCodeGroup sysCodeGroup) {
        this.sysCodeGroup = sysCodeGroup;
    }

    public RecommendKeywordRelation sysCodeGroup(SysCodeGroup sysCodeGroup) {
        this.setSysCodeGroup(sysCodeGroup);
        return this;
    }

    }
