package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.PopupScreenClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 팝업화면구성
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_popup_screen_consist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PopupScreenConsist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 팝업화면구성ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "popup_screen_consist_id", length = 36, nullable = false, unique = true)
    private String popupScreenConsistId;

    /**
     * 팝업화면유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "popup_screen_class_code", nullable = false)
    private PopupScreenClassCode popupScreenClassCode;

    /**
     * 프로그램UUID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "pgm_uuid", length = 36, nullable = false)
    private String pgmUuid;

    /**
     * 팝업화면순위
     */
    @NotNull
    @Column(name = "popup_screen_rank", nullable = false)
    private Long popupScreenRank;

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

    /**
     * 팝업템플릿ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "popup_template_id", length = 36, nullable = false)
    private String popupTemplateId;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = { "userMaster", "mainScreenConsists", "pgmCallLists", "popupScreenConsists", "sysMenus" },
        allowSetters = true
    )
    private PgmList pgmList;

    @ManyToOne
    @JsonIgnoreProperties(value = { "popupTemplateDetails", "popupScreenConsists" }, allowSetters = true)
    private PopupTemplate popupTemplate;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public PopupScreenConsist setIsPersisted() {
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

    public PopupScreenConsist pgmList(PgmList pgmList) {
        this.setPgmList(pgmList);
        return this;
    }

    public PopupTemplate getPopupTemplate() {
        return this.popupTemplate;
    }

    public void setPopupTemplate(PopupTemplate popupTemplate) {
        this.popupTemplate = popupTemplate;
    }

    public PopupScreenConsist popupTemplate(PopupTemplate popupTemplate) {
        this.setPopupTemplate(popupTemplate);
        return this;
    }

    }
