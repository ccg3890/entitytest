package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.InterfaceTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 인터페이스목록
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_interface_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InterfaceList extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 인터페이스ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "interface_id", length = 36, nullable = false, unique = true)
    private String interfaceId;

    /**
     * 인터페이스명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "interface_name", length = 300, nullable = false)
    private String interfaceName;

    /**
     * 연동구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "interface_type_code", nullable = false)
    private InterfaceTypeCode interfaceTypeCode;

    /**
     * 연동기업ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "interface_company_id", length = 36, nullable = false)
    private String interfaceCompanyId;

    /**
     * FTP접속IP주소
     */
    @Size(max = 20)
    @Column(name = "ftp_connect_ip_address", length = 20)
    private String ftpConnectIpAddress;

    /**
     * FTP접속ID
     */
    @Size(max = 100)
    @Column(name = "ftp_connect_id", length = 100)
    private String ftpConnectId;

    /**
     * FTP접속비밀번호
     */
    @Size(max = 200)
    @Column(name = "ftp_connect_password", length = 200)
    private String ftpConnectPassword;

    /**
     * API접속주소
     */
    @Size(max = 1000)
    @Column(name = "api_connect_address", length = 1000)
    private String apiConnectAddress;

    /**
     * API접속토큰
     */
    @Size(max = 1000)
    @Column(name = "api_connect_token", length = 1000)
    private String apiConnectToken;

    /**
     * API접속키
     */
    @Size(max = 1000)
    @Column(name = "api_connect_key", length = 1000)
    private String apiConnectKey;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = { "attachDocMaster", "userMasters", "contentsCatalogMasters", "affiliateIntroduces", "interfaceLists", "newsInfos" },
        allowSetters = true
    )
    private CompanyMaster companyMaster;

    @OneToMany(mappedBy = "interfaceList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "interfaceList" }, allowSetters = true)
    private Set<InterfaceCallHist> interfaceCallHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public InterfaceList setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public CompanyMaster getCompanyMaster() {
        return this.companyMaster;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public InterfaceList companyMaster(CompanyMaster companyMaster) {
        this.setCompanyMaster(companyMaster);
        return this;
    }

    public Set<InterfaceCallHist> getInterfaceCallHists() {
        return this.interfaceCallHists;
    }

    public void setInterfaceCallHists(Set<InterfaceCallHist> interfaceCallHists) {
        if (this.interfaceCallHists != null) {
            this.interfaceCallHists.forEach(i -> i.setInterfaceList(null));
        }
        if (interfaceCallHists != null) {
            interfaceCallHists.forEach(i -> i.setInterfaceList(this));
        }
        this.interfaceCallHists = interfaceCallHists;
    }

    public InterfaceList interfaceCallHists(Set<InterfaceCallHist> interfaceCallHists) {
        this.setInterfaceCallHists(interfaceCallHists);
        return this;
    }

    public InterfaceList addInterfaceCallHist(InterfaceCallHist interfaceCallHist) {
        this.interfaceCallHists.add(interfaceCallHist);
        interfaceCallHist.setInterfaceList(this);
        return this;
    }

    public InterfaceList removeInterfaceCallHist(InterfaceCallHist interfaceCallHist) {
        this.interfaceCallHists.remove(interfaceCallHist);
        interfaceCallHist.setInterfaceList(null);
        return this;
    }

    }
