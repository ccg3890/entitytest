package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 콘텐츠샘플데이터정보
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contents_sample_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContentsSampleData extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 콘텐츠샘플데이터ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contenst_sample_data_id", length = 36, nullable = false, unique = true)
    private String contenstSampleDataId;

    /**
     * 콘텐츠샘플데이터컬럼ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "contents_sample_column_id", length = 36, nullable = false)
    private String contentsSampleColumnId;

    /**
     * 데이터행번호
     */
    @NotNull
    @Column(name = "data_row_number", nullable = false)
    private Long dataRowNumber;

    /**
     * 데이터값
     */
    @Size(max = 300)
    @Column(name = "data_value", length = 300)
    private String dataValue;

    /**
     * 데이터상세값
     */
    @Size(max = 4000)
    @Column(name = "data_detail_value", length = 4000)
    private String dataDetailValue;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "contentsCatalogMaster", "contentsSampleData" }, allowSetters = true)
    private ContentsSampleColumn contentsSampleColumn;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public ContentsSampleData setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public ContentsSampleColumn getContentsSampleColumn() {
        return this.contentsSampleColumn;
    }

    public void setContentsSampleColumn(ContentsSampleColumn contentsSampleColumn) {
        this.contentsSampleColumn = contentsSampleColumn;
    }

    public ContentsSampleData contentsSampleColumn(ContentsSampleColumn contentsSampleColumn) {
        this.setContentsSampleColumn(contentsSampleColumn);
        return this;
    }

    }
