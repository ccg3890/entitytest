package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.TemplateDetailAreaCode;
import net.lotte.marketplace.domain.enumeration.TemplateDetailTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 팝업템플릿상세
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_popup_template_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PopupTemplateDetails extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 팝업템플릿상세ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "popup_template_detail_id", length = 36, nullable = false, unique = true)
    private String popupTemplateDetailId;

    /**
     * 팝업템플릿ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "popup_template_id", length = 36, nullable = false)
    private String popupTemplateId;

    /**
     * 템플릿상세영역코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "template_detail_area_code", nullable = false)
    private TemplateDetailAreaCode templateDetailAreaCode;

    /**
     * 템플릿상세구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "template_detail_type_code", nullable = false)
    private TemplateDetailTypeCode templateDetailTypeCode;

    /**
     * 템플릿상세구분값
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "template_detail_type_value", length = 1000, nullable = false)
    private String templateDetailTypeValue;

    /**
     * 템플릿상세구분연결
     */
    @Size(max = 1000)
    @Column(name = "template_detail_type_link", length = 1000)
    private String templateDetailTypeLink;

    /**
     * 템플릿상세구분설명
     */
    @Size(max = 4000)
    @Column(name = "template_detail_type_explain", length = 4000)
    private String templateDetailTypeExplain;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "popupTemplateDetails", "popupScreenConsists" }, allowSetters = true)
    private PopupTemplate popupTemplate;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public PopupTemplateDetails setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public PopupTemplate getPopupTemplate() {
        return this.popupTemplate;
    }

    public void setPopupTemplate(PopupTemplate popupTemplate) {
        this.popupTemplate = popupTemplate;
    }

    public PopupTemplateDetails popupTemplate(PopupTemplate popupTemplate) {
        this.setPopupTemplate(popupTemplate);
        return this;
    }

    }
