package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.NoticeTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 공지사항정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_notice_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NoticeInfo extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 공지사항ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "notice_id", length = 36, nullable = false, unique = true)
    private String noticeId;

    /**
     * 공지구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "notice_type_code", nullable = false)
    private NoticeTypeCode noticeTypeCode;

    /**
     * 공지구분ID
     */
    @Size(max = 36)
    @Column(name = "notice_type_id", length = 36)
    private String noticeTypeId;

    /**
     * 제목
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "title", length = 1000, nullable = false)
    private String title;

    /**
     * 공지내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "notice_content", nullable = false)
    private String noticeContent;

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

    public NoticeInfo setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    }
