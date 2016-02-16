package lihu.zlm.web.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 理财师
 * 
 * @author wuxincheng(wxcking)
 * 
 * @Date 2016年1月27日 下午7:48:48
 * 
 */
public class Advisor implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户主键 */
	private String userid;

	/** 昵称 */
	private String nickName;

	/** 登录邮箱 */
	private String loginEmail;

	/** 自定义头像 */
	private String picPath;

	private MultipartFile avatarFile;

	private String sex;

	/** 社交平台头像 */
	private String socialPicPath;

	/** 个人简介 */
	private String memo;

	/** 用户组 */
	private String userGroup;

	/** 用户职位 */
	private String position;

	/** 用户状态 */
	private String userState;

	private Integer collectSum;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSocialPicPath() {
		return socialPicPath;
	}

	public void setSocialPicPath(String socialPicPath) {
		this.socialPicPath = socialPicPath;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public Integer getCollectSum() {
		return collectSum;
	}

	public void setCollectSum(Integer collectSum) {
		this.collectSum = collectSum;
	}

}
