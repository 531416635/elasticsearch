package com.zto.entity;
import java.math.BigDecimal;
import java.util.Date;


public class BillStatus {
    private String billCode;

    private String useSite;

    private Long useSiteId;

    private String recordSite;

    private Long recordSiteId;

    private Date recordDate;

    private BigDecimal recordWeight ;

    private String recSite;

    private Long recSiteId ;

    private Date recDate;

    private String firScanSite;

    private Long firScanSiteId;

    private Date firScanDate;

    private String firScanType;

    private BigDecimal firWeight;

    private String recMan;

    private String recManCode;

    private String recCustomer;

    private String presSite;

    private Long presSiteId;

    private Date presScanDate;

    private String presScanType;

    private String presContent;

    private BigDecimal presWeight;

    private String firPreSite;

    private Long firPreId;

    private String firComeSite;

    private Long firComeId;

    private Date firComeDate;

    private String lastSendSite;

    private Long lastSendId;

    private Date lastSendDate;

    private String lastSendNext;

    private Long lastNextId;

    private Short blProb;

    private Date probDate;

    private String probRegiSite;

    private Long probRegiId;

    private String lastContSite;

    private Long lastContId;

    private Date lastContDate;

    private String lastContType;

    private String lastMan;

    private String lastManCode;

    private Short blSign ;

    private String signSite;

    private Long signSiteId;

    private Date signDate;

    private String maxScanType;

    private BigDecimal maxWeight;

    private BigDecimal befoOneWeight;

    private Short blBag ;

    private Short blDisp ;

    private Short billType;

    private Date createDate;

    private Short elecType ;

    private String useMan;

    private String useManCode;

    private String useCustomerCode;

    private String useCustomerName;

    private Long useCustomerId;

    private String recCustomerCode;

    private Long recCustomerId;

    private Short blTopayment;

    private Short blCod;

    private Short blPrice;

    private Short blDeliver;

    private Date dispDate;

    private String dispSite;

    private Long dispSiteId;

    private String signMan;

    private Short blProbRe;

    private Short blReturn;

    private Date returnDate;

    private Date signUpDate;
    
    private String index;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode == null ? null : billCode.trim();
    }

    public String getUseSite() {
        return useSite;
    }

    public void setUseSite(String useSite) {
        this.useSite = useSite == null ? null : useSite.trim();
    }

    public Long getUseSiteId() {
        return useSiteId==null?0l:useSiteId;
    }

    public void setUseSiteId(Long useSiteId) {
        this.useSiteId = useSiteId;
    }

    public String getRecordSite() {
        return recordSite;
    }

    public void setRecordSite(String recordSite) {
        this.recordSite = recordSite == null ? null : recordSite.trim();
    }

    public Long getRecordSiteId() {
		return recordSiteId;
	}

	public void setRecordSiteId(Long recordSiteId) {
		this.recordSiteId = recordSiteId;
	}

	public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public BigDecimal getRecordWeight() {
        return recordWeight==null?new BigDecimal(0):recordWeight;
    }

    public void setRecordWeight(BigDecimal recordWeight) {
        this.recordWeight = recordWeight;
    }

    public String getRecSite() {
        return recSite;
    }

    public void setRecSite(String recSite) {
        this.recSite = recSite == null ? null : recSite.trim();
    }

    public Long getRecSiteId() {
        return recSiteId==null?0l:recSiteId;
    }

    public void setRecSiteId(Long recSiteId) {
        this.recSiteId = recSiteId;
    }

    public Date getRecDate() {
        return recDate;
    }

    public void setRecDate(Date recDate) {
        this.recDate = recDate;
    }

    public String getFirScanSite() {
        return firScanSite;
    }

    public void setFirScanSite(String firScanSite) {
        this.firScanSite = firScanSite == null ? null : firScanSite.trim();
    }

    public Long getFirScanSiteId() {
        return firScanSiteId==null?0l:firScanSiteId;
    }

    public void setFirScanSiteId(Long firScanSiteId) {
        this.firScanSiteId = firScanSiteId;
    }

    public Date getFirScanDate() {
        return firScanDate;
    }

    public void setFirScanDate(Date firScanDate) {
        this.firScanDate = firScanDate;
    }

    public String getFirScanType() {
        return firScanType;
    }

    public void setFirScanType(String firScanType) {
        this.firScanType = firScanType == null ? null : firScanType.trim();
    }

    public BigDecimal getFirWeight() {
        return firWeight==null?new BigDecimal(0):firWeight;
    }

    public void setFirWeight(BigDecimal firWeight) {
        this.firWeight = firWeight;
    }

    public String getRecMan() {
        return recMan;
    }

    public void setRecMan(String recMan) {
        this.recMan = recMan == null ? null : recMan.trim();
    }

    public String getRecManCode() {
        return recManCode;
    }

    public void setRecManCode(String recManCode) {
        this.recManCode = recManCode == null ? null : recManCode.trim();
    }

    public String getRecCustomer() {
        return recCustomer;
    }

    public void setRecCustomer(String recCustomer) {
        this.recCustomer = recCustomer == null ? null : recCustomer.trim();
    }

    public String getPresSite() {
        return presSite;
    }

    public void setPresSite(String presSite) {
        this.presSite = presSite == null ? null : presSite.trim();
    }

    public Long getPresSiteId() {
        return presSiteId==null?0l:presSiteId;
    }

    public void setPresSiteId(Long presSiteId) {
        this.presSiteId = presSiteId;
    }

    public Date getPresScanDate() {
        return presScanDate;
    }

    public void setPresScanDate(Date presScanDate) {
        this.presScanDate = presScanDate;
    }

    public String getPresScanType() {
        return presScanType;
    }

    public void setPresScanType(String presScanType) {
        this.presScanType = presScanType == null ? null : presScanType.trim();
    }

    public String getPresContent() {
        return presContent;
    }

    public void setPresContent(String presContent) {
        this.presContent = presContent == null ? null : presContent.trim();
    }

    public BigDecimal getPresWeight() {
        return presWeight==null?new BigDecimal(0):presWeight;
    }

    public void setPresWeight(BigDecimal presWeight) {
        this.presWeight = presWeight;
    }

    public String getFirPreSite() {
        return firPreSite;
    }

    public void setFirPreSite(String firPreSite) {
        this.firPreSite = firPreSite == null ? null : firPreSite.trim();
    }

    public Long getFirPreId() {
        return firPreId==null?0l:firPreId;
    }

    public void setFirPreId(Long firPreId) {
        this.firPreId = firPreId;
    }

    public String getFirComeSite() {
        return firComeSite;
    }

    public void setFirComeSite(String firComeSite) {
        this.firComeSite = firComeSite == null ? null : firComeSite.trim();
    }

    public Long getFirComeId() {
        return firComeId==null?0l:firComeId;
    }

    public void setFirComeId(Long firComeId) {
        this.firComeId = firComeId;
    }

    public Date getFirComeDate() {
        return firComeDate;
    }

    public void setFirComeDate(Date firComeDate) {
        this.firComeDate = firComeDate;
    }

    public String getLastSendSite() {
        return lastSendSite;
    }

    public void setLastSendSite(String lastSendSite) {
        this.lastSendSite = lastSendSite == null ? null : lastSendSite.trim();
    }

    public Long getLastSendId() {
        return lastSendId==null?0l:lastSendId;
    }

    public void setLastSendId(Long lastSendId) {
        this.lastSendId = lastSendId;
    }

    public Date getLastSendDate() {
        return lastSendDate;
    }

    public void setLastSendDate(Date lastSendDate) {
        this.lastSendDate = lastSendDate;
    }

    public String getLastSendNext() {
        return lastSendNext;
    }

    public void setLastSendNext(String lastSendNext) {
        this.lastSendNext = lastSendNext == null ? null : lastSendNext.trim();
    }

    public Long getLastNextId() {
        return lastNextId==null?0l:lastNextId;
    }

    public void setLastNextId(Long lastNextId) {
        this.lastNextId = lastNextId;
    }

    public Short getBlProb() {
        return blProb==null?0:blProb;
    }

    public void setBlProb(Short blProb) {
        this.blProb = blProb;
    }

    public Date getProbDate() {
        return probDate;
    }

    public void setProbDate(Date probDate) {
        this.probDate = probDate;
    }

    public String getProbRegiSite() {
        return probRegiSite;
    }

    public void setProbRegiSite(String probRegiSite) {
        this.probRegiSite = probRegiSite == null ? null : probRegiSite.trim();
    }

    public Long getProbRegiId() {
        return probRegiId==null?0l:probRegiId;
    }

    public void setProbRegiId(Long probRegiId) {
        this.probRegiId = probRegiId;
    }

    public String getLastContSite() {
        return lastContSite;
    }

    public void setLastContSite(String lastContSite) {
        this.lastContSite = lastContSite == null ? null : lastContSite.trim();
    }

    public Long getLastContId() {
        return lastContId==null?0l:lastContId;
    }

    public void setLastContId(Long lastContId) {
        this.lastContId = lastContId;
    }

    public Date getLastContDate() {
        return lastContDate;
    }

    public void setLastContDate(Date lastContDate) {
        this.lastContDate = lastContDate;
    }

    public String getLastContType() {
        return lastContType;
    }

    public void setLastContType(String lastContType) {
        this.lastContType = lastContType == null ? null : lastContType.trim();
    }

    public String getLastMan() {
        return lastMan;
    }

    public void setLastMan(String lastMan) {
        this.lastMan = lastMan == null ? null : lastMan.trim();
    }

    public String getLastManCode() {
        return lastManCode;
    }

    public void setLastManCode(String lastManCode) {
        this.lastManCode = lastManCode == null ? null : lastManCode.trim();
    }

    public Short getBlSign() {
        return blSign==null?0:blSign;
    }

    public void setBlSign(Short blSign) {
        this.blSign = blSign;
    }

    public String getSignSite() {
        return signSite;
    }

    public void setSignSite(String signSite) {
        this.signSite = signSite == null ? null : signSite.trim();
    }

    public Long getSignSiteId() {
        return signSiteId==null?0l:signSiteId;
    }

    public void setSignSiteId(Long signSiteId) {
        this.signSiteId = signSiteId;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getMaxScanType() {
        return maxScanType;
    }

    public void setMaxScanType(String maxScanType) {
        this.maxScanType = maxScanType == null ? null : maxScanType.trim();
    }

    public BigDecimal getMaxWeight() {
        return maxWeight==null?new BigDecimal(0):maxWeight;
    }

    public void setMaxWeight(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    public BigDecimal getBefoOneWeight() {
        return befoOneWeight==null?new BigDecimal(0):befoOneWeight;
    }

    public void setBefoOneWeight(BigDecimal befoOneWeight) {
        this.befoOneWeight = befoOneWeight;
    }

    public Short getBlBag() {
        return blBag==null?0:blBag;
    }

    public void setBlBag(Short blBag) {
        this.blBag = blBag;
    }

    public Short getBlDisp() {
        return blDisp==null?0:blDisp;
    }

    public void setBlDisp(Short blDisp) {
        this.blDisp = blDisp;
    }

    public Short getBillType() {
        return billType==null?0:billType;
    }

    public void setBillType(Short billType) {
        this.billType = billType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getElecType() {
        return elecType==null?0:elecType;
    }

    public void setElecType(Short elecType) {
        this.elecType = elecType;
    }

    public String getUseMan() {
        return useMan;
    }

    public void setUseMan(String useMan) {
        this.useMan = useMan == null ? null : useMan.trim();
    }

    public String getUseManCode() {
        return useManCode;
    }

    public void setUseManCode(String useManCode) {
        this.useManCode = useManCode == null ? null : useManCode.trim();
    }

    public String getUseCustomerCode() {
        return useCustomerCode;
    }

    public void setUseCustomerCode(String useCustomerCode) {
        this.useCustomerCode = useCustomerCode == null ? null : useCustomerCode.trim();
    }

    public String getUseCustomerName() {
        return useCustomerName;
    }

    public void setUseCustomerName(String useCustomerName) {
        this.useCustomerName = useCustomerName == null ? null : useCustomerName.trim();
    }

    public Long getUseCustomerId() {
        return useCustomerId;
    }

    public void setUseCustomerId(Long useCustomerId) {
        this.useCustomerId = useCustomerId;
    }

    public String getRecCustomerCode() {
        return recCustomerCode;
    }

    public void setRecCustomerCode(String recCustomerCode) {
        this.recCustomerCode = recCustomerCode == null ? null : recCustomerCode.trim();
    }

    public Long getRecCustomerId() {
        return recCustomerId;
    }

    public void setRecCustomerId(Long recCustomerId) {
        this.recCustomerId = recCustomerId;
    }

    public Short getBlTopayment() {
        return blTopayment;
    }

    public void setBlTopayment(Short blTopayment) {
        this.blTopayment = blTopayment;
    }

    public Short getBlCod() {
        return blCod;
    }

    public void setBlCod(Short blCod) {
        this.blCod = blCod;
    }

    public Short getBlPrice() {
        return blPrice;
    }

    public void setBlPrice(Short blPrice) {
        this.blPrice = blPrice;
    }

    public Short getBlDeliver() {
        return blDeliver;
    }

    public void setBlDeliver(Short blDeliver) {
        this.blDeliver = blDeliver;
    }

    public Date getDispDate() {
        return dispDate;
    }

    public void setDispDate(Date dispDate) {
        this.dispDate = dispDate;
    }

    public String getDispSite() {
        return dispSite;
    }

    public void setDispSite(String dispSite) {
        this.dispSite = dispSite == null ? null : dispSite.trim();
    }

    public Long getDispSiteId() {
        return dispSiteId;
    }

    public void setDispSiteId(Long dispSiteId) {
        this.dispSiteId = dispSiteId;
    }

    public String getSignMan() {
        return signMan;
    }

    public void setSignMan(String signMan) {
        this.signMan = signMan == null ? null : signMan.trim();
    }

    public Short getBlProbRe() {
        return blProbRe;
    }

    public void setBlProbRe(Short blProbRe) {
        this.blProbRe = blProbRe;
    }

    public Short getBlReturn() {
        return blReturn;
    }

    public void setBlReturn(Short blReturn) {
        this.blReturn = blReturn;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
    
}