package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ReplyStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠문의원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_inquiry_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsInquiryMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠문의ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_inquiry_id", length = 36, nullable = false, unique = true)
    private String contentsInquiryId;

    /**
     * 콘텐츠카탈로그ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_catalog_id", length = 36, nullable = false)
    private String contentsCatalogId;

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
     * 답변자ID
     */
    @Size(max = 20)
    @Column(name = "reply_user_id", length = 20)
    private String replyUserId;

    /**
     * 답변상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reply_status_code", nullable = false)
    private ReplyStatusCode replyStatusCode;

    /**
     * 답변종료예정일자
     */
    @NotNull
    @Column(name = "reply_end_expect_date", nullable = false)
    private LocalDate replyEndExpectDate;

    /**
     * 답변종료실제일자
     */
    @Column(name = "reply_end_actual_date")
    private LocalDate replyEndActualDate;

    /**
     * 답변만족도점수
     */
    @Column(name = "reply_satisfaction_score")
    private Long replySatisfactionScore;

    
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

    @OneToMany(mappedBy = "contentsInquiryMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contentsInquiryMaster" }, allowSetters = true)
    private Set<ContentsInquiryHist> contentsInquiryHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsInquiryMaster setIsPersisted() {
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

    public ContentsInquiryMaster contentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.setContentsCatalogMaster(contentsCatalogMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public ContentsInquiryMaster userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<ContentsInquiryHist> getContentsInquiryHists() {
        return this.contentsInquiryHists;
    }

    public void setContentsInquiryHists(Set<ContentsInquiryHist> contentsInquiryHists) {
        if (this.contentsInquiryHists != null) {
            this.contentsInquiryHists.forEach(i -> i.setContentsInquiryMaster(null));
        }
        if (contentsInquiryHists != null) {
            contentsInquiryHists.forEach(i -> i.setContentsInquiryMaster(this));
        }
        this.contentsInquiryHists = contentsInquiryHists;
    }

    public ContentsInquiryMaster contentsInquiryHists(Set<ContentsInquiryHist> contentsInquiryHists) {
        this.setContentsInquiryHists(contentsInquiryHists);
        return this;
    }

    public ContentsInquiryMaster addContentsInquiryHist(ContentsInquiryHist contentsInquiryHist) {
        this.contentsInquiryHists.add(contentsInquiryHist);
        contentsInquiryHist.setContentsInquiryMaster(this);
        return this;
    }

    public ContentsInquiryMaster removeContentsInquiryHist(ContentsInquiryHist contentsInquiryHist) {
        this.contentsInquiryHists.remove(contentsInquiryHist);
        contentsInquiryHist.setContentsInquiryMaster(null);
        return this;
    }

    }
