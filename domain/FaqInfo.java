package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.FaqTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * FAQ정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_faq_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FaqInfo extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * FAQ정보ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "faq_id", length = 36, nullable = false, unique = true)
    private String faqId;

    /**
     * FAQ구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "faq_type_code", nullable = false)
    private FaqTypeCode faqTypeCode;

    /**
     * FAQ구분ID
     */
    @Size(max = 36)
    @Column(name = "faq_type_id", length = 36)
    private String faqTypeId;

    /**
     * 제목
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "title", length = 1000, nullable = false)
    private String title;

    /**
     * FAQ내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "faq_content", nullable = false)
    private String faqContent;

    /**
     * 열람횟수
     */
    @NotNull
    @Column(name = "reading_count", nullable = false)
    private Long readingCount;

    /**
     * 적용시작일자
     */
    @NotNull
    @Column(name = "apply_start_date", nullable = false)
    private LocalDate applyStartDate;

    /**
     * 적용종료일자
     */
    @NotNull
    @Column(name = "apply_end_date", nullable = false)
    private LocalDate applyEndDate;

    
    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public FaqInfo setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    }
