package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ErrorCode;
import net.lotte.marketplace.domain.enumeration.SendReceiveTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 인터페이스호출이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_interface_call_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InterfaceCallHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 인터페이스호출ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "interface_call_id", length = 36, nullable = false, unique = true)
    private String interfaceCallId;

    /**
     * 인터페이스ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "interface_id", length = 36, nullable = false)
    private String interfaceId;

    /**
     * 송수신구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "send_receive_type_code", nullable = false)
    private SendReceiveTypeCode sendReceiveTypeCode;

    /**
     * 연동전문
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "interface_telegram", nullable = false)
    private String interfaceTelegram;

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

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "companyMaster", "interfaceCallHists" }, allowSetters = true)
    private InterfaceList interfaceList;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public InterfaceCallHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public InterfaceList getInterfaceList() {
        return this.interfaceList;
    }

    public void setInterfaceList(InterfaceList interfaceList) {
        this.interfaceList = interfaceList;
    }

    public InterfaceCallHist interfaceList(InterfaceList interfaceList) {
        this.setInterfaceList(interfaceList);
        return this;
    }

    }
