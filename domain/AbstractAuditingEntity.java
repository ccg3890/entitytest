package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "version", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, allowGetters = true)
public abstract class AbstractAuditingEntity<T> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    public abstract T getId();

    @Version
    @Column(name = "version") @Comment("optimistic lock 버전 정보")
    private Long version;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false) @Comment("최초생성자")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false) @Comment("최초 생성 일시")
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50) @Comment("마지막 변경자")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date") @Comment("마지막 변경 일시")
    private Instant lastModifiedDate = Instant.now();
}
