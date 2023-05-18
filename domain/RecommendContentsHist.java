package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
 * 추천콘텐츠이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_recommend_contents_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RecommendContentsHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 추천콘텐츠UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_contents_uuid", length = 36, nullable = false, unique = true)
    private String recommendContentsUuid;

    /**
     * 추천콘텐츠ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "recommend_contents_id", length = 36, nullable = false)
    private String recommendContentsId;

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
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "userMaster", "recommendContentsHists" }, allowSetters = true)
    private RecommendContentsMaster recommendContentsMaster;

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

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public RecommendContentsHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public RecommendContentsMaster getRecommendContentsMaster() {
        return this.recommendContentsMaster;
    }

    public void setRecommendContentsMaster(RecommendContentsMaster recommendContentsMaster) {
        this.recommendContentsMaster = recommendContentsMaster;
    }

    public RecommendContentsHist recommendContentsMaster(RecommendContentsMaster recommendContentsMaster) {
        this.setRecommendContentsMaster(recommendContentsMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public RecommendContentsHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
