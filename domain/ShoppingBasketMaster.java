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
 * 장바구니원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_shopping_basket_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShoppingBasketMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 장바구니ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "shopping_basket_id", length = 36, nullable = false, unique = true)
    private String shoppingBasketId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

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

    @OneToMany(mappedBy = "shoppingBasketMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "contentsCatalogMaster", "shoppingBasketMaster", "calculateHists", "contentsBuyHists", "contentsSubscribeHists" },
        allowSetters = true
    )
    private Set<ContentsBuyMaster> contentsBuyMasters ;

    @OneToMany(mappedBy = "shoppingBasketMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "shoppingBasketMaster" }, allowSetters = true)
    private Set<ShoppingBasketHist> shoppingBasketHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ShoppingBasketMaster setIsPersisted() {
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

    public ShoppingBasketMaster contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    public Set<ContentsBuyMaster> getContentsBuyMasters() {
        return this.contentsBuyMasters;
    }

    public void setContentsBuyMasters(Set<ContentsBuyMaster> contentsBuyMasters) {
        if (this.contentsBuyMasters != null) {
            this.contentsBuyMasters.forEach(i -> i.setShoppingBasketMaster(null));
        }
        if (contentsBuyMasters != null) {
            contentsBuyMasters.forEach(i -> i.setShoppingBasketMaster(this));
        }
        this.contentsBuyMasters = contentsBuyMasters;
    }

    public ShoppingBasketMaster contentsBuyMasters(Set<ContentsBuyMaster> contentsBuyMasters) {
        this.setContentsBuyMasters(contentsBuyMasters);
        return this;
    }

    public ShoppingBasketMaster addContentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.contentsBuyMasters.add(contentsBuyMaster);
        contentsBuyMaster.setShoppingBasketMaster(this);
        return this;
    }

    public ShoppingBasketMaster removeContentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.contentsBuyMasters.remove(contentsBuyMaster);
        contentsBuyMaster.setShoppingBasketMaster(null);
        return this;
    }

    public Set<ShoppingBasketHist> getShoppingBasketHists() {
        return this.shoppingBasketHists;
    }

    public void setShoppingBasketHists(Set<ShoppingBasketHist> shoppingBasketHists) {
        if (this.shoppingBasketHists != null) {
            this.shoppingBasketHists.forEach(i -> i.setShoppingBasketMaster(null));
        }
        if (shoppingBasketHists != null) {
            shoppingBasketHists.forEach(i -> i.setShoppingBasketMaster(this));
        }
        this.shoppingBasketHists = shoppingBasketHists;
    }

    public ShoppingBasketMaster shoppingBasketHists(Set<ShoppingBasketHist> shoppingBasketHists) {
        this.setShoppingBasketHists(shoppingBasketHists);
        return this;
    }

    public ShoppingBasketMaster addShoppingBasketHist(ShoppingBasketHist shoppingBasketHist) {
        this.shoppingBasketHists.add(shoppingBasketHist);
        shoppingBasketHist.setShoppingBasketMaster(this);
        return this;
    }

    public ShoppingBasketMaster removeShoppingBasketHist(ShoppingBasketHist shoppingBasketHist) {
        this.shoppingBasketHists.remove(shoppingBasketHist);
        shoppingBasketHist.setShoppingBasketMaster(null);
        return this;
    }

    }
