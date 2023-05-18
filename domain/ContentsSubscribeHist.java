package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.SubscribeClassCode;
import net.lotte.marketplace.domain.enumeration.SubscribeStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠구독처리이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_subscribe_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsSubscribeHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠구독처리ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_subscribe_id", length = 36, nullable = false, unique = true)
    private String contentsSubscribeId;

    /**
     * 콘텐츠구매ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_buy_id", length = 36, nullable = false)
    private String contentsBuyId;

    /**
     * 구독유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "subscribe_class_code", nullable = false)
    private SubscribeClassCode subscribeClassCode;

    /**
     * 구독자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "subscriber_id", length = 20, nullable = false)
    private String subscriberId;

    /**
     * 구독상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "subscribe_status_code", nullable = false)
    private SubscribeStatusCode subscribeStatusCode;

    /**
     * 구독처리배치실행ID
     */
    @Size(max = 36)
    @Column(name = "subscribe_batch_execute_id", length = 36)
    private String subscribeBatchExecuteId;

    /**
     * 알림톡발송여부
     */
    @NotNull
    @Column(name = "send_inform_talk_yn", nullable = false)
    private Boolean sendInformTalkYn;

    /**
     * 메일발송여부
     */
    @NotNull
    @Column(name = "send_mail_yn", nullable = false)
    private Boolean sendMailYn;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "batchPgmList", "contentsSubscribeHists" }, allowSetters = true)
    private BatchExecuteHist batchExecuteHist;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "contentsCatalogMaster", "shoppingBasketMaster", "calculateHists", "contentsBuyHists", "contentsSubscribeHists" },
        allowSetters = true
    )
    private ContentsBuyMaster contentsBuyMaster;

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

    public ContentsSubscribeHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public BatchExecuteHist getBatchExecuteHist() {
        return this.batchExecuteHist;
    }

    public void setBatchExecuteHist(BatchExecuteHist batchExecuteHist) {
        this.batchExecuteHist = batchExecuteHist;
    }

    public ContentsSubscribeHist batchExecuteHist(BatchExecuteHist batchExecuteHist) {
        this.setBatchExecuteHist(batchExecuteHist);
        return this;
    }

    public ContentsBuyMaster getContentsBuyMaster() {
        return this.contentsBuyMaster;
    }

    public void setContentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.contentsBuyMaster = contentsBuyMaster;
    }

    public ContentsSubscribeHist contentsBuyMaster(ContentsBuyMaster contentsBuyMaster) {
        this.setContentsBuyMaster(contentsBuyMaster);
        return this;
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public ContentsSubscribeHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
