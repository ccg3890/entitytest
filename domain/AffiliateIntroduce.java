package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.IntroduceTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 그룹계열사소개정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_affiliate_introduce")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AffiliateIntroduce extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 그룹계열사ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "affiliate_id", length = 36, nullable = false, unique = true)
    private String affiliateId;

    /**
     * 기업그룹사ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "company_id", length = 36, nullable = false)
    private String companyId;

    /**
     * 소개구분코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "introduce_type_code")
    private IntroduceTypeCode introduceTypeCode;

    /**
     * 소개구분값순번
     */
    @NotNull
    @Column(name = "introduce_type_value_turn", nullable = false)
    private Long introduceTypeValueTurn;

    /**
     * 소개구분값
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "introduce_type_value", length = 1000, nullable = false)
    private String introduceTypeValue;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = { "attachDocMaster", "userMasters", "contentsCatalogMasters", "affiliateIntroduces", "interfaceLists", "newsInfos" },
        allowSetters = true
    )
    private CompanyMaster companyMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public AffiliateIntroduce setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public CompanyMaster getCompanyMaster() {
        return this.companyMaster;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public AffiliateIntroduce companyMaster(CompanyMaster companyMaster) {
        this.setCompanyMaster(companyMaster);
        return this;
    }

    }
