package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ProgressStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 맞춤형콘텐츠문의원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_custom_contents_inquiry_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomContentsInquiryMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 맞춤형콘텐츠문의ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "custom_contents_inquiry_id", length = 36, nullable = false, unique = true)
    private String customContentsInquiryId;

    /**
     * 문의제목
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "qna_title", length = 1000, nullable = false)
    private String qnaTitle;

    /**
     * 문의내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "qna_content", nullable = false)
    private String qnaContent;

    /**
     * 판매자ID
     */
    @Size(max = 20)
    @Column(name = "sale_user_id", length = 20)
    private String saleUserId;

    /**
     * 진행상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status_code", nullable = false)
    private ProgressStatusCode progressStatusCode;

    /**
     * 판매종료예정일자
     */
    @NotNull
    @Column(name = "sale_end_expect_date", nullable = false)
    private LocalDate saleEndExpectDate;

    /**
     * 판매종료실제일자
     */
    @Column(name = "sale_end_actual_date")
    private LocalDate saleEndActualDate;

    /**
     * 구매만족도점수
     */
    @Column(name = "purchase_satisfaction_score")
    private Long purchaseSatisfactionScore;

    
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

    @OneToMany(mappedBy = "customContentsInquiryMaster")
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

    @OneToMany(mappedBy = "customContentsInquiryMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "customContentsInquiryMaster" }, allowSetters = true)
    private Set<CustomContentsInquiryHist> customContentsInquiryHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public CustomContentsInquiryMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public CustomContentsInquiryMaster userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<ContentsCatalogMaster> getContentsCatalogMasters() {
        return this.contentsCatalogMasters;
    }

    public void setContentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        if (this.contentsCatalogMasters != null) {
            this.contentsCatalogMasters.forEach(i -> i.setCustomContentsInquiryMaster(null));
        }
        if (contentsCatalogMasters != null) {
            contentsCatalogMasters.forEach(i -> i.setCustomContentsInquiryMaster(this));
        }
        this.contentsCatalogMasters = contentsCatalogMasters;
    }

    public CustomContentsInquiryMaster contentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        this.setContentsCatalogMasters(contentsCatalogMasters);
        return this;
    }

    public CustomContentsInquiryMaster addContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.add(contentsCatalogMaster);
        contentsCatalogMaster.setCustomContentsInquiryMaster(this);
        return this;
    }

    public CustomContentsInquiryMaster removeContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.remove(contentsCatalogMaster);
        contentsCatalogMaster.setCustomContentsInquiryMaster(null);
        return this;
    }

    public Set<CustomContentsInquiryHist> getCustomContentsInquiryHists() {
        return this.customContentsInquiryHists;
    }

    public void setCustomContentsInquiryHists(Set<CustomContentsInquiryHist> customContentsInquiryHists) {
        if (this.customContentsInquiryHists != null) {
            this.customContentsInquiryHists.forEach(i -> i.setCustomContentsInquiryMaster(null));
        }
        if (customContentsInquiryHists != null) {
            customContentsInquiryHists.forEach(i -> i.setCustomContentsInquiryMaster(this));
        }
        this.customContentsInquiryHists = customContentsInquiryHists;
    }

    public CustomContentsInquiryMaster customContentsInquiryHists(Set<CustomContentsInquiryHist> customContentsInquiryHists) {
        this.setCustomContentsInquiryHists(customContentsInquiryHists);
        return this;
    }

    public CustomContentsInquiryMaster addCustomContentsInquiryHist(CustomContentsInquiryHist customContentsInquiryHist) {
        this.customContentsInquiryHists.add(customContentsInquiryHist);
        customContentsInquiryHist.setCustomContentsInquiryMaster(this);
        return this;
    }

    public CustomContentsInquiryMaster removeCustomContentsInquiryHist(CustomContentsInquiryHist customContentsInquiryHist) {
        this.customContentsInquiryHists.remove(customContentsInquiryHist);
        customContentsInquiryHist.setCustomContentsInquiryMaster(null);
        return this;
    }

    }
