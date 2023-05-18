package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.StatisticProgressStatusCode;
import net.lotte.marketplace.domain.enumeration.StatisticTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠통계원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_statistic_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsStatisticMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠통계ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_statistic_id", length = 36, nullable = false, unique = true)
    private String contentsStatisticId;

    /**
     * 통계구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statistic_type_code", nullable = false)
    private StatisticTypeCode statisticTypeCode;

    /**
     * 통계회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "statistic_user_id", length = 20, nullable = false)
    private String statisticUserId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

    /**
     * 통계진행상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statistic_progress_status_code", nullable = false)
    private StatisticProgressStatusCode statisticProgressStatusCode;

    /**
     * 참조내용
     */
    @Size(max = 4000)
    @Column(name = "reference_content", length = 4000)
    private String referenceContent;

    
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
    @JsonIgnoreProperties(
        value = {
            "userAgreements",
            "companyMaster",
            "userBusiType",
            "userDormantHists",
            "userQuitHists",
            "userAgreements",
            "userLockHists",
            "userChangeHists",
            "userVerifyHists",
            "userApproveHists",
            "userLoginHists",
            "batchPgmLists",
            "recommendContentsMasters",
            "recommendContentsHists",
            "sendMailMasters",
            "sendMailHists",
            "pgmLists",
            "contentsSubscribeHists",
            "contentsRoleRequests",
            "contentsRoleApproveHists",
            "qnaMasters",
            "contentsInquiryMasters",
            "customContentsInquiryMasters",
            "contentsStatisticMasters",
            "userAttentionContents",
            "sendInformTalkHists",
        },
        allowSetters = true
    )
    private UserMaster userMaster;

    @OneToMany(mappedBy = "contentsStatisticMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsStatisticMaster" }, allowSetters = true)
    private Set<ContentsStatisticHist> contentsStatisticHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsStatisticMaster setIsPersisted() {
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

    public ContentsStatisticMaster contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public ContentsStatisticMaster userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<ContentsStatisticHist> getContentsStatisticHists() {
        return this.contentsStatisticHists;
    }

    public void setContentsStatisticHists(Set<ContentsStatisticHist> contentsStatisticHists) {
        if (this.contentsStatisticHists != null) {
            this.contentsStatisticHists.forEach(i -> i.setContentsStatisticMaster(null));
        }
        if (contentsStatisticHists != null) {
            contentsStatisticHists.forEach(i -> i.setContentsStatisticMaster(this));
        }
        this.contentsStatisticHists = contentsStatisticHists;
    }

    public ContentsStatisticMaster contentsStatisticHists(Set<ContentsStatisticHist> contentsStatisticHists) {
        this.setContentsStatisticHists(contentsStatisticHists);
        return this;
    }

    public ContentsStatisticMaster addContentsStatisticHist(ContentsStatisticHist contentsStatisticHist) {
        this.contentsStatisticHists.add(contentsStatisticHist);
        contentsStatisticHist.setContentsStatisticMaster(this);
        return this;
    }

    public ContentsStatisticMaster removeContentsStatisticHist(ContentsStatisticHist contentsStatisticHist) {
        this.contentsStatisticHists.remove(contentsStatisticHist);
        contentsStatisticHist.setContentsStatisticMaster(null);
        return this;
    }

    }
