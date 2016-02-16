package lihu.zlm.web.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 产品集
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月3日 下午2:47:08
 * 
 */
public class Collect implements Serializable {

	private static final long serialVersionUID = -2048622308852729384L;

	/** 产品集主键 */
	private String collectid;

	/** 产品集名称 */
	private String collectName;

	/** 集封面路径 */
	private String coverImgPath;

	/** 隐藏封面路径 */
	private String coverImgPathHidden;

	/** 封面图片文件 */
	private MultipartFile coverImgFile;

	/** 背景颜色 */
	private String bgColor;

	/** 用户创建ID */
	private String userid;

	/** 产品总数 */
	private Integer productSum;

	/** 被收藏次数 */
	private Integer collectSum;

	/** 说明 */
	private String memo;

	/** 更新标志 */
	private String updateState;

	/** 更新时间 */
	private String updateTime;

	/** 创建时间 */
	private String createTime;

	/** 产品集状态 */
	private String collectState;

	private String recommend;

	/** 产品集赞数 */
	private Integer likeSum;

	/** 产品集赞度 */
	private Integer likeScore;

	/** 产品集反对数 */
	private Integer unLikeSum;

	/** 产品集反对度 */
	private Integer unLikeScore;

	/** 产品集评论数 */
	private Integer commentSum;

	private String detailContent;

	/** 创建用户信息 */
	private String nickName;

	public Collect() {
	}

	public String getCoverImgPath() {
		return coverImgPath;
	}

	public void setCoverImgPath(String coverImgPath) {
		this.coverImgPath = coverImgPath;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getProductSum() {
		return productSum;
	}

	public void setProductSum(Integer productSum) {
		this.productSum = productSum;
	}

	public Integer getCollectSum() {
		return collectSum;
	}

	public void setCollectSum(Integer collectSum) {
		this.collectSum = collectSum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUpdateState() {
		return updateState;
	}

	public void setUpdateState(String updateState) {
		this.updateState = updateState;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCollectid() {
		return collectid;
	}

	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}

	public String getCollectName() {
		return collectName;
	}

	public void setCollectName(String collectName) {
		this.collectName = collectName;
	}

	public String getCollectState() {
		return collectState;
	}

	public void setCollectState(String collectState) {
		this.collectState = collectState;
	}

	public MultipartFile getCoverImgFile() {
		return coverImgFile;
	}

	public void setCoverImgFile(MultipartFile coverImgFile) {
		this.coverImgFile = coverImgFile;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getCoverImgPathHidden() {
		return coverImgPathHidden;
	}

	public void setCoverImgPathHidden(String coverImgPathHidden) {
		this.coverImgPathHidden = coverImgPathHidden;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
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

	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
