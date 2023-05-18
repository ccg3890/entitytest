package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.lotte.marketplace.domain.enumeration.CalculateBankCode;
import net.lotte.marketplace.domain.enumeration.SexCode;
import net.lotte.marketplace.domain.enumeration.UserStatusCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * 회원계정원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Comment("회원계정원부ID")
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 회원ID
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_id", length = 20, nullable = false, unique = true)
    private String userId;

    /**
     * 회원구분ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "user_busi_type_id", length = 36, nullable = false)
    private String userBusiTypeId;

    /**
     * 소속기업ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "belong_company_id", length = 36, nullable = false)
    private String belongCompanyId;

    /**
     * 이름
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    /**
     * 비밀번호
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "user_password", length = 20, nullable = false)
    private String userPassword;

    /**
     * 모바일전화번호
     */
    @NotNull
    @Size(max = 12)
    @Column(name = "mobile_phone_number", length = 12, nullable = false)
    private String mobilePhoneNumber;

    /**
     * 이메일주소
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "email_address", length = 100, nullable = false)
    private String emailAddress;

    /**
     * 생년월일
     */
    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    /**
     * 성별
     */
    @Comment("성별")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_sex", nullable = false)
    private SexCode userSex;

    /**
     * 직종
     */
    @Size(max = 100)
    @Column(name = "job_type", length = 100)
    private String jobType;

    /**
     * 부서명
     */
    @Size(max = 100)
    @Column(name = "department_name", length = 100)
    private String departmentName;

    /**
     * 직책
     */
    @Size(max = 100)
    @Column(name = "job_position", length = 100)
    private String jobPosition;

    /**
     * 직장전화번호
     */
    @Size(max = 12)
    @Column(name = "job_phone_number", length = 12)
    private String jobPhoneNumber;

    /**
     * 분석가인증여부
     */
    @Column(name = "analyst_approve_yn")
    private Boolean analystApproveYn;

    /**
     * 회원소개
     */
    @Size(max = 4000)
    @Column(name = "user_introdue", length = 4000)
    private String userIntrodue;

    /**
     * 회원상태코드
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status_code", nullable = false)
    private UserStatusCode userStatusCode;

    /**
     * 접속IP주소
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "connect_ip_address", length = 20, nullable = false)
    private String connectIpAddress;

    /**
     * 최근접속일자
     */
    @NotNull
    @Column(name = "recently_connect_date", nullable = false)
    private LocalDate recentlyConnectDate;

    /**
     * 최근비밀번호변경일자
     */
    @NotNull
    @Column(name = "recently_change_password_date", nullable = false)
    private LocalDate recentlyChangePasswordDate;

    /**
     * 정산은행코드
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "calculate_bank_code")
    private CalculateBankCode calculateBankCode;

    /**
     * 정산은행계좌번호
     */
    @Size(max = 50)
    @Column(name = "calculate_bank_account_number", length = 50)
    private String calculateBankAccountNumber;

    /**
     * 정산은행계좌이름
     */
    @Size(max = 100)
    @Column(name = "calculate_bank_account_name", length = 100)
    private String calculateBankAccountName;

    /**
     * 네이버ID
     */
    @Size(max = 20)
    @Column(name = "naver_login_id", length = 20)
    private String naverLoginId;

    /**
     * 네이버페이충전키
     */
    @Size(max = 100)
    @Column(name = "naver_pay_recharge_key", length = 100)
    private String naverPayRechargeKey;

    /**
     * 알림톡접속ID
     */
    @Size(max = 100)
    @Column(name = "infrom_talk_connect_id", length = 100)
    private String infromTalkConnectId;

    /**
     * 로그인실패횟수
     */
    @NotNull
    @Column(name = "login_failure_count", nullable = false)
    private Long loginFailureCount;

    /**
     * 카카오ID
     */
    @Size(max = 20)
    @Column(name = "kakao_login_id", length = 20)
    private String kakaoLoginId;

    /**
     * 카카오페이충전키
     */
    @Size(max = 100)
    @Column(name = "kakao_pay_recharge_key", length = 100)
    private String kakaoPayRechargeKey;


    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster", "userMaster"}, allowSetters = true)
    private Set<UserAgreement> userAgreements;

    @ManyToOne @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name="fk_company_master"))
    private CompanyMaster companyMaster;

    @ManyToOne @JoinColumn(name = "user_busi_type_id", foreignKey = @ForeignKey(name="fk_usermaster_userbusitype"))
    private UserBusiType userBusiType;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
            value = {"sendInformTalkMaster", "sendMailMaster", "userMaster", "userVerifyHist", "userDormantMaster"},
            allowSetters = true
    )
    private Set<UserDormantHist> userDormantHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster"}, allowSetters = true)
    private Set<UserQuitHist> userQuitHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster"}, allowSetters = true)
    private Set<UserLockHist> userLockHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster"}, allowSetters = true)
    private Set<UserChangeHist> userChangeHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster", "userDormantHists"}, allowSetters = true)
    private Set<UserVerifyHist> userVerifyHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"sendInformTalkMaster", "sendMailMaster", "userMaster"}, allowSetters = true)
    private Set<UserApproveHist> userApproveHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster"}, allowSetters = true)
    private Set<UserLoginHist> userLoginHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster", "batchExecuteHists"}, allowSetters = true)
    private Set<BatchPgmList> batchPgmLists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"contentsCatalogMaster", "userMaster", "recommendContentsHists"}, allowSetters = true)
    private Set<RecommendContentsMaster> recommendContentsMasters;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"recommendContentsMaster", "userMaster"}, allowSetters = true)
    private Set<RecommendContentsHist> recommendContentsHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster", "sendMailHists", "userDormantHists", "userApproveHists"}, allowSetters = true)
    private Set<SendMailMaster> sendMailMasters;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"sendMailMaster", "userMaster"}, allowSetters = true)
    private Set<SendMailHist> sendMailHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
            value = {"userMaster", "mainScreenConsists", "pgmCallLists", "popupScreenConsists", "sysMenus"},
            allowSetters = true
    )
    private Set<PgmList> pgmLists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"batchExecuteHist", "contentsBuyMaster", "userMaster"}, allowSetters = true)
    private Set<ContentsSubscribeHist> contentsSubscribeHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"contentsRole", "userMaster", "contentsRoleApproveHists"}, allowSetters = true)
    private Set<ContentsRoleRequest> contentsRoleRequests;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"contentsRoleRequest", "userMaster"}, allowSetters = true)
    private Set<ContentsRoleApproveHist> contentsRoleApproveHists;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster", "qnaHists"}, allowSetters = true)
    private Set<QnaMaster> qnaMasters;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"contentsCatalogMaster", "userMaster", "contentsInquiryHists"}, allowSetters = true)
    private Set<ContentsInquiryMaster> contentsInquiryMasters;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster", "contentsCatalogMasters", "customContentsInquiryHists"}, allowSetters = true)
    private Set<CustomContentsInquiryMaster> customContentsInquiryMasters;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"contentsCatalogMaster", "userMaster", "contentsStatisticHists"}, allowSetters = true)
    private Set<ContentsStatisticMaster> contentsStatisticMasters;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"userMaster"}, allowSetters = true)
    private Set<UserAttentionContents> userAttentionContents;

    @OneToMany(mappedBy = "userMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"sendInformTalkMaster", "userMaster"}, allowSetters = true)
    private Set<SendInformTalkHist> sendInformTalkHists;

    public CompanyMaster getCompanyMaster() {
        return this.companyMaster;
    }

    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }

    public UserMaster companyMaster(CompanyMaster companyMaster) {
        this.setCompanyMaster(companyMaster);
        return this;
    }

    public UserBusiType getUserBusiType() {
        return this.userBusiType;
    }

    public void setUserBusiType(UserBusiType userBusiType) {
        this.userBusiType = userBusiType;
    }

    public UserMaster userBusiType(UserBusiType userBusiType) {
        this.setUserBusiType(userBusiType);
        return this;
    }

    public Set<UserDormantHist> getUserDormantHists() {
        return this.userDormantHists;
    }

    public void setUserDormantHists(Set<UserDormantHist> userDormantHists) {
        if (this.userDormantHists != null) {
            this.userDormantHists.forEach(i -> i.setUserMaster(null));
        }
        if (userDormantHists != null) {
            userDormantHists.forEach(i -> i.setUserMaster(this));
        }
        this.userDormantHists = userDormantHists;
    }

    public UserMaster userDormantHists(Set<UserDormantHist> userDormantHists) {
        this.setUserDormantHists(userDormantHists);
        return this;
    }

    public UserMaster addUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.add(userDormantHist);
        userDormantHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserDormantHist(UserDormantHist userDormantHist) {
        this.userDormantHists.remove(userDormantHist);
        userDormantHist.setUserMaster(null);
        return this;
    }

    public Set<UserQuitHist> getUserQuitHists() {
        return this.userQuitHists;
    }

    public void setUserQuitHists(Set<UserQuitHist> userQuitHists) {
        if (this.userQuitHists != null) {
            this.userQuitHists.forEach(i -> i.setUserMaster(null));
        }
        if (userQuitHists != null) {
            userQuitHists.forEach(i -> i.setUserMaster(this));
        }
        this.userQuitHists = userQuitHists;
    }

    public UserMaster userQuitHists(Set<UserQuitHist> userQuitHists) {
        this.setUserQuitHists(userQuitHists);
        return this;
    }

    public UserMaster addUserQuitHist(UserQuitHist userQuitHist) {
        this.userQuitHists.add(userQuitHist);
        userQuitHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserQuitHist(UserQuitHist userQuitHist) {
        this.userQuitHists.remove(userQuitHist);
        userQuitHist.setUserMaster(null);
        return this;
    }

    public UserMaster userAgreements(Set<UserAgreement> userAgreements) {
        this.setUserAgreements(userAgreements);
        return this;
    }

    public Set<UserLockHist> getUserLockHists() {
        return this.userLockHists;
    }

    public void setUserLockHists(Set<UserLockHist> userLockHists) {
        if (this.userLockHists != null) {
            this.userLockHists.forEach(i -> i.setUserMaster(null));
        }
        if (userLockHists != null) {
            userLockHists.forEach(i -> i.setUserMaster(this));
        }
        this.userLockHists = userLockHists;
    }

    public UserMaster userLockHists(Set<UserLockHist> userLockHists) {
        this.setUserLockHists(userLockHists);
        return this;
    }

    public UserMaster addUserLockHist(UserLockHist userLockHist) {
        this.userLockHists.add(userLockHist);
        userLockHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserLockHist(UserLockHist userLockHist) {
        this.userLockHists.remove(userLockHist);
        userLockHist.setUserMaster(null);
        return this;
    }

    public Set<UserChangeHist> getUserChangeHists() {
        return this.userChangeHists;
    }

    public void setUserChangeHists(Set<UserChangeHist> userChangeHists) {
        if (this.userChangeHists != null) {
            this.userChangeHists.forEach(i -> i.setUserMaster(null));
        }
        if (userChangeHists != null) {
            userChangeHists.forEach(i -> i.setUserMaster(this));
        }
        this.userChangeHists = userChangeHists;
    }

    public UserMaster userChangeHists(Set<UserChangeHist> userChangeHists) {
        this.setUserChangeHists(userChangeHists);
        return this;
    }

    public UserMaster addUserChangeHist(UserChangeHist userChangeHist) {
        this.userChangeHists.add(userChangeHist);
        userChangeHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserChangeHist(UserChangeHist userChangeHist) {
        this.userChangeHists.remove(userChangeHist);
        userChangeHist.setUserMaster(null);
        return this;
    }

    public Set<UserVerifyHist> getUserVerifyHists() {
        return this.userVerifyHists;
    }

    public void setUserVerifyHists(Set<UserVerifyHist> userVerifyHists) {
        if (this.userVerifyHists != null) {
            this.userVerifyHists.forEach(i -> i.setUserMaster(null));
        }
        if (userVerifyHists != null) {
            userVerifyHists.forEach(i -> i.setUserMaster(this));
        }
        this.userVerifyHists = userVerifyHists;
    }

    public UserMaster userVerifyHists(Set<UserVerifyHist> userVerifyHists) {
        this.setUserVerifyHists(userVerifyHists);
        return this;
    }

    public UserMaster addUserVerifyHist(UserVerifyHist userVerifyHist) {
        this.userVerifyHists.add(userVerifyHist);
        userVerifyHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserVerifyHist(UserVerifyHist userVerifyHist) {
        this.userVerifyHists.remove(userVerifyHist);
        userVerifyHist.setUserMaster(null);
        return this;
    }

    public Set<UserApproveHist> getUserApproveHists() {
        return this.userApproveHists;
    }

    public void setUserApproveHists(Set<UserApproveHist> userApproveHists) {
        if (this.userApproveHists != null) {
            this.userApproveHists.forEach(i -> i.setUserMaster(null));
        }
        if (userApproveHists != null) {
            userApproveHists.forEach(i -> i.setUserMaster(this));
        }
        this.userApproveHists = userApproveHists;
    }

    public UserMaster userApproveHists(Set<UserApproveHist> userApproveHists) {
        this.setUserApproveHists(userApproveHists);
        return this;
    }

    public UserMaster addUserApproveHist(UserApproveHist userApproveHist) {
        this.userApproveHists.add(userApproveHist);
        userApproveHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserApproveHist(UserApproveHist userApproveHist) {
        this.userApproveHists.remove(userApproveHist);
        userApproveHist.setUserMaster(null);
        return this;
    }

    public Set<UserLoginHist> getUserLoginHists() {
        return this.userLoginHists;
    }

    public void setUserLoginHists(Set<UserLoginHist> userLoginHists) {
        if (this.userLoginHists != null) {
            this.userLoginHists.forEach(i -> i.setUserMaster(null));
        }
        if (userLoginHists != null) {
            userLoginHists.forEach(i -> i.setUserMaster(this));
        }
        this.userLoginHists = userLoginHists;
    }

    public UserMaster userLoginHists(Set<UserLoginHist> userLoginHists) {
        this.setUserLoginHists(userLoginHists);
        return this;
    }

    public UserMaster addUserLoginHist(UserLoginHist userLoginHist) {
        this.userLoginHists.add(userLoginHist);
        userLoginHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserLoginHist(UserLoginHist userLoginHist) {
        this.userLoginHists.remove(userLoginHist);
        userLoginHist.setUserMaster(null);
        return this;
    }

    public Set<BatchPgmList> getBatchPgmLists() {
        return this.batchPgmLists;
    }

    public void setBatchPgmLists(Set<BatchPgmList> batchPgmLists) {
        if (this.batchPgmLists != null) {
            this.batchPgmLists.forEach(i -> i.setUserMaster(null));
        }
        if (batchPgmLists != null) {
            batchPgmLists.forEach(i -> i.setUserMaster(this));
        }
        this.batchPgmLists = batchPgmLists;
    }

    public UserMaster batchPgmLists(Set<BatchPgmList> batchPgmLists) {
        this.setBatchPgmLists(batchPgmLists);
        return this;
    }

    public UserMaster addBatchPgmList(BatchPgmList batchPgmList) {
        this.batchPgmLists.add(batchPgmList);
        batchPgmList.setUserMaster(this);
        return this;
    }

    public UserMaster removeBatchPgmList(BatchPgmList batchPgmList) {
        this.batchPgmLists.remove(batchPgmList);
        batchPgmList.setUserMaster(null);
        return this;
    }

    public Set<RecommendContentsMaster> getRecommendContentsMasters() {
        return this.recommendContentsMasters;
    }

    public void setRecommendContentsMasters(Set<RecommendContentsMaster> recommendContentsMasters) {
        if (this.recommendContentsMasters != null) {
            this.recommendContentsMasters.forEach(i -> i.setUserMaster(null));
        }
        if (recommendContentsMasters != null) {
            recommendContentsMasters.forEach(i -> i.setUserMaster(this));
        }
        this.recommendContentsMasters = recommendContentsMasters;
    }

    public UserMaster recommendContentsMasters(Set<RecommendContentsMaster> recommendContentsMasters) {
        this.setRecommendContentsMasters(recommendContentsMasters);
        return this;
    }

    public UserMaster addRecommendContentsMaster(RecommendContentsMaster recommendContentsMaster) {
        this.recommendContentsMasters.add(recommendContentsMaster);
        recommendContentsMaster.setUserMaster(this);
        return this;
    }

    public UserMaster removeRecommendContentsMaster(RecommendContentsMaster recommendContentsMaster) {
        this.recommendContentsMasters.remove(recommendContentsMaster);
        recommendContentsMaster.setUserMaster(null);
        return this;
    }

    public Set<RecommendContentsHist> getRecommendContentsHists() {
        return this.recommendContentsHists;
    }

    public void setRecommendContentsHists(Set<RecommendContentsHist> recommendContentsHists) {
        if (this.recommendContentsHists != null) {
            this.recommendContentsHists.forEach(i -> i.setUserMaster(null));
        }
        if (recommendContentsHists != null) {
            recommendContentsHists.forEach(i -> i.setUserMaster(this));
        }
        this.recommendContentsHists = recommendContentsHists;
    }

    public UserMaster recommendContentsHists(Set<RecommendContentsHist> recommendContentsHists) {
        this.setRecommendContentsHists(recommendContentsHists);
        return this;
    }

    public UserMaster addRecommendContentsHist(RecommendContentsHist recommendContentsHist) {
        this.recommendContentsHists.add(recommendContentsHist);
        recommendContentsHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeRecommendContentsHist(RecommendContentsHist recommendContentsHist) {
        this.recommendContentsHists.remove(recommendContentsHist);
        recommendContentsHist.setUserMaster(null);
        return this;
    }

    public Set<SendMailMaster> getSendMailMasters() {
        return this.sendMailMasters;
    }

    public void setSendMailMasters(Set<SendMailMaster> sendMailMasters) {
        if (this.sendMailMasters != null) {
            this.sendMailMasters.forEach(i -> i.setUserMaster(null));
        }
        if (sendMailMasters != null) {
            sendMailMasters.forEach(i -> i.setUserMaster(this));
        }
        this.sendMailMasters = sendMailMasters;
    }

    public UserMaster sendMailMasters(Set<SendMailMaster> sendMailMasters) {
        this.setSendMailMasters(sendMailMasters);
        return this;
    }

    public UserMaster addSendMailMaster(SendMailMaster sendMailMaster) {
        this.sendMailMasters.add(sendMailMaster);
        sendMailMaster.setUserMaster(this);
        return this;
    }

    public UserMaster removeSendMailMaster(SendMailMaster sendMailMaster) {
        this.sendMailMasters.remove(sendMailMaster);
        sendMailMaster.setUserMaster(null);
        return this;
    }

    public Set<SendMailHist> getSendMailHists() {
        return this.sendMailHists;
    }

    public void setSendMailHists(Set<SendMailHist> sendMailHists) {
        if (this.sendMailHists != null) {
            this.sendMailHists.forEach(i -> i.setUserMaster(null));
        }
        if (sendMailHists != null) {
            sendMailHists.forEach(i -> i.setUserMaster(this));
        }
        this.sendMailHists = sendMailHists;
    }

    public UserMaster sendMailHists(Set<SendMailHist> sendMailHists) {
        this.setSendMailHists(sendMailHists);
        return this;
    }

    public UserMaster addSendMailHist(SendMailHist sendMailHist) {
        this.sendMailHists.add(sendMailHist);
        sendMailHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeSendMailHist(SendMailHist sendMailHist) {
        this.sendMailHists.remove(sendMailHist);
        sendMailHist.setUserMaster(null);
        return this;
    }

    public Set<PgmList> getPgmLists() {
        return this.pgmLists;
    }

    public void setPgmLists(Set<PgmList> pgmLists) {
        if (this.pgmLists != null) {
            this.pgmLists.forEach(i -> i.setUserMaster(null));
        }
        if (pgmLists != null) {
            pgmLists.forEach(i -> i.setUserMaster(this));
        }
        this.pgmLists = pgmLists;
    }

    public UserMaster pgmLists(Set<PgmList> pgmLists) {
        this.setPgmLists(pgmLists);
        return this;
    }

    public UserMaster addPgmList(PgmList pgmList) {
        this.pgmLists.add(pgmList);
        pgmList.setUserMaster(this);
        return this;
    }

    public UserMaster removePgmList(PgmList pgmList) {
        this.pgmLists.remove(pgmList);
        pgmList.setUserMaster(null);
        return this;
    }

    public Set<ContentsSubscribeHist> getContentsSubscribeHists() {
        return this.contentsSubscribeHists;
    }

    public void setContentsSubscribeHists(Set<ContentsSubscribeHist> contentsSubscribeHists) {
        if (this.contentsSubscribeHists != null) {
            this.contentsSubscribeHists.forEach(i -> i.setUserMaster(null));
        }
        if (contentsSubscribeHists != null) {
            contentsSubscribeHists.forEach(i -> i.setUserMaster(this));
        }
        this.contentsSubscribeHists = contentsSubscribeHists;
    }

    public UserMaster contentsSubscribeHists(Set<ContentsSubscribeHist> contentsSubscribeHists) {
        this.setContentsSubscribeHists(contentsSubscribeHists);
        return this;
    }

    public UserMaster addContentsSubscribeHist(ContentsSubscribeHist contentsSubscribeHist) {
        this.contentsSubscribeHists.add(contentsSubscribeHist);
        contentsSubscribeHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeContentsSubscribeHist(ContentsSubscribeHist contentsSubscribeHist) {
        this.contentsSubscribeHists.remove(contentsSubscribeHist);
        contentsSubscribeHist.setUserMaster(null);
        return this;
    }

    public Set<ContentsRoleRequest> getContentsRoleRequests() {
        return this.contentsRoleRequests;
    }

    public void setContentsRoleRequests(Set<ContentsRoleRequest> contentsRoleRequests) {
        if (this.contentsRoleRequests != null) {
            this.contentsRoleRequests.forEach(i -> i.setUserMaster(null));
        }
        if (contentsRoleRequests != null) {
            contentsRoleRequests.forEach(i -> i.setUserMaster(this));
        }
        this.contentsRoleRequests = contentsRoleRequests;
    }

    public UserMaster contentsRoleRequests(Set<ContentsRoleRequest> contentsRoleRequests) {
        this.setContentsRoleRequests(contentsRoleRequests);
        return this;
    }

    public UserMaster addContentsRoleRequest(ContentsRoleRequest contentsRoleRequest) {
        this.contentsRoleRequests.add(contentsRoleRequest);
        contentsRoleRequest.setUserMaster(this);
        return this;
    }

    public UserMaster removeContentsRoleRequest(ContentsRoleRequest contentsRoleRequest) {
        this.contentsRoleRequests.remove(contentsRoleRequest);
        contentsRoleRequest.setUserMaster(null);
        return this;
    }

    public Set<ContentsRoleApproveHist> getContentsRoleApproveHists() {
        return this.contentsRoleApproveHists;
    }

    public void setContentsRoleApproveHists(Set<ContentsRoleApproveHist> contentsRoleApproveHists) {
        if (this.contentsRoleApproveHists != null) {
            this.contentsRoleApproveHists.forEach(i -> i.setUserMaster(null));
        }
        if (contentsRoleApproveHists != null) {
            contentsRoleApproveHists.forEach(i -> i.setUserMaster(this));
        }
        this.contentsRoleApproveHists = contentsRoleApproveHists;
    }

    public UserMaster contentsRoleApproveHists(Set<ContentsRoleApproveHist> contentsRoleApproveHists) {
        this.setContentsRoleApproveHists(contentsRoleApproveHists);
        return this;
    }

    public UserMaster addContentsRoleApproveHist(ContentsRoleApproveHist contentsRoleApproveHist) {
        this.contentsRoleApproveHists.add(contentsRoleApproveHist);
        contentsRoleApproveHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeContentsRoleApproveHist(ContentsRoleApproveHist contentsRoleApproveHist) {
        this.contentsRoleApproveHists.remove(contentsRoleApproveHist);
        contentsRoleApproveHist.setUserMaster(null);
        return this;
    }

    public Set<QnaMaster> getQnaMasters() {
        return this.qnaMasters;
    }

    public void setQnaMasters(Set<QnaMaster> qnaMasters) {
        if (this.qnaMasters != null) {
            this.qnaMasters.forEach(i -> i.setUserMaster(null));
        }
        if (qnaMasters != null) {
            qnaMasters.forEach(i -> i.setUserMaster(this));
        }
        this.qnaMasters = qnaMasters;
    }

    public UserMaster qnaMasters(Set<QnaMaster> qnaMasters) {
        this.setQnaMasters(qnaMasters);
        return this;
    }

    public UserMaster addQnaMaster(QnaMaster qnaMaster) {
        this.qnaMasters.add(qnaMaster);
        qnaMaster.setUserMaster(this);
        return this;
    }

    public UserMaster removeQnaMaster(QnaMaster qnaMaster) {
        this.qnaMasters.remove(qnaMaster);
        qnaMaster.setUserMaster(null);
        return this;
    }

    public Set<ContentsInquiryMaster> getContentsInquiryMasters() {
        return this.contentsInquiryMasters;
    }

    public void setContentsInquiryMasters(Set<ContentsInquiryMaster> contentsInquiryMasters) {
        if (this.contentsInquiryMasters != null) {
            this.contentsInquiryMasters.forEach(i -> i.setUserMaster(null));
        }
        if (contentsInquiryMasters != null) {
            contentsInquiryMasters.forEach(i -> i.setUserMaster(this));
        }
        this.contentsInquiryMasters = contentsInquiryMasters;
    }

    public UserMaster contentsInquiryMasters(Set<ContentsInquiryMaster> contentsInquiryMasters) {
        this.setContentsInquiryMasters(contentsInquiryMasters);
        return this;
    }

    public UserMaster addContentsInquiryMaster(ContentsInquiryMaster contentsInquiryMaster) {
        this.contentsInquiryMasters.add(contentsInquiryMaster);
        contentsInquiryMaster.setUserMaster(this);
        return this;
    }

    public UserMaster removeContentsInquiryMaster(ContentsInquiryMaster contentsInquiryMaster) {
        this.contentsInquiryMasters.remove(contentsInquiryMaster);
        contentsInquiryMaster.setUserMaster(null);
        return this;
    }

    public Set<CustomContentsInquiryMaster> getCustomContentsInquiryMasters() {
        return this.customContentsInquiryMasters;
    }

    public void setCustomContentsInquiryMasters(Set<CustomContentsInquiryMaster> customContentsInquiryMasters) {
        if (this.customContentsInquiryMasters != null) {
            this.customContentsInquiryMasters.forEach(i -> i.setUserMaster(null));
        }
        if (customContentsInquiryMasters != null) {
            customContentsInquiryMasters.forEach(i -> i.setUserMaster(this));
        }
        this.customContentsInquiryMasters = customContentsInquiryMasters;
    }

    public UserMaster customContentsInquiryMasters(Set<CustomContentsInquiryMaster> customContentsInquiryMasters) {
        this.setCustomContentsInquiryMasters(customContentsInquiryMasters);
        return this;
    }

    public UserMaster addCustomContentsInquiryMaster(CustomContentsInquiryMaster customContentsInquiryMaster) {
        this.customContentsInquiryMasters.add(customContentsInquiryMaster);
        customContentsInquiryMaster.setUserMaster(this);
        return this;
    }

    public UserMaster removeCustomContentsInquiryMaster(CustomContentsInquiryMaster customContentsInquiryMaster) {
        this.customContentsInquiryMasters.remove(customContentsInquiryMaster);
        customContentsInquiryMaster.setUserMaster(null);
        return this;
    }

    public Set<ContentsStatisticMaster> getContentsStatisticMasters() {
        return this.contentsStatisticMasters;
    }

    public void setContentsStatisticMasters(Set<ContentsStatisticMaster> contentsStatisticMasters) {
        if (this.contentsStatisticMasters != null) {
            this.contentsStatisticMasters.forEach(i -> i.setUserMaster(null));
        }
        if (contentsStatisticMasters != null) {
            contentsStatisticMasters.forEach(i -> i.setUserMaster(this));
        }
        this.contentsStatisticMasters = contentsStatisticMasters;
    }

    public UserMaster contentsStatisticMasters(Set<ContentsStatisticMaster> contentsStatisticMasters) {
        this.setContentsStatisticMasters(contentsStatisticMasters);
        return this;
    }

    public UserMaster addContentsStatisticMaster(ContentsStatisticMaster contentsStatisticMaster) {
        this.contentsStatisticMasters.add(contentsStatisticMaster);
        contentsStatisticMaster.setUserMaster(this);
        return this;
    }

    public UserMaster removeContentsStatisticMaster(ContentsStatisticMaster contentsStatisticMaster) {
        this.contentsStatisticMasters.remove(contentsStatisticMaster);
        contentsStatisticMaster.setUserMaster(null);
        return this;
    }

    public Set<UserAttentionContents> getUserAttentionContents() {
        return this.userAttentionContents;
    }

    public void setUserAttentionContents(Set<UserAttentionContents> userAttentionContents) {
        if (this.userAttentionContents != null) {
            this.userAttentionContents.forEach(i -> i.setUserMaster(null));
        }
        if (userAttentionContents != null) {
            userAttentionContents.forEach(i -> i.setUserMaster(this));
        }
        this.userAttentionContents = userAttentionContents;
    }

    public UserMaster userAttentionContents(Set<UserAttentionContents> userAttentionContents) {
        this.setUserAttentionContents(userAttentionContents);
        return this;
    }

    public UserMaster addUserAttentionContents(UserAttentionContents userAttentionContents) {
        this.userAttentionContents.add(userAttentionContents);
        userAttentionContents.setUserMaster(this);
        return this;
    }

    public UserMaster removeUserAttentionContents(UserAttentionContents userAttentionContents) {
        this.userAttentionContents.remove(userAttentionContents);
        userAttentionContents.setUserMaster(null);
        return this;
    }

    public Set<SendInformTalkHist> getSendInformTalkHists() {
        return this.sendInformTalkHists;
    }

    public void setSendInformTalkHists(Set<SendInformTalkHist> sendInformTalkHists) {
        if (this.sendInformTalkHists != null) {
            this.sendInformTalkHists.forEach(i -> i.setUserMaster(null));
        }
        if (sendInformTalkHists != null) {
            sendInformTalkHists.forEach(i -> i.setUserMaster(this));
        }
        this.sendInformTalkHists = sendInformTalkHists;
    }

    public UserMaster sendInformTalkHists(Set<SendInformTalkHist> sendInformTalkHists) {
        this.setSendInformTalkHists(sendInformTalkHists);
        return this;
    }

    public UserMaster addSendInformTalkHist(SendInformTalkHist sendInformTalkHist) {
        this.sendInformTalkHists.add(sendInformTalkHist);
        sendInformTalkHist.setUserMaster(this);
        return this;
    }

    public UserMaster removeSendInformTalkHist(SendInformTalkHist sendInformTalkHist) {
        this.sendInformTalkHists.remove(sendInformTalkHist);
        sendInformTalkHist.setUserMaster(null);
        return this;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}

