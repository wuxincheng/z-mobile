package lihu.zlm.web.model;

import java.io.Serializable;

/**
 * 用户收藏产品集
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月24日 下午2:06:08
 * 
 */
public class CollectUser implements Serializable {

	private static final long serialVersionUID = -361294005914978730L;

	private String collectid;
	
	private String fundCode;

	private String userid;

	private String createTime;

	private String collectState;

	public String getCollectid() {
		return collectid;
	}

	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCollectState() {
		return collectState;
	}

	public void setCollectState(String collectState) {
		this.collectState = collectState;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

}
