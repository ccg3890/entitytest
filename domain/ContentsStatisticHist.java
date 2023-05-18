package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.CallTypeCode;
import net.lotte.marketplace.domain.enumeration.StatisticProgressStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠통계이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_statistic_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsStatisticHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠통계UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_statistic_uuid", length = 36, nullable = false, unique = true)
    private String contentsStatisticUuid;

    /**
     * 콘텐츠통계ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_statistic_id", length = 36, nullable = false)
    private String contentsStatisticId;

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

    /**
     * 호출구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "call_type_code", nullable = false)
    private CallTypeCode callTypeCode;

    /**
     * 호출구분ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "call_type_id", length = 36, nullable = false)
    private String callTypeId;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "userMaster", "contentsStatisticHists" }, allowSetters = true)
    private ContentsStatisticMaster contentsStatisticMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsStatisticHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public ContentsStatisticMaster getContentsStatisticMaster() {
        return this.contentsStatisticMaster;
    }

    public void setContentsStatisticMaster(ContentsStatisticMaster contentsStatisticMaster) {
        this.contentsStatisticMaster = contentsStatisticMaster;
    }

    public ContentsStatisticHist contentsStatisticMaster(ContentsStatisticMaster contentsStatisticMaster) {
        this.setContentsStatisticMaster(contentsStatisticMaster);
        return this;
    }

    }
