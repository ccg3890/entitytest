package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ProgressStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 맞춤형콘텐츠문의이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_custom_contents_inquiry_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomContentsInquiryHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 맞춤형콘텐츠문의UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "custom_contents_inquiry_uuid", length = 36, nullable = false, unique = true)
    private String customContentsInquiryUuid;

    /**
     * 맞춤형콘텐츠문의ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "custom_contents_inquiry_id", length = 36, nullable = false)
    private String customContentsInquiryId;

    /**
     * 진행상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status_code", nullable = false)
    private ProgressStatusCode progressStatusCode;

    /**
     * 진행내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "progress_content")
    private String progressContent;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "userMaster", "contentsCatalogMasters", "customContentsInquiryHists" }, allowSetters = true)
    private CustomContentsInquiryMaster customContentsInquiryMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public CustomContentsInquiryHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public CustomContentsInquiryMaster getCustomContentsInquiryMaster() {
        return this.customContentsInquiryMaster;
    }

    public void setCustomContentsInquiryMaster(CustomContentsInquiryMaster customContentsInquiryMaster) {
        this.customContentsInquiryMaster = customContentsInquiryMaster;
    }

    public CustomContentsInquiryHist customContentsInquiryMaster(CustomContentsInquiryMaster customContentsInquiryMaster) {
        this.setCustomContentsInquiryMaster(customContentsInquiryMaster);
        return this;
    }

    }
