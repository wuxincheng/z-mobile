package lihu.zlm.web.model;

import java.io.Serializable;

/**
 * 产品(评论和基金组成)
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月25日 下午7:05:27
 * 
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 产品主键 */
	private String prodid;

	/** 产品名称 */
	private String prodName;

	/** 产品管网 */
	private String prodUrl;

	/** 基金名称 */
	private String fundName;
	/** 基金代码 */
	private String fundCode;

	/** 产品说明 */
	private String memo;

	/** 产品赞数 */
	private Integer likeSum;

	/** 产品评论数 */
	private Integer commentSum;

	/** 产品状态：默认：0-正常 */
	private String prodState;

	/** 产品发布日期 */
	private String postDate;

	/** 产品操作时间 */
	private String postDateTime;

	private String userid;

	/** 产品集主键 */
	private String collectid;

	/** 产品关注度 */
	private String score;

	// ====== 新增字段 start ======
	/** 最新价 */
	private String latestPrice;
	/** 涨跌幅 */
	private String changePercent;
	/** 成本价 */
	private String costPrice;
	/** 盈亏比例 */
	private String profitLossRat;
	/** 仓位 */
	private String positionPercent;
	/** 手动录入数据标志 */
	private String manulFlag;
	// ====== 新增字段 end ======

	// ===== 用户信息 =====
	private String nickName;

	private String loginEmail;

	private String picPath;

	private String socialPicPath;

	private String userMemo;

	private String userGroup;

	private String position;

	// ====== 用户赞 ======
	private String likeState;

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdUrl() {
		return prodUrl;
	}

	public void setProdUrl(String prodUrl) {
		this.prodUrl = prodUrl;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getLikeSum() {
		return likeSum;
	}

	public void setLikeSum(Integer likeSum) {
		this.likeSum = likeSum;
	}

	public Integer getCommentSum() {
		return commentSum;
	}

	public void setCommentSum(Integer commentSum) {
		this.commentSum = commentSum;
	}

	public String getProdState() {
		return prodState;
	}

	public void setProdState(String prodState) {
		this.prodState = prodState;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostDateTime() {
		return postDateTime;
	}

	public void setPostDateTime(String postDateTime) {
		this.postDateTime = postDateTime;
	}

	public String getUserid() {
		return userid;
	}

	public String getLatestPrice() {
		return latestPrice;
	}

	public void setLatestPrice(String latestPrice) {
		this.latestPrice = latestPrice;
	}

	public String getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getProfitLossRat() {
		return profitLossRat;
	}

	public void setProfitLossRat(String profitLossRat) {
		this.profitLossRat = profitLossRat;
	}

	public String getPositionPercent() {
		return positionPercent;
	}

	public void setPositionPercent(String positionPercent) {
		this.positionPercent = positionPercent;
	}

	public String getManulFlag() {
		return manulFlag;
	}

	public void setManulFlag(String manulFlag) {
		this.manulFlag = manulFlag;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCollectid() {
		return collectid;
	}

	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getSocialPicPath() {
		return socialPicPath;
	}

	public void setSocialPicPath(String socialPicPath) {
		this.socialPicPath = socialPicPath;
	}

	public String getUserMemo() {
		return userMemo;
	}

	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getLikeState() {
		return likeState;
	}

	public void setLikeState(String likeState) {
		this.likeState = likeState;
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

}
