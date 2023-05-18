package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ErrorCode;
import net.lotte.marketplace.domain.enumeration.ProcessTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 프로그램호출이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pgm_call_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PgmCallList extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 프로그램실행ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "pgm_execute_id", length = 36, nullable = false, unique = true)
    private String pgmExecuteId;

    /**
     * 프로그램UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "pgm_uuid", length = 36, nullable = false)
    private String pgmUuid;

    /**
     * 프로그램실행구분코드
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "pgm_execute_type_code", length = 20, nullable = false)
    private String pgmExecuteTypeCode;

    /**
     * 프로그램실행자ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "pgm_executer_id", length = 36, nullable = false)
    private String pgmExecuterId;

    /**
     * 호출전문
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "call_telegram")
    private String callTelegram;

    /**
     * 응답전문
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "reply_telegram")
    private String replyTelegram;

    /**
     * 정상처리여부
     */
    @NotNull
    @Column(name = "success_process_yn", nullable = false)
    private Boolean successProcessYn;

    /**
     * 오류코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "error_code")
    private ErrorCode errorCode;

    /**
     * 오류사유
     */
    @Size(max = 4000)
    @Column(name = "error_reason", length = 4000)
    private String errorReason;

    /**
     * 처리구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "process_type_code", nullable = false)
    private ProcessTypeCode processTypeCode;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = { "userMaster", "mainScreenConsists", "pgmCallLists", "popupScreenConsists", "sysMenus" },
        allowSetters = true
    )
    private PgmList pgmList;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public PgmCallList setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public PgmList getPgmList() {
        return this.pgmList;
    }

    public void setPgmList(PgmList pgmList) {
        this.pgmList = pgmList;
    }

    public PgmCallList pgmList(PgmList pgmList) {
        this.setPgmList(pgmList);
        return this;
    }

    }
