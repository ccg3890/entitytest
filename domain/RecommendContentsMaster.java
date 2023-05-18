package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.RecommendContentsStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 추천콘텐츠원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_recommend_contents_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RecommendContentsMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 추천콘텐츠ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_contents_id", length = 36, nullable = false, unique = true)
    private String recommendContentsId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

    /**
     * 추천콘텐츠상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "recommend_contents_status_code", nullable = false)
    private RecommendContentsStatusCode recommendContentsStatusCode;

    /**
     * 추천인ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "recommender_id", length = 20, nullable = false)
    private String recommenderId;

    /**
     * 피추천인ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "recommendee_id", length = 20, nullable = false)
    private String recommendeeId;

    /**
     * 추천글
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "recommend_title", length = 300, nullable = false)
    private String recommendTitle;

    /**
     * 추천설명
     */
    @Size(max = 4000)
    @Column(name = "recommend_explain", length = 4000)
    private String recommendExplain;

    
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

    @OneToMany(mappedBy = "recommendContentsMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "recommendContentsMaster", "userMaster" }, allowSetters = true)
    private Set<RecommendContentsHist> recommendContentsHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public RecommendContentsMaster setIsPersisted() {
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

    public RecommendContentsMaster contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public RecommendContentsMaster userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<RecommendContentsHist> getRecommendContentsHists() {
        return this.recommendContentsHists;
    }

    public void setRecommendContentsHists(Set<RecommendContentsHist> recommendContentsHists) {
        if (this.recommendContentsHists != null) {
            this.recommendContentsHists.forEach(i -> i.setRecommendContentsMaster(null));
        }
        if (recommendContentsHists != null) {
            recommendContentsHists.forEach(i -> i.setRecommendContentsMaster(this));
        }
        this.recommendContentsHists = recommendContentsHists;
    }

    public RecommendContentsMaster recommendContentsHists(Set<RecommendContentsHist> recommendContentsHists) {
        this.setRecommendContentsHists(recommendContentsHists);
        return this;
    }

    public RecommendContentsMaster addRecommendContentsHist(RecommendContentsHist recommendContentsHist) {
        this.recommendContentsHists.add(recommendContentsHist);
        recommendContentsHist.setRecommendContentsMaster(this);
        return this;
    }

    public RecommendContentsMaster removeRecommendContentsHist(RecommendContentsHist recommendContentsHist) {
        this.recommendContentsHists.remove(recommendContentsHist);
        recommendContentsHist.setRecommendContentsMaster(null);
        return this;
    }

    }
