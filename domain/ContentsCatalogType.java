package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
 * 콘텐츠카테고리구분
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_catalog_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsCatalogType extends AbstractAuditingEntity<String> implements Persistable<String> {

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
     * 콘텐츠카테고리유형코드
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "contents_catalog_class_code", length = 20, nullable = false)
    private String contentsCatalogClassCode;

    /**
     * 콘텐츠카테고리유형값
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "contents_catalog_valuse", length = 20, nullable = false)
    private String contentsCatalogValuse;

    /**
     * 적용여부
     */
    @NotNull
    @Column(name = "apply_yn", nullable = false)
    private Boolean applyYn;

    
    @OneToMany(mappedBy = "contentsCatalogType")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<ContentsCatalogMaster> contentsCatalogMasters ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsCatalogType setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<ContentsCatalogMaster> getContentsCatalogMasters() {
        return this.contentsCatalogMasters;
    }

    public void setContentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        if (this.contentsCatalogMasters != null) {
            this.contentsCatalogMasters.forEach(i -> i.setContentsCatalogType(null));
        }
        if (contentsCatalogMasters != null) {
            contentsCatalogMasters.forEach(i -> i.setContentsCatalogType(this));
        }
        this.contentsCatalogMasters = contentsCatalogMasters;
    }

    public ContentsCatalogType contentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        this.setContentsCatalogMasters(contentsCatalogMasters);
        return this;
    }

    public ContentsCatalogType addContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.add(contentsCatalogMaster);
        contentsCatalogMaster.setContentsCatalogType(this);
        return this;
    }

    public ContentsCatalogType removeContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.remove(contentsCatalogMaster);
        contentsCatalogMaster.setContentsCatalogType(null);
        return this;
    }

    }
