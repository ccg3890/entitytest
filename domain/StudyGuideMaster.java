package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.StudyGuideTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 학습가이드원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_study_guide_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StudyGuideMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 학습가이드ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "study_guide_id", length = 36, nullable = false, unique = true)
    private String studyGuideId;

    /**
     * 학습가이드명
     */
    @NotNull
    @Size(max = 500)
    @Column(name = "study_guide_name", length = 500, nullable = false)
    private String studyGuideName;

    /**
     * 학습가이드구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "study_guide_type_code", nullable = false)
    private StudyGuideTypeCode studyGuideTypeCode;

    /**
     * 학습가이드설명순번
     */
    @NotNull
    @Column(name = "study_guide_explain_turn", nullable = false)
    private Long studyGuideExplainTurn;

    /**
     * 학습가이드설명
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "study_guide_explain", length = 1000, nullable = false)
    private String studyGuideExplain;

    
    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public StudyGuideMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    }
