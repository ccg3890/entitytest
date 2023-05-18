package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.ReplyStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠문의이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_inquiry_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsInquiryHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠문의UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_inquiry_uuid", length = 36, nullable = false, unique = true)
    private String contentsInquiryUuid;

    /**
     * 콘텐츠문의ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_inquiry_id", length = 36, nullable = false)
    private String contentsInquiryId;

    /**
     * 답변상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reply_status_code", nullable = false)
    private ReplyStatusCode replyStatusCode;

    /**
     * 답변제목
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "reply_title", length = 1000, nullable = false)
    private String replyTitle;

    /**
     * 답변내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "reply_content", nullable = false)
    private String replyContent;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "userMaster", "contentsInquiryHists" }, allowSetters = true)
    private ContentsInquiryMaster contentsInquiryMaster;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsInquiryHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public ContentsInquiryMaster getContentsInquiryMaster() {
        return this.contentsInquiryMaster;
    }

    public void setContentsInquiryMaster(ContentsInquiryMaster contentsInquiryMaster) {
        this.contentsInquiryMaster = contentsInquiryMaster;
    }

    public ContentsInquiryHist contentsInquiryMaster(ContentsInquiryMaster contentsInquiryMaster) {
        this.setContentsInquiryMaster(contentsInquiryMaster);
        return this;
    }

    }
