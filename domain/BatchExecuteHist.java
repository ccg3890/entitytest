package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.BatchExecuteTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 배치프로그램실행이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_batch_execute_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BatchExecuteHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 배치실행ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "batch_execute_id", length = 36, nullable = false, unique = true)
    private String batchExecuteId;

    /**
     * 배치프로그램UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "batch_pgm_uuid", length = 36, nullable = false)
    private String batchPgmUuid;

    /**
     * 실행프로세스ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "execute_process_id", length = 100, nullable = false)
    private String executeProcessId;

    /**
     * 배치실행구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "batch_execute_type_code", nullable = false)
    private BatchExecuteTypeCode batchExecuteTypeCode;

    /**
     * 실행인자설명
     */
    @Size(max = 500)
    @Column(name = "execute_parameter_explain", length = 500)
    private String executeParameterExplain;

    /**
     * 총처리건수
     */
    @Column(name = "total_handling_count")
    private Long totalHandlingCount;

    /**
     * 정상처리건수
     */
    @Column(name = "normal_handling_count")
    private Long normalHandlingCount;

    /**
     * 오류처리건수
     */
    @Column(name = "error_handling_count")
    private Long errorHandlingCount;

    /**
     * 실행결과설명
     */
    @Size(max = 4000)
    @Column(name = "execute_result_explain", length = 4000)
    private String executeResultExplain;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "userMaster", "batchExecuteHists" }, allowSetters = true)
    private BatchPgmList batchPgmList;

    @OneToMany(mappedBy = "batchExecuteHist")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "batchExecuteHist", "contentsBuyMaster", "userMaster" }, allowSetters = true)
    private Set<ContentsSubscribeHist> contentsSubscribeHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public BatchExecuteHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public BatchPgmList getBatchPgmList() {
        return this.batchPgmList;
    }

    public void setBatchPgmList(BatchPgmList batchPgmList) {
        this.batchPgmList = batchPgmList;
    }

    public BatchExecuteHist batchPgmList(BatchPgmList batchPgmList) {
        this.setBatchPgmList(batchPgmList);
        return this;
    }

    public Set<ContentsSubscribeHist> getContentsSubscribeHists() {
        return this.contentsSubscribeHists;
    }

    public void setContentsSubscribeHists(Set<ContentsSubscribeHist> contentsSubscribeHists) {
        if (this.contentsSubscribeHists != null) {
            this.contentsSubscribeHists.forEach(i -> i.setBatchExecuteHist(null));
        }
        if (contentsSubscribeHists != null) {
            contentsSubscribeHists.forEach(i -> i.setBatchExecuteHist(this));
        }
        this.contentsSubscribeHists = contentsSubscribeHists;
    }

    public BatchExecuteHist contentsSubscribeHists(Set<ContentsSubscribeHist> contentsSubscribeHists) {
        this.setContentsSubscribeHists(contentsSubscribeHists);
        return this;
    }

    public BatchExecuteHist addContentsSubscribeHist(ContentsSubscribeHist contentsSubscribeHist) {
        this.contentsSubscribeHists.add(contentsSubscribeHist);
        contentsSubscribeHist.setBatchExecuteHist(this);
        return this;
    }

    public BatchExecuteHist removeContentsSubscribeHist(ContentsSubscribeHist contentsSubscribeHist) {
        this.contentsSubscribeHists.remove(contentsSubscribeHist);
        contentsSubscribeHist.setBatchExecuteHist(null);
        return this;
    }

    }
