package lihu.zlm.web.model;

/**
 * 基金信息表
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月13日 上午10:41:01
 * 
 */
public class FundInfo {

	/** 基金名称 */
	private String fundName;

	/** 基金代码 */
	private String fundCode;

	/** 基金经理 */
	private String fundManager;

	/** 基金TA */
	private String fundTACode;

	/** 基金公司 */
	private String fundCompany;

	/** 基金成立日期 */
	private String foundedDate;

	/** 基金发行日期 */
	private String issueDate;

	/** 基金类别：普通基金、股票等 */
	private String fundType;

	/** 基金风险级别 */
	private String fundRiskLevel;

	/** 最小购买金额 */
	private String minSubscribe;

	/** 费率 */
	private String manageRate;

	/** 最新规模 */
	private String newScale;

	/** 基金状态 */
	private String fundState;

	/** 系统录入时间 */
	private String createTime;

	/** 系统修改时间 */
	private String updateTime;

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

	public String getFundManager() {
		return fundManager;
	}

	public void setFundManager(String fundManager) {
		this.fundManager = fundManager;
	}

	public String getFundTACode() {
		return fundTACode;
	}

	public void setFundTACode(String fundTACode) {
		this.fundTACode = fundTACode;
	}

	public String getFundCompany() {
		return fundCompany;
	}

	public void setFundCompany(String fundCompany) {
		this.fundCompany = fundCompany;
	}

	public String getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(String foundedDate) {
		this.foundedDate = foundedDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFundRiskLevel() {
		return fundRiskLevel;
	}

	public void setFundRiskLevel(String fundRiskLevel) {
		this.fundRiskLevel = fundRiskLevel;
	}

	public String getMinSubscribe() {
		return minSubscribe;
	}

	public void setMinSubscribe(String minSubscribe) {
		this.minSubscribe = minSubscribe;
	}

	public String getManageRate() {
		return manageRate;
	}

	public void setManageRate(String manageRate) {
		this.manageRate = manageRate;
	}

	public String getFundState() {
		return fundState;
	}

	public void setFundState(String fundState) {
		this.fundState = fundState;
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

	public String getNewScale() {
		return newScale;
	}

	public void setNewScale(String newScale) {
		this.newScale = newScale;
	}

}
