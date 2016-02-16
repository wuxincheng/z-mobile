package lihu.zlm.web.model;

import java.io.Serializable;

/**
 * 基金行情
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月13日 上午10:40:40
 * 
 */
public class FundMarket implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 行情日期 */
	private String marketDate;

	/** 基金名称 */
	private String fundName;

	/** 基金代码 */
	private String fundCode;

	/** 基金TA代码 */
	private String fundTACode;

	/** 基金公司 */
	private String fundCompany;

	/** 基金类型 */
	private String fundType;

	/** 基金成立日期 */
	private String foundedDate;

	/** 基金风险级别 */
	private String fundRiskLevel;

	/** 基金经理 */
	private String fundManager;

	/** 基金净值(如果是货币基金, 则为7日年化收益率) */
	private String currentNav;

	/** 基金当日状态 */
	private String currentState;

	/** 基金收益 */
	private String fundIncome;

	/** 基金收益率 */
	private String fundIncomeRate;

	/** 净值日期 */
	private String navDate;

	/** 累计单位净值 */
	private String totalNav;

	/** 近一周涨幅 */
	private String fundRiseWeek;

	/** 近一个月涨幅 */
	private String fundRiseMonth;

	/** 近三个月涨幅 */
	private String fundRiseThreeMonth;

	/** 近半年涨幅 */
	private String fundRiseHalfYear;

	/** 近一年涨幅 */
	private String fundRiseYear;

	/** 今年涨幅 */
	private String fundRiseThisYear;

	/** 基金最新规模 */
	private String newScale;

	/** 基金涨跌幅 */
	private String rateChange;

	/** 基金近3月排名 */
	private String fundSortThreeMonth;
	private String fundTotalThreeMonth;

	/** 创建时间 */
	private String createTime;

	/** 更新时间 */
	private String updateTime;

	/** 产品赞数 */
	private Integer likeSum;

	/** 产品赞度 */
	private Integer likeScore;

	/** 产品反对数 */
	private Integer unLikeSum;

	/** 产品反对度 */
	private Integer unLikeScore;

	/** 产品评论数 */
	private Integer commentSum;

	/** 产品关注人数 */
	private Integer focusSum;

	/** 产品关注度 */
	private Integer focusScore;

	/** 临时字段 */
	private String fundMemo;
	
	
	private String thisUserFocus;
	private String prodid;

	public String getFundMemo() {
		return fundMemo;
	}

	public void setFundMemo(String fundMemo) {
		this.fundMemo = fundMemo;
	}

	public String getMarketDate() {
		return marketDate;
	}

	public void setMarketDate(String marketDate) {
		this.marketDate = marketDate;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundTACode() {
		return fundTACode;
	}

	public void setFundTACode(String fundTACode) {
		this.fundTACode = fundTACode;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(String foundedDate) {
		this.foundedDate = foundedDate;
	}

	public String getFundRiskLevel() {
		return fundRiskLevel;
	}

	public void setFundRiskLevel(String fundRiskLevel) {
		this.fundRiskLevel = fundRiskLevel;
	}

	public String getFundManager() {
		return fundManager;
	}

	public void setFundManager(String fundManager) {
		this.fundManager = fundManager;
	}

	public String getCurrentNav() {
		return currentNav;
	}

	public void setCurrentNav(String currentNav) {
		this.currentNav = currentNav;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getFundIncome() {
		return fundIncome;
	}

	public void setFundIncome(String fundIncome) {
		this.fundIncome = fundIncome;
	}

	public String getFundIncomeRate() {
		return fundIncomeRate;
	}

	public void setFundIncomeRate(String fundIncomeRate) {
		this.fundIncomeRate = fundIncomeRate;
	}

	public String getNavDate() {
		return navDate;
	}

	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}

	public String getTotalNav() {
		return totalNav;
	}

	public void setTotalNav(String totalNav) {
		this.totalNav = totalNav;
	}

	public String getFundRiseWeek() {
		return fundRiseWeek;
	}

	public void setFundRiseWeek(String fundRiseWeek) {
		this.fundRiseWeek = fundRiseWeek;
	}

	public String getFundRiseMonth() {
		return fundRiseMonth;
	}

	public void setFundRiseMonth(String fundRiseMonth) {
		this.fundRiseMonth = fundRiseMonth;
	}

	public String getFundRiseThreeMonth() {
		return fundRiseThreeMonth;
	}

	public void setFundRiseThreeMonth(String fundRiseThreeMonth) {
		this.fundRiseThreeMonth = fundRiseThreeMonth;
	}

	public String getFundRiseHalfYear() {
		return fundRiseHalfYear;
	}

	public void setFundRiseHalfYear(String fundRiseHalfYear) {
		this.fundRiseHalfYear = fundRiseHalfYear;
	}

	public String getFundRiseYear() {
		return fundRiseYear;
	}

	public void setFundRiseYear(String fundRiseYear) {
		this.fundRiseYear = fundRiseYear;
	}

	public String getFundRiseThisYear() {
		return fundRiseThisYear;
	}

	public void setFundRiseThisYear(String fundRiseThisYear) {
		this.fundRiseThisYear = fundRiseThisYear;
	}

	public String getNewScale() {
		return newScale;
	}

	public void setNewScale(String newScale) {
		this.newScale = newScale;
	}

	public String getRateChange() {
		return rateChange;
	}

	public void setRateChange(String rateChange) {
		this.rateChange = rateChange;
	}

	public String getFundSortThreeMonth() {
		return fundSortThreeMonth;
	}

	public void setFundSortThreeMonth(String fundSortThreeMonth) {
		this.fundSortThreeMonth = fundSortThreeMonth;
	}

	public String getFundTotalThreeMonth() {
		return fundTotalThreeMonth;
	}

	public void setFundTotalThreeMonth(String fundTotalThreeMonth) {
		this.fundTotalThreeMonth = fundTotalThreeMonth;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getFundCompany() {
		return fundCompany;
	}

	public void setFundCompany(String fundCompany) {
		this.fundCompany = fundCompany;
	}

	public Integer getLikeSum() {
		return likeSum;
	}

	public void setLikeSum(Integer likeSum) {
		this.likeSum = likeSum;
	}

	public Integer getLikeScore() {
		return likeScore;
	}

	public void setLikeScore(Integer likeScore) {
		this.likeScore = likeScore;
	}

	public Integer getUnLikeSum() {
		return unLikeSum;
	}

	public void setUnLikeSum(Integer unLikeSum) {
		this.unLikeSum = unLikeSum;
	}

	public Integer getUnLikeScore() {
		return unLikeScore;
	}

	public void setUnLikeScore(Integer unLikeScore) {
		this.unLikeScore = unLikeScore;
	}

	public Integer getCommentSum() {
		return commentSum;
	}

	public void setCommentSum(Integer commentSum) {
		this.commentSum = commentSum;
	}

	public Integer getFocusSum() {
		return focusSum;
	}

	public void setFocusSum(Integer focusSum) {
		this.focusSum = focusSum;
	}

	public Integer getFocusScore() {
		return focusScore;
	}

	public void setFocusScore(Integer focusScore) {
		this.focusScore = focusScore;
	}

	public String getThisUserFocus() {
		return thisUserFocus;
	}

	public void setThisUserFocus(String thisUserFocus) {
		this.thisUserFocus = thisUserFocus;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

}
