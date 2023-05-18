package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ContentsCatalogClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠카테고리구분
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_category_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsCategoryType extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠카테고리ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_category_id", length = 36, nullable = false, unique = true)
    private String contentsCategoryId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

    /**
     * 콘텐츠카탈로그유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contents_catalog_class_code", nullable = false)
    private ContentsCatalogClassCode contentsCatalogClassCode;

    /**
     * 콘텐츠카탈로그유형값
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "contents_catalog_class_valuse", length = 20, nullable = false)
    private String contentsCatalogClassValuse;

    /**
     * 적용여부
     */
    @NotNull
    @Column(name = "apply_yn", nullable = false)
    private Boolean applyYn;

    
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

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsCategoryType setIsPersisted() {
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

    public ContentsCategoryType contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    }
