package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ChargeClassCode;
import net.lotte.marketplace.domain.enumeration.RoundCutTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 정산수수료관리
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_calculate_charge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CalculateCharge extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 수수료율ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "charge_rate_id", length = 36, nullable = false, unique = true)
    private String chargeRateId;

    /**
     * 수수료유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "charge_class_code", nullable = false)
    private ChargeClassCode chargeClassCode;

    /**
     * 수수료율
     */
    @NotNull
    @Column(name = "charge_rate", nullable = false)
    private Long chargeRate;

    /**
     * 수수료율명
     */
    @Size(max = 300)
    @Column(name = "charge_rate_name", length = 300)
    private String chargeRateName;

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

    /**
     * 절사구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "round_cut_type_code", nullable = false)
    private RoundCutTypeCode roundCutTypeCode;

    
    @OneToMany(mappedBy = "calculateCharge")
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

    @OneToMany(mappedBy = "calculateCharge")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "calculateCharge", "contentsBuyId", "saleUserId", "calculateHists" }, allowSetters = true)
    private Set<CalculateMaster> calculateMasters ;

    @OneToMany(mappedBy = "calculateCharge")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "calculateCharge", "contentsBuyMaster", "calculateMaster" }, allowSetters = true)
    private Set<CalculateHist> calculateHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public CalculateCharge setIsPersisted() {
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
            this.contentsCatalogMasters.forEach(i -> i.setCalculateCharge(null));
        }
        if (contentsCatalogMasters != null) {
            contentsCatalogMasters.forEach(i -> i.setCalculateCharge(this));
        }
        this.contentsCatalogMasters = contentsCatalogMasters;
    }

    public CalculateCharge contentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        this.setContentsCatalogMasters(contentsCatalogMasters);
        return this;
    }

    public CalculateCharge addContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.add(contentsCatalogMaster);
        contentsCatalogMaster.setCalculateCharge(this);
        return this;
    }

    public CalculateCharge removeContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.remove(contentsCatalogMaster);
        contentsCatalogMaster.setCalculateCharge(null);
        return this;
    }

    public Set<CalculateMaster> getCalculateMasters() {
        return this.calculateMasters;
    }

    public void setCalculateMasters(Set<CalculateMaster> calculateMasters) {
        if (this.calculateMasters != null) {
            this.calculateMasters.forEach(i -> i.setCalculateCharge(null));
        }
        if (calculateMasters != null) {
            calculateMasters.forEach(i -> i.setCalculateCharge(this));
        }
        this.calculateMasters = calculateMasters;
    }

    public CalculateCharge calculateMasters(Set<CalculateMaster> calculateMasters) {
        this.setCalculateMasters(calculateMasters);
        return this;
    }

    public CalculateCharge addCalculateMaster(CalculateMaster calculateMaster) {
        this.calculateMasters.add(calculateMaster);
        calculateMaster.setCalculateCharge(this);
        return this;
    }

    public CalculateCharge removeCalculateMaster(CalculateMaster calculateMaster) {
        this.calculateMasters.remove(calculateMaster);
        calculateMaster.setCalculateCharge(null);
        return this;
    }

    public Set<CalculateHist> getCalculateHists() {
        return this.calculateHists;
    }

    public void setCalculateHists(Set<CalculateHist> calculateHists) {
        if (this.calculateHists != null) {
            this.calculateHists.forEach(i -> i.setCalculateCharge(null));
        }
        if (calculateHists != null) {
            calculateHists.forEach(i -> i.setCalculateCharge(this));
        }
        this.calculateHists = calculateHists;
    }

    public CalculateCharge calculateHists(Set<CalculateHist> calculateHists) {
        this.setCalculateHists(calculateHists);
        return this;
    }

    public CalculateCharge addCalculateHist(CalculateHist calculateHist) {
        this.calculateHists.add(calculateHist);
        calculateHist.setCalculateCharge(this);
        return this;
    }

    public CalculateCharge removeCalculateHist(CalculateHist calculateHist) {
        this.calculateHists.remove(calculateHist);
        calculateHist.setCalculateCharge(null);
        return this;
    }

    }
