package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.RecommendKeywordStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 추천키워드원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_recommend_keyword_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RecommendKeywordMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 추천키워드ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_keyword_id", length = 36, nullable = false, unique = true)
    private String recommendKeywordId;

    /**
     * 추천키워드명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "recommend_keyword_name", length = 300, nullable = false)
    private String recommendKeywordName;

    /**
     * 추천키워드상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "recommend_keyword_status_code", nullable = false)
    private RecommendKeywordStatusCode recommendKeywordStatusCode;

    /**
     * 추천키워드설명
     */
    @Size(max = 4000)
    @Column(name = "recommend_keyword_explain", length = 4000)
    private String recommendKeywordExplain;

    
    @OneToMany(mappedBy = "recommendKeywordMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "recommendKeywordMaster" }, allowSetters = true)
    private Set<RecommendKeywordHist> recommendKeywordHists ;

    @OneToMany(mappedBy = "recommendKeywordMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "recommendKeywordMaster", "sysCodeGroup" }, allowSetters = true)
    private Set<RecommendKeywordRelation> recommendKeywordRelations ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public RecommendKeywordMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<RecommendKeywordHist> getRecommendKeywordHists() {
        return this.recommendKeywordHists;
    }

    public void setRecommendKeywordHists(Set<RecommendKeywordHist> recommendKeywordHists) {
        if (this.recommendKeywordHists != null) {
            this.recommendKeywordHists.forEach(i -> i.setRecommendKeywordMaster(null));
        }
        if (recommendKeywordHists != null) {
            recommendKeywordHists.forEach(i -> i.setRecommendKeywordMaster(this));
        }
        this.recommendKeywordHists = recommendKeywordHists;
    }

    public RecommendKeywordMaster recommendKeywordHists(Set<RecommendKeywordHist> recommendKeywordHists) {
        this.setRecommendKeywordHists(recommendKeywordHists);
        return this;
    }

    public RecommendKeywordMaster addRecommendKeywordHist(RecommendKeywordHist recommendKeywordHist) {
        this.recommendKeywordHists.add(recommendKeywordHist);
        recommendKeywordHist.setRecommendKeywordMaster(this);
        return this;
    }

    public RecommendKeywordMaster removeRecommendKeywordHist(RecommendKeywordHist recommendKeywordHist) {
        this.recommendKeywordHists.remove(recommendKeywordHist);
        recommendKeywordHist.setRecommendKeywordMaster(null);
        return this;
    }

    public Set<RecommendKeywordRelation> getRecommendKeywordRelations() {
        return this.recommendKeywordRelations;
    }

    public void setRecommendKeywordRelations(Set<RecommendKeywordRelation> recommendKeywordRelations) {
        if (this.recommendKeywordRelations != null) {
            this.recommendKeywordRelations.forEach(i -> i.setRecommendKeywordMaster(null));
        }
        if (recommendKeywordRelations != null) {
            recommendKeywordRelations.forEach(i -> i.setRecommendKeywordMaster(this));
        }
        this.recommendKeywordRelations = recommendKeywordRelations;
    }

    public RecommendKeywordMaster recommendKeywordRelations(Set<RecommendKeywordRelation> recommendKeywordRelations) {
        this.setRecommendKeywordRelations(recommendKeywordRelations);
        return this;
    }

    public RecommendKeywordMaster addRecommendKeywordRelation(RecommendKeywordRelation recommendKeywordRelation) {
        this.recommendKeywordRelations.add(recommendKeywordRelation);
        recommendKeywordRelation.setRecommendKeywordMaster(this);
        return this;
    }

    public RecommendKeywordMaster removeRecommendKeywordRelation(RecommendKeywordRelation recommendKeywordRelation) {
        this.recommendKeywordRelations.remove(recommendKeywordRelation);
        recommendKeywordRelation.setRecommendKeywordMaster(null);
        return this;
    }

    }
