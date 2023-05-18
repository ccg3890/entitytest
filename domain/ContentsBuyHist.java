package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
 * 콘텐츠구매이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_buy_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsBuyHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠구매UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_buy_uuid", length = 36, nullable = false, unique = true)
    private String contentsBuyUuid;

    /**
     * 콘텐츠구매ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_buy_id", length = 36, nullable = false)
    private String contentsBuyId;

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
     * 구매승인사유설명
     */
    @Size(max = 4000)
    @Column(name = "buy_approve_reason_explain", length = 4000)
    private String buyApproveReasonExplain;

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
        value = { "contentsCatalogMaster", "shoppingBasketMaster", "calculateHists", "contentsBuyHists", "contentsSubscribeHists" },
        allowSetters = true
    )
    private ContentsBuyMaster contentsBuyMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsBuyHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public ContentsBuyMaster getContentsBuyMaster() {
        return this.contentsBuyMaster;
    }

    public void setContentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.contentsBuyMaster = contentsBuyMaster;
    }

    public ContentsBuyHist contentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.setContentsBuyMaster(contentsBuyMaster);
        return this;
    }

    }
