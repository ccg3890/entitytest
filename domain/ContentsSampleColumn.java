package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.SampleDataTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠샘플데이터컬럼정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_sample_column")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsSampleColumn extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠샘플데이터컬럼ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_sample_column_id", length = 36, nullable = false, unique = true)
    private String contentsSampleColumnId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

    /**
     * 샘플컬럼순번
     */
    @NotNull
    @Column(name = "sample_column_turn", nullable = false)
    private Long sampleColumnTurn;

    /**
     * 샘플컬럼ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sample_column_id", length = 100, nullable = false)
    private String sampleColumnId;

    /**
     * 샘플컬럼명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "sample_column_name", length = 300, nullable = false)
    private String sampleColumnName;

    /**
     * 샘플데이터구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sample_data_type_code", nullable = false)
    private SampleDataTypeCode sampleDataTypeCode;

    /**
     * 샘플데이터길이
     */
    @Column(name = "sample_data_length")
    private Long sampleDataLength;

    /**
     * 샘플NULL값가능여부
     */
    @NotNull
    @Column(name = "sample_nullable_yn", nullable = false)
    private Boolean sampleNullableYn;

    /**
     * 샘플PK여부
     */
    @NotNull
    @Column(name = "sample_primary_key_yn", nullable = false)
    private Boolean samplePrimaryKeyYn;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "attachDocMaster",
            "calculateCharge",
            "companyMaster",
            "customContentsInquiryMaster",
            "contentsBuyMasters",
            "contentsCategoryTypes",
            "contentsRelations",
            "contentsDetailMasters",
            "recommendContentsMasters",
            "contentsSaleApproveHists",
            "shoppingBasketMasters",
            "contentsInquiryMasters",
            "contentsStatisticMasters",
            "contentsSampleColumns",
            "contentsPurchaseRestricts",
        },
        allowSetters = true
    )
    private ContentsCatalogMaster contentsCatalogMaster;

    @OneToMany(mappedBy = "contentsSampleColumn")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsSampleColumn" }, allowSetters = true)
    private Set<ContentsSampleData> contentsSampleData ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsSampleColumn setIsPersisted() {
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

    public ContentsSampleColumn contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    public Set<ContentsSampleData> getContentsSampleData() {
        return this.contentsSampleData;
    }

    public void setContentsSampleData(Set<ContentsSampleData> contentsSampleData) {
        if (this.contentsSampleData != null) {
            this.contentsSampleData.forEach(i -> i.setContentsSampleColumn(null));
        }
        if (contentsSampleData != null) {
            contentsSampleData.forEach(i -> i.setContentsSampleColumn(this));
        }
        this.contentsSampleData = contentsSampleData;
    }

    public ContentsSampleColumn contentsSampleData(Set<ContentsSampleData> contentsSampleData) {
        this.setContentsSampleData(contentsSampleData);
        return this;
    }

    public ContentsSampleColumn addContentsSampleData(ContentsSampleData contentsSampleData) {
        this.contentsSampleData.add(contentsSampleData);
        contentsSampleData.setContentsSampleColumn(this);
        return this;
    }

    public ContentsSampleColumn removeContentsSampleData(ContentsSampleData contentsSampleData) {
        this.contentsSampleData.remove(contentsSampleData);
        contentsSampleData.setContentsSampleColumn(null);
        return this;
    }

    }
