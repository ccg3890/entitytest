package net.lotte.marketplace.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.lotte.marketplace.domain.enumeration.DormantStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 휴면회원계정이력
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_dormant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserDormant  extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Comment("휴면회원계정이력ID")
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    @Comment("회원계정원부ID")
    @NotNull
    @Size(max = 36)
    @Column(name = "user_master_id", length = 36, nullable = false)
    private String userMasterId;

    /**
     * 휴면계정상태코드
     */
    @Comment("휴면계정상태코드")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dormant_status_code", length = 30, nullable = false)
    private DormantStatusCode dormantStatusCode;

    /**
     * 고지메일발송ID
     */
    @Comment("고지메일발송ID")
    @NotNull
    @Size(max = 36)
    @Column(name = "notice_send_mail_id", length = 36, nullable = false)
    private String noticeSendMailId;

    /**
     * 고지알림톡발송ID
     */
    @Comment("고지알림톡발송ID")
    @NotNull
    @Size(max = 36)
    @Column(name = "notice_send_inform_talk_id", length = 36, nullable = false)
    private String noticeSendInformTalkId;

    /**
     * 휴면해제회원인증ID
     */
    @Comment("휴면해제회원인증ID")
    @NotNull
    @Size(max = 36)
    @Column(name = "remove_dormant_user_verify_id", length = 36, nullable = false)
    private String removeDormantUserVerifyId;
}
