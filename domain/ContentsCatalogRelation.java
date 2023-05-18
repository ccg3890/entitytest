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
 * 콘텐츠연관정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_catalog_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsCatalogRelation extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠연관ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_relation_id", length = 36, nullable = false, unique = true)
    private String contentsRelationId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

    /**
     * 연관유형코드
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "relation_class_code", length = 20, nullable = false)
    private String relationClassCode;

    /**
     * 연관콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "relation_contents_catalog_id", length = 36, nullable = false)
    private String relationContentsCatalogId;

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

    
    @JsonIgnoreProperties(
        value = {
            "companyMasters",
            "contentsSampleColumns",
            "attachDocMasters",
            "contentsDetailMasters",
            "contentsPurchaseRestricts",
            "contentsCatalogType",
            "contentsCatalogRelation",
        },
        allowSetters = true
    )
    @OneToOne
    @JoinColumn(unique = true)
    private ContentsCatalogMaster contentsCatalogMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsCatalogRelation setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public ContentsCatalogMaster getContentsCatalogMaster() {
        return this.contentsCatalogMaster;
    }

    public void setContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMaster = contentsCatalogMaster;
    }

    public ContentsCatalogRelation contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    }
