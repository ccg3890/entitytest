package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.BuyTypeCode;
import net.lotte.marketplace.domain.enumeration.CalculateMethodCode;
import net.lotte.marketplace.domain.enumeration.ProductClassCode;
import net.lotte.marketplace.domain.enumeration.ProductExhibitMethodCode;
import net.lotte.marketplace.domain.enumeration.ProductExtensionCode;
import net.lotte.marketplace.domain.enumeration.ProductInterfaceMethodCode;
import net.lotte.marketplace.domain.enumeration.ProductPriceCode;
import net.lotte.marketplace.domain.enumeration.ProductPurchaseMethodCode;
import net.lotte.marketplace.domain.enumeration.ProductSourcingCode;
import net.lotte.marketplace.domain.enumeration.ProductTypeCode;
import net.lotte.marketplace.domain.enumeration.SaleApproveStatusCode;
import net.lotte.marketplace.domain.enumeration.SampleDataTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠카탈로그원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_catalog_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsCatalogMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false, unique = true)
    private String contentsCatalogId;

    /**
     * 상품명
     */
    @NotNull
    @Size(max = 750)
    @Column(name = "product_name", length = 750, nullable = false)
    private String productName;

    /**
     * 공급사ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "company_id", length = 36, nullable = false)
    private String companyId;

    /**
     * 수수료율ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "charge_rate_id", length = 36, nullable = false)
    private String chargeRateId;

    /**
     * 상품유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_class_code", nullable = false)
    private ProductClassCode productClassCode;

    /**
     * 상품구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type_code", nullable = false)
    private ProductTypeCode productTypeCode;

    /**
     * 상품가격코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_price_code", nullable = false)
    private ProductPriceCode productPriceCode;

    /**
     * 상품소싱코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_sourcing_code", nullable = false)
    private ProductSourcingCode productSourcingCode;

    /**
     * 상품연동방식코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_interface_method_code", nullable = false)
    private ProductInterfaceMethodCode productInterfaceMethodCode;

    /**
     * 상품전시방식코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_exhibit_method_code", nullable = false)
    private ProductExhibitMethodCode productExhibitMethodCode;

    /**
     * 상품구매방식코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_purchase_method_code", nullable = false)
    private ProductPurchaseMethodCode productPurchaseMethodCode;

    /**
     * 상품요약설명
     */
    @NotNull
    @Size(max = 750)
    @Column(name = "product_brief_explain", length = 750, nullable = false)
    private String productBriefExplain;

    /**
     * 상품상세설명
     */
    @NotNull
    @Size(max = 4000)
    @Column(name = "product_detail_explain", length = 4000, nullable = false)
    private String productDetailExplain;

    /**
     * 활용제시
     */
    @NotNull
    @Size(max = 4000)
    @Column(name = "useful_suggest", length = 4000, nullable = false)
    private String usefulSuggest;

    /**
     * 활용사례
     */
    @NotNull
    @Size(max = 4000)
    @Column(name = "useful_example", length = 4000, nullable = false)
    private String usefulExample;

    /**
     * 샘플데이터구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sample_data_type_code", nullable = false)
    private SampleDataTypeCode sampleDataTypeCode;

    /**
     * 샘플데이터참조ID
     */
    @Size(max = 36)
    @Column(name = "sample_data_reference_id", length = 36)
    private String sampleDataReferenceId;

    /**
     * 데이터정의서첨부ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "data_definition_attach_id", length = 36, nullable = false)
    private String dataDefinitionAttachId;

    /**
     * 상품브로슈어첨부ID
     */
    @Size(max = 36)
    @Column(name = "product_brochure_attach_id", length = 36)
    private String productBrochureAttachId;

    /**
     * 테그명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "tag_name", length = 300, nullable = false)
    private String tagName;

    /**
     * 업데이트주기
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "update_cyclic", length = 100, nullable = false)
    private String updateCyclic;

    /**
     * 상품확장자코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "product_extension_code", nullable = false)
    private ProductExtensionCode productExtensionCode;

    /**
     * 상품용량
     */
    @NotNull
    @Column(name = "product_capacity", nullable = false)
    private Long productCapacity;

    /**
     * 상품라이선스
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "product_license", length = 300, nullable = false)
    private String productLicense;

    /**
     * 과금체계첨부ID
     */
    @Size(max = 36)
    @Column(name = "charging_system_attach_id", length = 36)
    private String chargingSystemAttachId;

    /**
     * 상품가격
     */
    @NotNull
    @Column(name = "product_price", nullable = false)
    private Long productPrice;

    /**
     * 과금체계코드
     */
    @Size(max = 20)
    @Column(name = "charging_system_code", length = 20)
    private String chargingSystemCode;

    /**
     * 구매제한업종여부
     */
    @Column(name = "purchase_restrict_business_yn")
    private Boolean purchaseRestrictBusinessYn;

    /**
     * 전송방식
     */
    @Size(max = 4000)
    @Column(name = "transfer_method", length = 4000)
    private String transferMethod;

    /**
     * 맞춤형콘텐츠문의ID
     */
    @Size(max = 36)
    @Column(name = "custom_contents_inquiry_id", length = 36)
    private String customContentsInquiryId;

    /**
     * 판매승인상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sale_approve_status_code", nullable = false)
    private SaleApproveStatusCode saleApproveStatusCode;

    /**
     * 구매구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "buy_type_code", nullable = false)
    private BuyTypeCode buyTypeCode;

    /**
     * 구독콘텐츠년간결제할인율
     */
    @Column(name = "subscribe_yearly_pay_discount_rate")
    private Long subscribeYearlyPayDiscountRate;

    /**
     * 정산방식코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "calculate_method_code", nullable = false)
    private CalculateMethodCode calculateMethodCode;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "companyMasters", "attachDocHists", "contentsCatalogMasters" }, allowSetters = true)
    private AttachDocMaster attachDocMaster;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsCatalogMasters", "calculateMasters", "calculateHists" }, allowSetters = true)
    private CalculateCharge calculateCharge;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "attachDocMaster", "userMasters", "contentsCatalogMasters", "affiliateIntroduces", "interfaceLists", "newsInfos" },
        allowSetters = true
    )
    private CompanyMaster companyMaster;

    @ManyToOne
    @JsonIgnoreProperties(value = { "userMaster", "contentsCatalogMasters", "customContentsInquiryHists" }, allowSetters = true)
    private CustomContentsInquiryMaster customContentsInquiryMaster;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "contentsCatalogMaster", "shoppingBasketMaster", "calculateHists", "contentsBuyHists", "contentsSubscribeHists" },
        allowSetters = true
    )
    private Set<ContentsBuyMaster> contentsBuyMasters ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster" }, allowSetters = true)
    private Set<ContentsCategoryType> contentsCategoryTypes ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster" }, allowSetters = true)
    private Set<ContentsRelation> contentsRelations ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster" }, allowSetters = true)
    private Set<ContentsDetailMaster> contentsDetailMasters ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "userMaster", "recommendContentsHists" }, allowSetters = true)
    private Set<RecommendContentsMaster> recommendContentsMasters ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster" }, allowSetters = true)
    private Set<ContentsSaleApproveHist> contentsSaleApproveHists ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "contentsBuyMasters", "shoppingBasketHists" }, allowSetters = true)
    private Set<ShoppingBasketMaster> shoppingBasketMasters ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "userMaster", "contentsInquiryHists" }, allowSetters = true)
    private Set<ContentsInquiryMaster> contentsInquiryMasters ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "userMaster", "contentsStatisticHists" }, allowSetters = true)
    private Set<ContentsStatisticMaster> contentsStatisticMasters ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "contentsSampleData" }, allowSetters = true)
    private Set<ContentsSampleColumn> contentsSampleColumns ;

    @OneToMany(mappedBy = "contentsCatalogMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsCatalogMaster" }, allowSetters = true)
    private Set<ContentsPurchaseRestrict> contentsPurchaseRestricts ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsCatalogMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public AttachDocMaster getAttachDocMaster() {
        return this.attachDocMaster;
    }

    public void setAttachDocMaster(AttachDocMaster attachDocMaster) {
        this.attachDocMaster = attachDocMaster;
    }

    public ContentsCatalogMaster attachDocMaster(AttachDocMaster attachDocMaster) {
        this.setAttachDocMaster(attachDocMaster);
        return this;
    }

    public CalculateCharge getCalculateCharge() {
        return this.calculateCharge;
    }

    public void setCalculateCharge(CalculateCharge calculateCharge) {
        this.calculateCharge = calculateCharge;
    }

    public ContentsCatalogMaster calculateCharge(CalculateCharge calculateCharge) {
        this.setCalculateCharge(calculateCharge);
        return this;
    }

    public CompanyMaster getCompanyMaster() {
        return this.companyMaster;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public ContentsCatalogMaster companyMaster(CompanyMaster companyMaster) {
        this.setCompanyMaster(companyMaster);
        return this;
    }

    public CustomContentsInquiryMaster getCustomContentsInquiryMaster() {
        return this.customContentsInquiryMaster;
    }

    public void setCustomContentsInquiryMaster(CustomContentsInquiryMaster customContentsInquiryMaster) {
        this.customContentsInquiryMaster = customContentsInquiryMaster;
    }

    public ContentsCatalogMaster customContentsInquiryMaster(CustomContentsInquiryMaster customContentsInquiryMaster) {
        this.setCustomContentsInquiryMaster(customContentsInquiryMaster);
        return this;
    }

    public Set<ContentsBuyMaster> getContentsBuyMasters() {
        return this.contentsBuyMasters;
    }

    public void setContentsBuyMasters(Set<ContentsBuyMaster> contentsBuyMasters) {
        if (this.contentsBuyMasters != null) {
            this.contentsBuyMasters.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsBuyMasters != null) {
            contentsBuyMasters.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsBuyMasters = contentsBuyMasters;
    }

    public ContentsCatalogMaster contentsBuyMasters(Set<ContentsBuyMaster> contentsBuyMasters) {
        this.setContentsBuyMasters(contentsBuyMasters);
        return this;
    }

    public ContentsCatalogMaster addContentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.contentsBuyMasters.add(contentsBuyMaster);
        contentsBuyMaster.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.contentsBuyMasters.remove(contentsBuyMaster);
        contentsBuyMaster.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsCategoryType> getContentsCategoryTypes() {
        return this.contentsCategoryTypes;
    }

    public void setContentsCategoryTypes(Set<ContentsCategoryType> contentsCategoryTypes) {
        if (this.contentsCategoryTypes != null) {
            this.contentsCategoryTypes.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsCategoryTypes != null) {
            contentsCategoryTypes.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsCategoryTypes = contentsCategoryTypes;
    }

    public ContentsCatalogMaster contentsCategoryTypes(Set<ContentsCategoryType> contentsCategoryTypes) {
        this.setContentsCategoryTypes(contentsCategoryTypes);
        return this;
    }

    public ContentsCatalogMaster addContentsCategoryType(ContentsCategoryType contentsCategoryType) {
        this.contentsCategoryTypes.add(contentsCategoryType);
        contentsCategoryType.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsCategoryType(ContentsCategoryType contentsCategoryType) {
        this.contentsCategoryTypes.remove(contentsCategoryType);
        contentsCategoryType.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsRelation> getContentsRelations() {
        return this.contentsRelations;
    }

    public void setContentsRelations(Set<ContentsRelation> contentsRelations) {
        if (this.contentsRelations != null) {
            this.contentsRelations.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsRelations != null) {
            contentsRelations.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsRelations = contentsRelations;
    }

    public ContentsCatalogMaster contentsRelations(Set<ContentsRelation> contentsRelations) {
        this.setContentsRelations(contentsRelations);
        return this;
    }

    public ContentsCatalogMaster addContentsRelation(ContentsRelation contentsRelation) {
        this.contentsRelations.add(contentsRelation);
        contentsRelation.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsRelation(ContentsRelation contentsRelation) {
        this.contentsRelations.remove(contentsRelation);
        contentsRelation.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsDetailMaster> getContentsDetailMasters() {
        return this.contentsDetailMasters;
    }

    public void setContentsDetailMasters(Set<ContentsDetailMaster> contentsDetailMasters) {
        if (this.contentsDetailMasters != null) {
            this.contentsDetailMasters.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsDetailMasters != null) {
            contentsDetailMasters.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsDetailMasters = contentsDetailMasters;
    }

    public ContentsCatalogMaster contentsDetailMasters(Set<ContentsDetailMaster> contentsDetailMasters) {
        this.setContentsDetailMasters(contentsDetailMasters);
        return this;
    }

    public ContentsCatalogMaster addContentsDetailMaster(ContentsDetailMaster contentsDetailMaster) {
        this.contentsDetailMasters.add(contentsDetailMaster);
        contentsDetailMaster.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsDetailMaster(ContentsDetailMaster contentsDetailMaster) {
        this.contentsDetailMasters.remove(contentsDetailMaster);
        contentsDetailMaster.setContentsCatalogMaster(null);
        return this;
    }

    public Set<RecommendContentsMaster> getRecommendContentsMasters() {
        return this.recommendContentsMasters;
    }

    public void setRecommendContentsMasters(Set<RecommendContentsMaster> recommendContentsMasters) {
        if (this.recommendContentsMasters != null) {
            this.recommendContentsMasters.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (recommendContentsMasters != null) {
            recommendContentsMasters.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.recommendContentsMasters = recommendContentsMasters;
    }

    public ContentsCatalogMaster recommendContentsMasters(Set<RecommendContentsMaster> recommendContentsMasters) {
        this.setRecommendContentsMasters(recommendContentsMasters);
        return this;
    }

    public ContentsCatalogMaster addRecommendContentsMaster(RecommendContentsMaster recommendContentsMaster) {
        this.recommendContentsMasters.add(recommendContentsMaster);
        recommendContentsMaster.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeRecommendContentsMaster(RecommendContentsMaster recommendContentsMaster) {
        this.recommendContentsMasters.remove(recommendContentsMaster);
        recommendContentsMaster.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsSaleApproveHist> getContentsSaleApproveHists() {
        return this.contentsSaleApproveHists;
    }

    public void setContentsSaleApproveHists(Set<ContentsSaleApproveHist> contentsSaleApproveHists) {
        if (this.contentsSaleApproveHists != null) {
            this.contentsSaleApproveHists.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsSaleApproveHists != null) {
            contentsSaleApproveHists.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsSaleApproveHists = contentsSaleApproveHists;
    }

    public ContentsCatalogMaster contentsSaleApproveHists(Set<ContentsSaleApproveHist> contentsSaleApproveHists) {
        this.setContentsSaleApproveHists(contentsSaleApproveHists);
        return this;
    }

    public ContentsCatalogMaster addContentsSaleApproveHist(ContentsSaleApproveHist contentsSaleApproveHist) {
        this.contentsSaleApproveHists.add(contentsSaleApproveHist);
        contentsSaleApproveHist.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsSaleApproveHist(ContentsSaleApproveHist contentsSaleApproveHist) {
        this.contentsSaleApproveHists.remove(contentsSaleApproveHist);
        contentsSaleApproveHist.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ShoppingBasketMaster> getShoppingBasketMasters() {
        return this.shoppingBasketMasters;
    }

    public void setShoppingBasketMasters(Set<ShoppingBasketMaster> shoppingBasketMasters) {
        if (this.shoppingBasketMasters != null) {
            this.shoppingBasketMasters.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (shoppingBasketMasters != null) {
            shoppingBasketMasters.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.shoppingBasketMasters = shoppingBasketMasters;
    }

    public ContentsCatalogMaster shoppingBasketMasters(Set<ShoppingBasketMaster> shoppingBasketMasters) {
        this.setShoppingBasketMasters(shoppingBasketMasters);
        return this;
    }

    public ContentsCatalogMaster addShoppingBasketMaster(ShoppingBasketMaster shoppingBasketMaster) {
        this.shoppingBasketMasters.add(shoppingBasketMaster);
        shoppingBasketMaster.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeShoppingBasketMaster(ShoppingBasketMaster shoppingBasketMaster) {
        this.shoppingBasketMasters.remove(shoppingBasketMaster);
        shoppingBasketMaster.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsInquiryMaster> getContentsInquiryMasters() {
        return this.contentsInquiryMasters;
    }

    public void setContentsInquiryMasters(Set<ContentsInquiryMaster> contentsInquiryMasters) {
        if (this.contentsInquiryMasters != null) {
            this.contentsInquiryMasters.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsInquiryMasters != null) {
            contentsInquiryMasters.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsInquiryMasters = contentsInquiryMasters;
    }

    public ContentsCatalogMaster contentsInquiryMasters(Set<ContentsInquiryMaster> contentsInquiryMasters) {
        this.setContentsInquiryMasters(contentsInquiryMasters);
        return this;
    }

    public ContentsCatalogMaster addContentsInquiryMaster(ContentsInquiryMaster contentsInquiryMaster) {
        this.contentsInquiryMasters.add(contentsInquiryMaster);
        contentsInquiryMaster.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsInquiryMaster(ContentsInquiryMaster contentsInquiryMaster) {
        this.contentsInquiryMasters.remove(contentsInquiryMaster);
        contentsInquiryMaster.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsStatisticMaster> getContentsStatisticMasters() {
        return this.contentsStatisticMasters;
    }

    public void setContentsStatisticMasters(Set<ContentsStatisticMaster> contentsStatisticMasters) {
        if (this.contentsStatisticMasters != null) {
            this.contentsStatisticMasters.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsStatisticMasters != null) {
            contentsStatisticMasters.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsStatisticMasters = contentsStatisticMasters;
    }

    public ContentsCatalogMaster contentsStatisticMasters(Set<ContentsStatisticMaster> contentsStatisticMasters) {
        this.setContentsStatisticMasters(contentsStatisticMasters);
        return this;
    }

    public ContentsCatalogMaster addContentsStatisticMaster(ContentsStatisticMaster contentsStatisticMaster) {
        this.contentsStatisticMasters.add(contentsStatisticMaster);
        contentsStatisticMaster.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsStatisticMaster(ContentsStatisticMaster contentsStatisticMaster) {
        this.contentsStatisticMasters.remove(contentsStatisticMaster);
        contentsStatisticMaster.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsSampleColumn> getContentsSampleColumns() {
        return this.contentsSampleColumns;
    }

    public void setContentsSampleColumns(Set<ContentsSampleColumn> contentsSampleColumns) {
        if (this.contentsSampleColumns != null) {
            this.contentsSampleColumns.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsSampleColumns != null) {
            contentsSampleColumns.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsSampleColumns = contentsSampleColumns;
    }

    public ContentsCatalogMaster contentsSampleColumns(Set<ContentsSampleColumn> contentsSampleColumns) {
        this.setContentsSampleColumns(contentsSampleColumns);
        return this;
    }

    public ContentsCatalogMaster addContentsSampleColumn(ContentsSampleColumn contentsSampleColumn) {
        this.contentsSampleColumns.add(contentsSampleColumn);
        contentsSampleColumn.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsSampleColumn(ContentsSampleColumn contentsSampleColumn) {
        this.contentsSampleColumns.remove(contentsSampleColumn);
        contentsSampleColumn.setContentsCatalogMaster(null);
        return this;
    }

    public Set<ContentsPurchaseRestrict> getContentsPurchaseRestricts() {
        return this.contentsPurchaseRestricts;
    }

    public void setContentsPurchaseRestricts(Set<ContentsPurchaseRestrict> contentsPurchaseRestricts) {
        if (this.contentsPurchaseRestricts != null) {
            this.contentsPurchaseRestricts.forEach(i -> i.setContentsCatalogMaster(null));
        }
        if (contentsPurchaseRestricts != null) {
            contentsPurchaseRestricts.forEach(i -> i.setContentsCatalogMaster(this));
        }
        this.contentsPurchaseRestricts = contentsPurchaseRestricts;
    }

    public ContentsCatalogMaster contentsPurchaseRestricts(Set<ContentsPurchaseRestrict> contentsPurchaseRestricts) {
        this.setContentsPurchaseRestricts(contentsPurchaseRestricts);
        return this;
    }

    public ContentsCatalogMaster addContentsPurchaseRestrict(ContentsPurchaseRestrict contentsPurchaseRestrict) {
        this.contentsPurchaseRestricts.add(contentsPurchaseRestrict);
        contentsPurchaseRestrict.setContentsCatalogMaster(this);
        return this;
    }

    public ContentsCatalogMaster removeContentsPurchaseRestrict(ContentsPurchaseRestrict contentsPurchaseRestrict) {
        this.contentsPurchaseRestricts.remove(contentsPurchaseRestrict);
        contentsPurchaseRestrict.setContentsCatalogMaster(null);
        return this;
    }

    }
