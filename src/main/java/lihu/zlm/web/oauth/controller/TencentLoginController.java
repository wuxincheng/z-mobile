package lihu.zlm.web.oauth.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import lihu.zlm.web.model.User;
import lihu.zlm.web.oauth.helper.TencentHttpsHelper;
import lihu.zlm.web.service.UserService;
import lihu.zlm.util.Constants;

/**
 * QQ授权登录
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年7月14日 下午5:46:49
 * 
 */
@Controller
@RequestMapping("/top/oauth/qq")
public class TencentLoginController {
	private static final Logger logger = LoggerFactory.getLogger(TencentLoginController.class);

	@Resource
	private UserService userService;

	@Resource
	private TencentHttpsHelper tencentHttpsHelper;

	/**
	 * 跳转到QQ登录的授权页面
	 */
	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");

		try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (Exception e) {
			logger.error("连接登录QQ异常", e);
		}
	}

	/**
	 * 用户授权后QQ后台返回信息
	 */
	@RequestMapping(value = "/afterLogin")
	public String afterLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("接收QQ后台用户登录信息返回");
		response.setContentType("text/html; charset=utf-8");
		User oauthUser = null;

		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

			if (StringUtils.isNotEmpty(accessTokenObj.getAccessToken())) {
				logger.info("QQ用户登录授权信息 accessToken={}，tokenExpireIn={}", accessTokenObj.getAccessToken(),
						accessTokenObj.getExpireIn());

				OpenID openIDObj = new OpenID(accessTokenObj.getAccessToken());
				logger.info("QQ用户登录授权信息 openID={}", openIDObj.getUserOpenID());

				// 根据AccessToken和UserOpenID获取用户信息
				Map<String, Object> userInfoMap = tencentHttpsHelper.getUserInfo(accessTokenObj.getAccessToken(),
						openIDObj.getUserOpenID());

				if (null == userInfoMap) {
					model.addAttribute(Constants.MSG_ERROR, "授权失败：qq信息返回为空");
					return "redirect:/login/";
				}

				if ((Integer) userInfoMap.get("ret") == 0) { // 正确返回
					oauthUser = new User();

					oauthUser.setNickName(userInfoMap.get("nickname").toString());
					oauthUser.setSocialPicPath(userInfoMap.get("figureurl_qq_1").toString());
					oauthUser.setAccessToken(accessTokenObj.getAccessToken());
					oauthUser.setTokenExpireIn(accessTokenObj.getExpireIn() + "");
					oauthUser.setOpenid(openIDObj.getUserOpenID());
					oauthUser.setLoginType(Constants.OAUTH_QQ); // 用户类型

					checkAndProcessOAuthUser(oauthUser, request);
					logger.info("QQ用户登录授权信息已处理");
				} else {
					logger.warn("获取QQ登录信息异常 原因：{}", userInfoMap.get("msg"));
				}
			} else {
				// 我们的网站被CSRF攻击了或者用户取消了授权
				// 做一些数据统计工作
				logger.info("QQ用户登录没有获取到响应参数");
			}
		} catch (QQConnectException e) {
			logger.error("QQ连接异常", e);
			model.addAttribute(Constants.MSG_ERROR, "授权失败：用户QQ连接异常");
			return "redirect:/login/";
		}

		if (oauthUser == null) {
			logger.warn("用户授权失败");
			model.addAttribute(Constants.MSG_ERROR, "用户授权失败");
			return "redirect:/login/";
		} else {
			logger.info("用户授权成功");
			model.addAttribute(Constants.MSG_SUCCESS, "用户授权成功");
			return "redirect:/index";
		}
	}

	/**
	 * 处理用户登录信息
	 */
	private void checkAndProcessOAuthUser(User oauthUser, HttpServletRequest request) {
		// 验证是否已经在库中有记录，如果有记录更新，没记录新增
		User checkUser = userService.validateOAuthUser(oauthUser);

		// 用户信息放入在Session中
		request.getSession().setAttribute(Constants.CURRENT_USER, checkUser);
		logger.info("授权成功");
	}

}
