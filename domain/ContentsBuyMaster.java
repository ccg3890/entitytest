package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.BuyApproveStatusCode;
import net.lotte.marketplace.domain.enumeration.BuyTypeCode;
import net.lotte.marketplace.domain.enumeration.DiscountReasonCode;
import net.lotte.marketplace.domain.enumeration.PaymentCycleCode;
import net.lotte.marketplace.domain.enumeration.PaymentMethodCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠구매원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_buy_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsBuyMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠구매ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_buy_id", length = 36, nullable = false, unique = true)
    private String contentsBuyId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

    /**
     * 장바구니ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "shopping_basket_id", length = 36, nullable = false)
    private String shoppingBasketId;

    /**
     * 구매구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "buy_type_code", nullable = false)
    private BuyTypeCode buyTypeCode;

    /**
     * 구매승인상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "buy_approve_status_code", nullable = false)
    private BuyApproveStatusCode buyApproveStatusCode;

    /**
     * 결제방식코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method_code", nullable = false)
    private PaymentMethodCode paymentMethodCode;

    /**
     * 결제주기코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_cycle_code", nullable = false)
    private PaymentCycleCode paymentCycleCode;

    /**
     * 결제가격
     */
    @NotNull
    @Column(name = "payment_price", nullable = false)
    private Long paymentPrice;

    /**
     * 할인금액
     */
    @Column(name = "discount_amount")
    private Long discountAmount;

    /**
     * 할인사유코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_reason_code")
    private DiscountReasonCode discountReasonCode;

    /**
     * 할인사유설명
     */
    @Size(max = 4000)
    @Column(name = "discount_reason_explain", length = 4000)
    private String discountReasonExplain;

    /**
     * 구독기간
     */
    @Column(name = "subscribe_period")
    private Long subscribePeriod;

    /**
     * 구독자동연장여부
     */
    @NotNull
    @Column(name = "subscribe_auto_extension_yn", nullable = false)
    private Boolean subscribeAutoExtensionYn;

    
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

    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "contentsBuyMasters", "shoppingBasketHists" }, allowSetters = true)
    private ShoppingBasketMaster shoppingBasketMaster;

    @OneToMany(mappedBy = "contentsBuyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "calculateCharge", "contentsBuyMaster", "calculateMaster" }, allowSetters = true)
    private Set<CalculateHist> calculateHists ;

    @OneToMany(mappedBy = "contentsBuyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsBuyMaster" }, allowSetters = true)
    private Set<ContentsBuyHist> contentsBuyHists ;

    @OneToMany(mappedBy = "contentsBuyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "batchExecuteHist", "contentsBuyMaster", "userMaster" }, allowSetters = true)
    private Set<ContentsSubscribeHist> contentsSubscribeHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsBuyMaster setIsPersisted() {
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

    public ContentsBuyMaster contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    public ShoppingBasketMaster getShoppingBasketMaster() {
        return this.shoppingBasketMaster;
    }

    public void setShoppingBasketMaster(ShoppingBasketMaster shoppingBasketMaster) {
        this.shoppingBasketMaster = shoppingBasketMaster;
    }

    public ContentsBuyMaster shoppingBasketMaster(ShoppingBasketMaster shoppingBasketMaster) {
        this.setShoppingBasketMaster(shoppingBasketMaster);
        return this;
    }

    public Set<CalculateHist> getCalculateHists() {
        return this.calculateHists;
    }

    public void setCalculateHists(Set<CalculateHist> calculateHists) {
        if (this.calculateHists != null) {
            this.calculateHists.forEach(i -> i.setContentsBuyMaster(null));
        }
        if (calculateHists != null) {
            calculateHists.forEach(i -> i.setContentsBuyMaster(this));
        }
        this.calculateHists = calculateHists;
    }

    public ContentsBuyMaster calculateHists(Set<CalculateHist> calculateHists) {
        this.setCalculateHists(calculateHists);
        return this;
    }

    public ContentsBuyMaster addCalculateHist(CalculateHist calculateHist) {
        this.calculateHists.add(calculateHist);
        calculateHist.setContentsBuyMaster(this);
        return this;
    }

    public ContentsBuyMaster removeCalculateHist(CalculateHist calculateHist) {
        this.calculateHists.remove(calculateHist);
        calculateHist.setContentsBuyMaster(null);
        return this;
    }

    public Set<ContentsBuyHist> getContentsBuyHists() {
        return this.contentsBuyHists;
    }

    public void setContentsBuyHists(Set<ContentsBuyHist> contentsBuyHists) {
        if (this.contentsBuyHists != null) {
            this.contentsBuyHists.forEach(i -> i.setContentsBuyMaster(null));
        }
        if (contentsBuyHists != null) {
            contentsBuyHists.forEach(i -> i.setContentsBuyMaster(this));
        }
        this.contentsBuyHists = contentsBuyHists;
    }

    public ContentsBuyMaster contentsBuyHists(Set<ContentsBuyHist> contentsBuyHists) {
        this.setContentsBuyHists(contentsBuyHists);
        return this;
    }

    public ContentsBuyMaster addContentsBuyHist(ContentsBuyHist contentsBuyHist) {
        this.contentsBuyHists.add(contentsBuyHist);
        contentsBuyHist.setContentsBuyMaster(this);
        return this;
    }

    public ContentsBuyMaster removeContentsBuyHist(ContentsBuyHist contentsBuyHist) {
        this.contentsBuyHists.remove(contentsBuyHist);
        contentsBuyHist.setContentsBuyMaster(null);
        return this;
    }

    public Set<ContentsSubscribeHist> getContentsSubscribeHists() {
        return this.contentsSubscribeHists;
    }

    public void setContentsSubscribeHists(Set<ContentsSubscribeHist> contentsSubscribeHists) {
        if (this.contentsSubscribeHists != null) {
            this.contentsSubscribeHists.forEach(i -> i.setContentsBuyMaster(null));
        }
        if (contentsSubscribeHists != null) {
            contentsSubscribeHists.forEach(i -> i.setContentsBuyMaster(this));
        }
        this.contentsSubscribeHists = contentsSubscribeHists;
    }

    public ContentsBuyMaster contentsSubscribeHists(Set<ContentsSubscribeHist> contentsSubscribeHists) {
        this.setContentsSubscribeHists(contentsSubscribeHists);
        return this;
    }

    public ContentsBuyMaster addContentsSubscribeHist(ContentsSubscribeHist contentsSubscribeHist) {
        this.contentsSubscribeHists.add(contentsSubscribeHist);
        contentsSubscribeHist.setContentsBuyMaster(this);
        return this;
    }

    public ContentsBuyMaster removeContentsSubscribeHist(ContentsSubscribeHist contentsSubscribeHist) {
        this.contentsSubscribeHists.remove(contentsSubscribeHist);
        contentsSubscribeHist.setContentsBuyMaster(null);
        return this;
    }

    }
