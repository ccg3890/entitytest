package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.BatchTypeCode;
import net.lotte.marketplace.domain.enumeration.ExecuteCycleCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 배치프로그램목록
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_batch_pgm_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BatchPgmList extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 배치프로그램UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "batch_pgm_uuid", length = 36, nullable = false, unique = true)
    private String batchPgmUuid;

    /**
     * 배치프로그램ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "batch_pgm_id", length = 100, nullable = false)
    private String batchPgmId;

    /**
     * 배치프로그램명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "batch_pgm_name", length = 300, nullable = false)
    private String batchPgmName;

    /**
     * 배치구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "batch_type_code", nullable = false)
    private BatchTypeCode batchTypeCode;

    /**
     * 배치담당자ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "batch_user_id", length = 20, nullable = false)
    private String batchUserId;

    /**
     * 실행주기코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "execute_cycle_code")
    private ExecuteCycleCode executeCycleCode;

    /**
     * 선행필수실행여부
     */
    @NotNull
    @Column(name = "precede_mandatory_execute_yn", nullable = false)
    private Boolean precedeMandatoryExecuteYn;

    /**
     * 선행배치프로그램UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "precede_batch_pgm_uuid", length = 36, nullable = false)
    private String precedeBatchPgmUuid;

    /**
     * 입력인자설명
     */
    @Size(max = 500)
    @Column(name = "input_parameter_explain", length = 500)
    private String inputParameterExplain;

    /**
     * 로깅처리건수단위
     */
    @Column(name = "logging_process_count_unit")
    private Long loggingProcessCountUnit;

    /**
     * 배치프로그램설명
     */
    @Size(max = 4000)
    @Column(name = "batch_pgm_explain", length = 4000)
    private String batchPgmExplain;

    
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

    @OneToMany(mappedBy = "batchPgmList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "batchPgmList", "contentsSubscribeHists" }, allowSetters = true)
    private Set<BatchExecuteHist> batchExecuteHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public BatchPgmList setIsPersisted() {
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

    public BatchPgmList userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<BatchExecuteHist> getBatchExecuteHists() {
        return this.batchExecuteHists;
    }

    public void setBatchExecuteHists(Set<BatchExecuteHist> batchExecuteHists) {
        if (this.batchExecuteHists != null) {
            this.batchExecuteHists.forEach(i -> i.setBatchPgmList(null));
        }
        if (batchExecuteHists != null) {
            batchExecuteHists.forEach(i -> i.setBatchPgmList(this));
        }
        this.batchExecuteHists = batchExecuteHists;
    }

    public BatchPgmList batchExecuteHists(Set<BatchExecuteHist> batchExecuteHists) {
        this.setBatchExecuteHists(batchExecuteHists);
        return this;
    }

    public BatchPgmList addBatchExecuteHist(BatchExecuteHist batchExecuteHist) {
        this.batchExecuteHists.add(batchExecuteHist);
        batchExecuteHist.setBatchPgmList(this);
        return this;
    }

    public BatchPgmList removeBatchExecuteHist(BatchExecuteHist batchExecuteHist) {
        this.batchExecuteHists.remove(batchExecuteHist);
        batchExecuteHist.setBatchPgmList(null);
        return this;
    }

    }
