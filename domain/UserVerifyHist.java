package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.lotte.marketplace.domain.enumeration.BusinessNumberTypeCode;
import net.lotte.marketplace.domain.enumeration.VerifyPurposeCode;
import net.lotte.marketplace.domain.enumeration.VerifyTypeCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

/**
 * 회원계정본인인증이력
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_verify_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserVerifyHist extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 회원인증ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_verify_id", length = 36, nullable = false, unique = true)
    private String userVerifyId;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * 인증구분코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "verify_type_code", nullable = false)
    private VerifyTypeCode verifyTypeCode;

    /**
     * 인증목적코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "verify_purpose_code", nullable = false)
    private VerifyPurposeCode verifyPurposeCode;

    /**
     * 인증용도이름
     */
    @Size(max = 100)
    @Column(name = "verify_use_name", length = 100)
    private String verifyUseName;

    /**
     * 인증용도생년월일
     */
    @Column(name = "verify_use_birth_date")
    private LocalDate verifyUseBirthDate;

    /**
     * 인증용도모바일전화번호
     */
    @Size(max = 12)
    @Column(name = "verify_use_mobile_phone_number", length = 12)
    private String verifyUseMobilePhoneNumber;

    /**
     * 발송인증번호
     */
    @Size(max = 6)
    @Column(name = "send_verify_number", length = 6)
    private String sendVerifyNumber;

    /**
     * 입력인증번호
     */
    @Size(max = 6)
    @Column(name = "input_verify_number", length = 6)
    private String inputVerifyNumber;

    /**
     * 사업자번호구분코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "business_number_type_code")
    private BusinessNumberTypeCode businessNumberTypeCode;

    /**
     * 사업자번호
     */
    @Size(max = 20)
    @Column(name = "business_number", length = 20)
    private String businessNumber;

    /**
     * 사업자명
     */
    @Size(max = 300)
    @Column(name = "business_company_name", length = 300)
    private String businessCompanyName;

    /**
     * 대표자명
     */
    @Size(max = 300)
    @Column(name = "representer_name", length = 300)
    private String representerName;

    /**
     * 인증용도이메일주소
     */
    @Size(max = 100)
    @Column(name = "verify_use_email_address", length = 100)
    private String verifyUseEmailAddress;

    /**
     * 정상인증여부
     */
    @NotNull
    @Column(name = "normal_verify_yn", nullable = false)
    private Boolean normalVerifyYn;

    /**
     * 인증실패사유
     */
    @Size(max = 4000)
    @Column(name = "verify_fail_reason", length = 4000)
    private String verifyFailReason;

    
    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "userAgreements",
            "companyMaster",
            "userBusiType",
            "userDormantHists",
            "userQuitHists",
            "userAgreements",
            "userLockHists",
            "userChangeHists",
            "userVerifyHists",
            "userApproveHists",
            "userLoginHists",
            "batchPgmLists",
            "recommendContentsMasters",
            "recommendContentsHists",
            "sendMailMasters",
            "sendMailHists",
            "pgmLists",
            "contentsSubscribeHists",
            "contentsRoleRequests",
            "contentsRoleApproveHists",
            "qnaMasters",
            "contentsInquiryMasters",
            "customContentsInquiryMasters",
            "contentsStatisticMasters",
            "userAttentionContents",
            "sendInformTalkHists",
        },
        allowSetters = true
    )
    private UserMaster userMaster;

    @OneToMany(mappedBy = "userVerifyHist")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "sendInformTalkMaster", "sendMailMaster", "userMaster", "userVerifyHist", "userDormantMaster" },
        allowSetters = true
    )
    private Set<UserDormantHist> userDormantHists ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public UserVerifyHist setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public UserMaster getUserMaster() {
        return this.userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    public UserVerifyHist userMaster(UserMaster userMaster) {
        this.setUserMaster(userMaster);
        return this;
    }

    public Set<UserDormantHist> getUserDormantHists() {
        return this.userDormantHists;
    }

    public void setUserDormantHists(Set<UserDormantHist> userDormantHists) {
        if (this.userDormantHists != null) {
            this.userDormantHists.forEach(i -> i.setUserVerifyHist(null));
        }
        if (userDormantHists != null) {
            userDormantHists.forEach(i -> i.setUserVerifyHist(this));
        }
        this.userDormantHists = userDormantHists;
    }

    public UserVerifyHist userDormantHists(Set<UserDormantHist> userDormantHists) {
        this.setUserDormantHists(userDormantHists);
        return this;
    }

    public UserVerifyHist addUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.add(userDormantHist);
        userDormantHist.setUserVerifyHist(this);
        return this;
    }

    public UserVerifyHist removeUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.remove(userDormantHist);
        userDormantHist.setUserVerifyHist(null);
        return this;
    }

    }
