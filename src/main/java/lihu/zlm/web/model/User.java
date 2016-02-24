package lihu.zlm.web.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 用户
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月15日 下午5:13:39
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户主键 */
	private String userid;

	/** 昵称 */
	private String nickName;

	/** 登录密码 */
	private String password;
	private String password1;
	private String password2;

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

	/** 产品集权限 */
	private String collectPermission;

	/** 第三方授权Token */
	private String accessToken;

	/** 第三方授权expireIn */
	private String tokenExpireIn;

	/** 第三方授权Openid */
	private String openid;

	/** 第三方平台类型 */
	private String loginType;

	/** 微信服务号授权返回 */
	private String unionid;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
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

	public String getCollectPermission() {
		return collectPermission;
	}

	public void setCollectPermission(String collectPermission) {
		this.collectPermission = collectPermission;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenExpireIn() {
		return tokenExpireIn;
	}

	public void setTokenExpireIn(String tokenExpireIn) {
		this.tokenExpireIn = tokenExpireIn;
	}

	public String getOpenid() {
		return openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}

}
