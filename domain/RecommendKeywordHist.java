package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
 * 추천키워드이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_recommend_keyword_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RecommendKeywordHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 추천키워드UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_keyword_uuid", length = 36, nullable = false, unique = true)
    private String recommendKeywordUuid;

    /**
     * 추천키워드ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_keyword_id", length = 36, nullable = false)
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

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "recommendKeywordHists", "recommendKeywordRelations" }, allowSetters = true)
    private RecommendKeywordMaster recommendKeywordMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public RecommendKeywordHist setIsPersisted() {
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

    public RecommendKeywordHist recommendKeywordMaster(RecommendKeywordMaster recommendKeywordMaster) {
        this.setRecommendKeywordMaster(recommendKeywordMaster);
        return this;
    }

    }
