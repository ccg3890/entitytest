package net.lotte.marketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
 * 기업그룹사원부
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_company_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CompanyMaster extends AbstractAuditingEntity<String> implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;

    /**
     * 기업그룹사ID
     */
    @NotNull
    @Size(max = 36)
    @Column(name = "company_id", length = 36, nullable = false, unique = true)
    private String companyId;

    /**
     * 기업그룹사명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "company_name", length = 300, nullable = false)
    private String companyName;

    /**
     * 기업그룹구분코드
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "company_type_code", length = 20, nullable = false)
    private String companyTypeCode;

    /**
     * 기업형태구분코드
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "company_form_code", length = 20, nullable = false)
    private String companyFormCode;

    /**
     * 대표자명
     */
    @NotNull
    @Size(max = 300)
    @Column(name = "representer_name", length = 300, nullable = false)
    private String representerName;

    /**
     * 사업자번호구분코드
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "business_number_type_code", length = 20, nullable = false)
    private String businessNumberTypeCode;

    /**
     * 사업자번호
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "business_number", length = 20, nullable = false)
    private String businessNumber;

    /**
     * 사업자등록증첨부문서UUID
     */
    @Size(max = 36)
    @Column(name = "business_number_attach_doc_uuid", length = 36)
    private String businessNumberAttachDocUuid;

    /**
     * 대표전화번호
     */
    @Size(max = 12)
    @Column(name = "represent_phone_number", length = 12)
    private String representPhoneNumber;

    /**
     * 홈페이지URL주소
     */
    @Size(max = 300)
    @Column(name = "homepage_url_address", length = 300)
    private String homepageUrlAddress;

    /**
     * 고객센터전화번호
     */
    @Size(max = 12)
    @Column(name = "call_center_phone_number", length = 12)
    private String callCenterPhoneNumber;

    /**
     * 담당자명
     */
    @Size(max = 300)
    @Column(name = "charger_name", length = 300)
    private String chargerName;

    /**
     * 담당자모바일전화번호
     */
    @Size(max = 12)
    @Column(name = "charger_mobile_phone_number", length = 12)
    private String chargerMobilePhoneNumber;

    /**
     * 담당자이메일주소
     */
    @Size(max = 100)
    @Column(name = "charger_email_address", length = 100)
    private String chargerEmailAddress;

    /**
     * 담당자부서명
     */
    @Size(max = 100)
    @Column(name = "charger_department_name", length = 100)
    private String chargerDepartmentName;

    /**
     * 담당자직책
     */
    @Size(max = 100)
    @Column(name = "charger_job_position", length = 100)
    private String chargerJobPosition;

    /**
     * 담당자직장전화번호
     */
    @Size(max = 12)
    @Column(name = "charger_job_phone_number", length = 12)
    private String chargerJobPhoneNumber;

    
    @ManyToOne
    @JsonIgnoreProperties(value = { "companyMasters", "attachDocHists", "contentsCatalogMasters" }, allowSetters = true)
    private AttachDocMaster attachDocMaster;

    @OneToMany(mappedBy = "companyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<UserMaster> userMasters ;

    @OneToMany(mappedBy = "companyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "attachDocMaster",
            "calculateCharge",
            "companyMaster",
            "customContentsInquiryMaster",
            "contentsBuyMasters",
            "contentsCategoryTypes",
            "contentsRelations",
            "contentsDetailMasters",
            "recommendContentsMasters",
            "contentsSaleApproveHists",
            "shoppingBasketMasters",
            "contentsInquiryMasters",
            "contentsStatisticMasters",
            "contentsSampleColumns",
            "contentsPurchaseRestricts",
        },
        allowSetters = true
    )
    private Set<ContentsCatalogMaster> contentsCatalogMasters ;

    @OneToMany(mappedBy = "companyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "companyMaster" }, allowSetters = true)
    private Set<AffiliateIntroduce> affiliateIntroduces ;

    @OneToMany(mappedBy = "companyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "companyMaster", "interfaceCallHists" }, allowSetters = true)
    private Set<InterfaceList> interfaceLists ;

    @OneToMany(mappedBy = "companyMaster")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "companyMaster" }, allowSetters = true)
    private Set<NewsInfo> newsInfos ;

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public CompanyMaster setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public AttachDocMaster getAttachDocMaster() {
        return this.attachDocMaster;
    }

    public void setAttachDocMaster(AttachDocMaster attachDocMaster) {
        this.attachDocMaster = attachDocMaster;
    }

    public CompanyMaster attachDocMaster(AttachDocMaster attachDocMaster) {
        this.setAttachDocMaster(attachDocMaster);
        return this;
    }

    public Set<UserMaster> getUserMasters() {
        return this.userMasters;
    }

    public void setUserMasters(Set<UserMaster> userMasters) {
        if (this.userMasters != null) {
            this.userMasters.forEach(i -> i.setCompanyMaster(null));
        }
        if (userMasters != null) {
            userMasters.forEach(i -> i.setCompanyMaster(this));
        }
        this.userMasters = userMasters;
    }

    public CompanyMaster userMasters(Set<UserMaster> userMasters) {
        this.setUserMasters(userMasters);
        return this;
    }

    public CompanyMaster addUserMaster(UserMaster userMaster) {
        this.userMasters.add(userMaster);
        userMaster.setCompanyMaster(this);
        return this;
    }

    public CompanyMaster removeUserMaster(UserMaster userMaster) {
        this.userMasters.remove(userMaster);
        userMaster.setCompanyMaster(null);
        return this;
    }

    public Set<ContentsCatalogMaster> getContentsCatalogMasters() {
        return this.contentsCatalogMasters;
    }

    public void setContentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        if (this.contentsCatalogMasters != null) {
            this.contentsCatalogMasters.forEach(i -> i.setCompanyMaster(null));
        }
        if (contentsCatalogMasters != null) {
            contentsCatalogMasters.forEach(i -> i.setCompanyMaster(this));
        }
        this.contentsCatalogMasters = contentsCatalogMasters;
    }

    public CompanyMaster contentsCatalogMasters(Set<ContentsCatalogMaster> contentsCatalogMasters) {
        this.setContentsCatalogMasters(contentsCatalogMasters);
        return this;
    }

    public CompanyMaster addContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.add(contentsCatalogMaster);
        contentsCatalogMaster.setCompanyMaster(this);
        return this;
    }

    public CompanyMaster removeContentsCatalogMaster(ContentsCatalogMaster contentsCatalogMaster) {
        this.contentsCatalogMasters.remove(contentsCatalogMaster);
        contentsCatalogMaster.setCompanyMaster(null);
        return this;
    }

    public Set<AffiliateIntroduce> getAffiliateIntroduces() {
        return this.affiliateIntroduces;
    }

    public void setAffiliateIntroduces(Set<AffiliateIntroduce> affiliateIntroduces) {
        if (this.affiliateIntroduces != null) {
            this.affiliateIntroduces.forEach(i -> i.setCompanyMaster(null));
        }
        if (affiliateIntroduces != null) {
            affiliateIntroduces.forEach(i -> i.setCompanyMaster(this));
        }
        this.affiliateIntroduces = affiliateIntroduces;
    }

    public CompanyMaster affiliateIntroduces(Set<AffiliateIntroduce> affiliateIntroduces) {
        this.setAffiliateIntroduces(affiliateIntroduces);
        return this;
    }

    public CompanyMaster addAffiliateIntroduce(AffiliateIntroduce affiliateIntroduce) {
        this.affiliateIntroduces.add(affiliateIntroduce);
        affiliateIntroduce.setCompanyMaster(this);
        return this;
    }

    public CompanyMaster removeAffiliateIntroduce(AffiliateIntroduce affiliateIntroduce) {
        this.affiliateIntroduces.remove(affiliateIntroduce);
        affiliateIntroduce.setCompanyMaster(null);
        return this;
    }

    public Set<InterfaceList> getInterfaceLists() {
        return this.interfaceLists;
    }

    public void setInterfaceLists(Set<InterfaceList> interfaceLists) {
        if (this.interfaceLists != null) {
            this.interfaceLists.forEach(i -> i.setCompanyMaster(null));
        }
        if (interfaceLists != null) {
            interfaceLists.forEach(i -> i.setCompanyMaster(this));
        }
        this.interfaceLists = interfaceLists;
    }

    public CompanyMaster interfaceLists(Set<InterfaceList> interfaceLists) {
        this.setInterfaceLists(interfaceLists);
        return this;
    }

    public CompanyMaster addInterfaceList(InterfaceList interfaceList) {
        this.interfaceLists.add(interfaceList);
        interfaceList.setCompanyMaster(this);
        return this;
    }

    public CompanyMaster removeInterfaceList(InterfaceList interfaceList) {
        this.interfaceLists.remove(interfaceList);
        interfaceList.setCompanyMaster(null);
        return this;
    }

    public Set<NewsInfo> getNewsInfos() {
        return this.newsInfos;
    }

    public void setNewsInfos(Set<NewsInfo> newsInfos) {
        if (this.newsInfos != null) {
            this.newsInfos.forEach(i -> i.setCompanyMaster(null));
        }
        if (newsInfos != null) {
            newsInfos.forEach(i -> i.setCompanyMaster(this));
        }
        this.newsInfos = newsInfos;
    }

    public CompanyMaster newsInfos(Set<NewsInfo> newsInfos) {
        this.setNewsInfos(newsInfos);
        return this;
    }

    public CompanyMaster addNewsInfo(NewsInfo newsInfo) {
        this.newsInfos.add(newsInfo);
        newsInfo.setCompanyMaster(this);
        return this;
    }

    public CompanyMaster removeNewsInfo(NewsInfo newsInfo) {
        this.newsInfos.remove(newsInfo);
        newsInfo.setCompanyMaster(null);
        return this;
    }



    }
