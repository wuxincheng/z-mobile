package lihu.zlm.web.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lihu.zlm.web.dao.UserDao;
import lihu.zlm.web.model.User;
import lihu.zlm.util.Constants;

@Service("userService")
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserDao userDao;

	/**
	 * 根据邮箱查询用户
	 */
	public User checkLogin(String loginEmail) {
		logger.info("根据邮箱查询用户 loginEmail={}", loginEmail);
		return userDao.queryByLoginEmail(loginEmail);
	}

	/**
	 * 新增用户
	 */
	public void register(User user) {
		logger.info("新增用户");
		user.setUserState(Constants.DEFAULT_STATE);
		user.setCollectPermission(Constants.DEFAULT_STATE);
		userDao.register(user);
	}

	/**
	 * 验证授权登录用户
	 */
	public User validateOAuthUser(User oauthUser) {
		logger.info("验证授权登录用户");

		logger.info("根据授权Openid查询用户信息");
		User queryOAuthUser = userDao.queryByOAuthOpenid(oauthUser.getOpenid());

		if (queryOAuthUser == null) {
			logger.info("未查询这该用户授权登录信息");
			// 记录这条信息
			this.register(oauthUser);
			logger.info("用户授权登录信息已添加");

			// 再查询一次
			queryOAuthUser = userDao.queryByOAuthOpenid(oauthUser.getOpenid());
		} else {
			logger.info("已查询这该用户授权登录信息");
			// 更新这条信息
			queryOAuthUser.setNickName(oauthUser.getNickName());
			queryOAuthUser.setSocialPicPath(oauthUser.getSocialPicPath());
			queryOAuthUser.setAccessToken(oauthUser.getAccessToken());
			queryOAuthUser.setTokenExpireIn(oauthUser.getTokenExpireIn());
			queryOAuthUser.setSex(oauthUser.getSex());
			queryOAuthUser.setUnionid(oauthUser.getUnionid());

			userDao.updateInfo(queryOAuthUser);
			logger.info("用户授权登录信息已更新");
		}

		return queryOAuthUser;
	}

	/**
	 * 更新用户信息
	 */
	public void updateInfo(User user) {
		logger.info("更新用户信息");

		// 根据用户主键查询用户信息是否存在
		User updateUser = userDao.queryByUserid(user.getUserid());
		if (updateUser != null) {
			updateUser.setNickName(user.getNickName());
			updateUser.setMemo(user.getMemo());
			updateUser.setUserGroup(user.getUserGroup());
			updateUser.setPosition(user.getPosition());
			updateUser.setPicPath(user.getPicPath());
			userDao.updateInfo(updateUser);
		}
	}

	/**
	 * 根据用户主键查询用户信息
	 */
	public User queryByUserid(String userid) {
		if (StringUtils.isEmpty(userid)) {
			return null;
		}
		return userDao.queryByUserid(userid);
	}

	/**
	 * 修改登录密码
	 */
	public void changePassword(User user) {
		userDao.changePassword(user);
	}

}
