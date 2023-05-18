package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.OperationTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 첨부문서이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_attach_doc_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttachDocHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 첨부문서이력ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "attach_doc_history_id", length = 36, nullable = false, unique = true)
    private String attachDocHistoryId;

    /**
     * 첨부문서UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "attach_doc_uuid", length = 36, nullable = false)
    private String attachDocUuid;

    /**
     * 작업구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type_code", nullable = false)
    private OperationTypeCode operationTypeCode;

    /**
     * 작업자IP주소
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "operator_ip_address", length = 20, nullable = false)
    private String operatorIpAddress;

    /**
     * 작업목적설명
     */
    @Size(max = 4000)
    @Column(name = "operation_goal_explain", length = 4000)
    private String operationGoalExplain;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "companyMasters", "attachDocHists", "contentsCatalogMasters" }, allowSetters = true)
    private AttachDocMaster attachDocMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public AttachDocHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public AttachDocMaster getAttachDocMaster() {
        return this.attachDocMaster;
    }

    public void setAttachDocMaster(AttachDocMaster attachDocMaster) {
        this.attachDocMaster = attachDocMaster;
    }

    public AttachDocHist attachDocMaster(AttachDocMaster attachDocMaster) {
        this.setAttachDocMaster(attachDocMaster);
        return this;
    }

    }
