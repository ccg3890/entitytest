package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.PopupTemplateClassCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 팝업템플릿
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_popup_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PopupTemplate extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 팝업템플릿ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "popup_template_id", length = 36, nullable = false, unique = true)
    private String popupTemplateId;

    /**
     * 팝업템플릿화면명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "popup_template_screen_name", length = 300, nullable = false)
    private String popupTemplateScreenName;

    /**
     * 팝업템플릿화면설명
     */
    @NotNull
    @Size(max = 4000)
    @Column(name = "popup_template_screen_explain", length = 4000, nullable = false)
    private String popupTemplateScreenExplain;

    /**
     * 팝업템플릿유형코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "popup_template_class_code", nullable = false)
    private PopupTemplateClassCode popupTemplateClassCode;

    /**
     * 팝업템플릿사용여부
     */
    @NotNull
    @Column(name = "popup_template_use_yn", nullable = false)
    private Boolean popupTemplateUseYn;

    
    @OneToMany(mappedBy = "popupTemplate")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "popupTemplate" }, allowSetters = true)
    private Set<PopupTemplateDetails> popupTemplateDetails ;

    @OneToMany(mappedBy = "popupTemplate")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "pgmList", "popupTemplate" }, allowSetters = true)
    private Set<PopupScreenConsist> popupScreenConsists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public PopupTemplate setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<PopupTemplateDetails> getPopupTemplateDetails() {
        return this.popupTemplateDetails;
    }

    public void setPopupTemplateDetails(Set<PopupTemplateDetails> popupTemplateDetails) {
        if (this.popupTemplateDetails != null) {
            this.popupTemplateDetails.forEach(i -> i.setPopupTemplate(null));
        }
        if (popupTemplateDetails != null) {
            popupTemplateDetails.forEach(i -> i.setPopupTemplate(this));
        }
        this.popupTemplateDetails = popupTemplateDetails;
    }

    public PopupTemplate popupTemplateDetails(Set<PopupTemplateDetails> popupTemplateDetails) {
        this.setPopupTemplateDetails(popupTemplateDetails);
        return this;
    }

    public PopupTemplate addPopupTemplateDetails(PopupTemplateDetails popupTemplateDetails) {
        this.popupTemplateDetails.add(popupTemplateDetails);
        popupTemplateDetails.setPopupTemplate(this);
        return this;
    }

    public PopupTemplate removePopupTemplateDetails(PopupTemplateDetails popupTemplateDetails) {
        this.popupTemplateDetails.remove(popupTemplateDetails);
        popupTemplateDetails.setPopupTemplate(null);
        return this;
    }

    public Set<PopupScreenConsist> getPopupScreenConsists() {
        return this.popupScreenConsists;
    }

    public void setPopupScreenConsists(Set<PopupScreenConsist> popupScreenConsists) {
        if (this.popupScreenConsists != null) {
            this.popupScreenConsists.forEach(i -> i.setPopupTemplate(null));
        }
        if (popupScreenConsists != null) {
            popupScreenConsists.forEach(i -> i.setPopupTemplate(this));
        }
        this.popupScreenConsists = popupScreenConsists;
    }

    public PopupTemplate popupScreenConsists(Set<PopupScreenConsist> popupScreenConsists) {
        this.setPopupScreenConsists(popupScreenConsists);
        return this;
    }

    public PopupTemplate addPopupScreenConsist(PopupScreenConsist popupScreenConsist) {
        this.popupScreenConsists.add(popupScreenConsist);
        popupScreenConsist.setPopupTemplate(this);
        return this;
    }

    public PopupTemplate removePopupScreenConsist(PopupScreenConsist popupScreenConsist) {
        this.popupScreenConsists.remove(popupScreenConsist);
        popupScreenConsist.setPopupTemplate(null);
        return this;
    }

    }
