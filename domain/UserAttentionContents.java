package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ContentsCatalogClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 회원별관심콘텐츠관리
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_attention_contents")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserAttentionContents extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 관심콘텐츠ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "attention_contents_id", length = 36, nullable = false, unique = true)
    private String attentionContentsId;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * 콘텐츠카탈로그유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contents_catalog_class_code", nullable = false)
    private ContentsCatalogClassCode contentsCatalogClassCode;

    /**
     * 콘텐츠카탈로그유형값
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "contents_catalog_class_valuse", length = 20, nullable = false)
    private String contentsCatalogClassValuse;

    /**
     * 우선순위
     */
    @Column(name = "order_priority")
    private Long orderPriority;

    /**
     * 관심제외여부
     */
    @NotNull
    @Column(name = "attention_except_yn", nullable = false)
    private Boolean attentionExceptYn;

    /**
     * 관심시작일자
     */
    @NotNull
    @Column(name = "attention_start_date", nullable = false)
    private LocalDate attentionStartDate;

    /**
     * 관심종료일자
     */
    @NotNull
    @Column(name = "attention_end_date", nullable = false)
    private LocalDate attentionEndDate;

    
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

    public UserAttentionContents setIsPersisted() {
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

    public UserAttentionContents userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    }
