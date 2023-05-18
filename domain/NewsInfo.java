package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.NewsTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 플랫폼소식정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_news_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NewsInfo extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 소식정보ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "news_info_id", length = 36, nullable = false, unique = true)
    private String newsInfoId;

    /**
     * 소식명
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "news_name", length = 1000, nullable = false)
    private String newsName;

    /**
     * 소식구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "news_type_code", nullable = false)
    private NewsTypeCode newsTypeCode;

    /**
     * 소식전문
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "new_telegram", nullable = false)
    private String newTelegram;

    /**
     * 소식기업ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "news_company_id", length = 36, nullable = false)
    private String newsCompanyId;

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
    @JsonIgnoreProperties(
        value = { "attachDocMaster", "userMasters", "contentsCatalogMasters", "affiliateIntroduces", "interfaceLists", "newsInfos" },
        allowSetters = true
    )
    private CompanyMaster companyMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public NewsInfo setIsPersisted() {
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

    public NewsInfo companyMaster(CompanyMaster companyMaster) {
        this.setCompanyMaster(companyMaster);
        return this;
    }

    }
