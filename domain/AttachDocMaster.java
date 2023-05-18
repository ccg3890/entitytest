package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.AttachDocOwnerClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 첨부문서원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_attach_doc_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttachDocMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 첨부문서UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "attach_doc_uuid", length = 36, nullable = false, unique = true)
    private String attachDocUuid;

    /**
     * 첨부문서명
     */
    @NotNull
    @Size(max = 600)
    @Column(name = "attach_doc_name", length = 600, nullable = false)
    private String attachDocName;

    /**
     * 첨부문서설명
     */
    @Size(max = 4000)
    @Column(name = "attach_doc_explain", length = 4000)
    private String attachDocExplain;

    /**
     * 첨부문서ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "attach_doc_id", length = 100, nullable = false)
    private String attachDocId;

    /**
     * 첨부문서위치
     */
    @Size(max = 1000)
    @Column(name = "attach_doc_position", length = 1000)
    private String attachDocPosition;

    /**
     * 첨부문서소유자유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "attach_doc_owner_class_code", nullable = false)
    private AttachDocOwnerClassCode attachDocOwnerClassCode;

    /**
     * 첨부문서소유자ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "attach_doc_owner_id", length = 36, nullable = false)
    private String attachDocOwnerId;

    
    @OneToMany(mappedBy = "attachDocMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "attachDocMaster", "userMasters", "contentsCatalogMasters", "affiliateIntroduces", "interfaceLists", "newsInfos" },
        allowSetters = true
    )
    private Set<CompanyMaster> companyMasters ;

    @OneToMany(mappedBy = "attachDocMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "attachDocMaster" }, allowSetters = true)
    private Set<AttachDocHist> attachDocHists ;

    @OneToMany(mappedBy = "attachDocMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<ContentsCatalogMaster> contentsCatalogMasters ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public AttachDocMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<CompanyMaster> getCompanyMasters() {
        return this.companyMasters;
    }

    public void setCompanyMasters(Set<CompanyMaster> companyMasters) {
        if (this.companyMasters != null) {
            this.companyMasters.forEach(i -> i.setAttachDocMaster(null));
        }
        if (companyMasters != null) {
            companyMasters.forEach(i -> i.setAttachDocMaster(this));
        }
        this.companyMasters = companyMasters;
    }

    public AttachDocMaster companyMasters(Set<CompanyMaster> companyMasters) {
        this.setCompanyMasters(companyMasters);
        return this;
    }

    public AttachDocMaster addCompanyMaster(CompanyMaster companyMaster) {
        this.companyMasters.add(companyMaster);
        companyMaster.setAttachDocMaster(this);
        return this;
    }

    public AttachDocMaster removeCompanyMaster(CompanyMaster companyMaster) {
        this.companyMasters.remove(companyMaster);
        companyMaster.setAttachDocMaster(null);
        return this;
    }

    public Set<AttachDocHist> getAttachDocHists() {
        return this.attachDocHists;
    }

    public void setAttachDocHists(Set<AttachDocHist> attachDocHists) {
        if (this.attachDocHists != null) {
            this.attachDocHists.forEach(i -> i.setAttachDocMaster(null));
        }
        if (attachDocHists != null) {
            attachDocHists.forEach(i -> i.setAttachDocMaster(this));
        }
        this.attachDocHists = attachDocHists;
    }

    public AttachDocMaster attachDocHists(Set<AttachDocHist> attachDocHists) {
        this.setAttachDocHists(attachDocHists);
        return this;
    }

    public AttachDocMaster addAttachDocHist(AttachDocHist attachDocHist) {
        this.attachDocHists.add(attachDocHist);
        attachDocHist.setAttachDocMaster(this);
        return this;
    }

    public AttachDocMaster removeAttachDocHist(AttachDocHist attachDocHist) {
        this.attachDocHists.remove(attachDocHist);
        attachDocHist.setAttachDocMaster(null);
        return this;
    }

    public Set<ContentsCatalogMaster> getContentsCatalogMasters() {
        return this.contentsCatalogMasters;
    }

    public void setContentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        if (this.contentsCatalogMasters != null) {
            this.contentsCatalogMasters.forEach(i -> i.setAttachDocMaster(null));
        }
        if (contentsCatalogMasters != null) {
            contentsCatalogMasters.forEach(i -> i.setAttachDocMaster(this));
        }
        this.contentsCatalogMasters = contentsCatalogMasters;
    }

    public AttachDocMaster contentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        this.setContentsCatalogMasters(contentsCatalogMasters);
        return this;
    }

    public AttachDocMaster addContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.add(contentsCatalogMaster);
        contentsCatalogMaster.setAttachDocMaster(this);
        return this;
    }

    public AttachDocMaster removeContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.remove(contentsCatalogMaster);
        contentsCatalogMaster.setAttachDocMaster(null);
        return this;
    }

    }
