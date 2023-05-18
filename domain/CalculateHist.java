package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.CalculateApproveStatusCode;
import net.lotte.marketplace.domain.enumeration.CalculateMethodCode;
import net.lotte.marketplace.domain.enumeration.RefundStatusCode;
import net.lotte.marketplace.domain.enumeration.RefundTargetReasonCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 정산내역이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_calculate_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CalculateHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 정산UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "calculate_uuid", length = 36, nullable = false, unique = true)
    private String calculateUuid;

    /**
     * 정산ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "calculate_id", length = 36, nullable = false)
    private String calculateId;

    /**
     * 판매자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "sale_user_id", length = 20, nullable = false)
    private String saleUserId;

    /**
     * 수수료율ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "charge_rate_id", length = 36, nullable = false)
    private String chargeRateId;

    /**
     * 판매금액
     */
    @NotNull
    @Column(name = "selling_price", nullable = false)
    private Long sellingPrice;

    /**
     * 수수료금액
     */
    @NotNull
    @Column(name = "charge_amount", nullable = false)
    private Long chargeAmount;

    /**
     * 절사수수료금액
     */
    @NotNull
    @Column(name = "round_cut_charge_amount", nullable = false)
    private Long roundCutChargeAmount;

    /**
     * 정산금액
     */
    @NotNull
    @Column(name = "calculate_amount", nullable = false)
    private Long calculateAmount;

    /**
     * 정산방식코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "calculate_method_code", nullable = false)
    private CalculateMethodCode calculateMethodCode;

    /**
     * 정산승인상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "calculate_approve_status_code", nullable = false)
    private CalculateApproveStatusCode calculateApproveStatusCode;

    /**
     * 정산승인사유
     */
    @Size(max = 4000)
    @Column(name = "calculate_approve_reason", length = 4000)
    private String calculateApproveReason;

    /**
     * 환급대상여부
     */
    @Column(name = "refund_target_yn")
    private Boolean refundTargetYn;

    /**
     * 환급대상사유코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "refund_target_reason_code")
    private RefundTargetReasonCode refundTargetReasonCode;

    /**
     * 환급대상사유설명
     */
    @Size(max = 4000)
    @Column(name = "refund_target_reason_explain", length = 4000)
    private String refundTargetReasonExplain;

    /**
     * 환급상태코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "refund_status_code")
    private RefundStatusCode refundStatusCode;

    /**
     * 환급상태사유
     */
    @Size(max = 4000)
    @Column(name = "refund_status_reason", length = 4000)
    private String refundStatusReason;

    /**
     * 환급금액
     */
    @Column(name = "refund_amount")
    private Long refundAmount;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsCatalogMasters", "calculateMasters", "calculateHists" }, allowSetters = true)
    private CalculateCharge calculateCharge;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "contentsCatalogMaster", "shoppingBasketMaster", "calculateHists", "contentsBuyHists", "contentsSubscribeHists" },
        allowSetters = true
    )
    private ContentsBuyMaster contentsBuyMaster;

    @ManyToOne
    @JsonIgnoreProperties(value = { "calculateCharge", "contentsBuyId", "saleUserId", "calculateHists" }, allowSetters = true)
    private CalculateMaster calculateMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public CalculateHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public CalculateCharge getCalculateCharge() {
        return this.calculateCharge;
    }

    public void setCalculateCharge(CalculateCharge calculateCharge) {
        this.calculateCharge = calculateCharge;
    }

    public CalculateHist calculateCharge(CalculateCharge calculateCharge) {
        this.setCalculateCharge(calculateCharge);
        return this;
    }

    public ContentsBuyMaster getContentsBuyMaster() {
        return this.contentsBuyMaster;
    }

    public void setContentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.contentsBuyMaster = contentsBuyMaster;
    }

    public CalculateHist contentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.setContentsBuyMaster(contentsBuyMaster);
        return this;
    }

    public CalculateMaster getCalculateMaster() {
        return this.calculateMaster;
    }

    public void setCalculateMaster(CalculateMaster calculateMaster) {
        this.calculateMaster = calculateMaster;
    }

    public CalculateHist calculateMaster(CalculateMaster calculateMaster) {
        this.setCalculateMaster(calculateMaster);
        return this;
    }

    }
