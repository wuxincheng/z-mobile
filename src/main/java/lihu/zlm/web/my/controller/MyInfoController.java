package lihu.zlm.web.my.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lihu.zlm.web.controller.BaseController;
import lihu.zlm.web.model.User;
import lihu.zlm.web.service.UserService;
import lihu.zlm.util.Constants;
import lihu.zlm.util.ImageUtil;
import lihu.zlm.util.MD5;

/**
 * 个人中心：个人信息
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月3日 下午5:38:32 
 *
 */
@Controller
@RequestMapping("/my/info")
public class MyInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MyInfoController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/query")
	public String query(Model model, HttpServletRequest request) {
		logger.info("显示个人信息页");
		
		requestMessageProcess(request);
		
		return "my/info";
	}
	
	@RequestMapping(value = "/modify")
	public String modify(Model model, HttpServletRequest request, User user) {
		logger.info("修改个人信息");
		
		// 获取登录用户的信息
		User sessionInfo = getCurrentUser(request);
		
		// 验证
		String responseValidateMsg = validateUpdateInfo(user, sessionInfo);
		if (StringUtils.isNotEmpty(responseValidateMsg)) {
			logger.debug("修改信息不合法：{}" + responseValidateMsg);
			model.addAttribute(Constants.MSG_WARN, responseValidateMsg);
			return "redirect:query";
		}
		
		logger.debug("修改信息验证通过");

		// 判断是邮箱注册还是授权登录
		if (StringUtils.isEmpty(sessionInfo.getLoginType())) { 
			if (StringUtils.isNotEmpty(user.getAvatarFile().getName())) {

				logger.debug("处理用户头像信息");
				
				String avataName = user.getAvatarFile().getOriginalFilename();
				String lastFix = avataName.substring(avataName.lastIndexOf("."), avataName.length());
				logger.debug("用户头像图片格式为 lastFix={}", lastFix);
				
				// 生成图片名称
				String ctxPath = request.getSession().getServletContext().getRealPath("/") + "imgbase/avatar/";
				logger.debug("保存用户头像路径为 ctxPath={}", ctxPath);
				String coverImgPath = System.currentTimeMillis() + lastFix;
				logger.info("封面图片 coverImgPath={}", coverImgPath);
				
				// 保存图片到服务器
				ImageUtil.saveFile(ctxPath, coverImgPath, user.getAvatarFile());
				logger.debug("头像已保存到图片服务器");
				// 设置user头像存储的路径
				user.setPicPath(coverImgPath);
				logger.debug("user头像存储的路径已设置");
				
			}
		}
		
		// 更新
		user.setUserid(sessionInfo.getUserid());
		userService.updateInfo(user);
		
		logger.debug("用户信息更新成功");
		
		model.addAttribute(Constants.MSG_SUCCESS, "资料更新成功 [下一次登录生效]");
		
		return "redirect:query";
	}

	@RequestMapping(value = "/password")
	public String password(Model model, HttpServletRequest request, User user) {
		logger.info("修改登录密码");
		
		// 获取登录用户的信息
		User sessionInfo = getCurrentUser(request);
		
		// 验证
		String responseValidateMsg = validateUserChangePwdInfo(user, sessionInfo);
		if (StringUtils.isNotEmpty(responseValidateMsg)) {
			model.addAttribute(Constants.MSG_WARN, responseValidateMsg);
			return "redirect:query";
		}
		
		// 更新密码
		user.setPassword(MD5.encryptMD5Pwd(user.getPassword1()));
		user.setUserid(sessionInfo.getUserid());
		userService.changePassword(user);
		
		model.addAttribute(Constants.MSG_SUCCESS, "密码更新成功 [下一次登录生效]");
		
		logger.debug("修改登录密码成功");
		
		return "redirect:query";
	}

	/**
	 * 验证修改用户信息参数
	 */
	private String validateUpdateInfo(User user, User sessionInfo) {
		String responseValidateMsg = null;
		
		if (StringUtils.isEmpty(user.getNickName())) {
			responseValidateMsg = "用户昵称不能为空";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (user.getNickName().length() < 2 || user.getNickName().length() > 8) {
			responseValidateMsg = "用户昵称无效长度 [2到8位]";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (user.getMemo().length() > 30) {
			responseValidateMsg = "一句话简介不能超过30个字";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (user.getUserGroup().length() > 20) {
			responseValidateMsg = "组织不能超过20个字";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (user.getUserGroup().length() > 20) {
			responseValidateMsg = "职位不能超过20个字";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		// 判断是邮箱注册还是授权登录
		if (StringUtils.isNotEmpty(sessionInfo.getLoginType())) { 
			return responseValidateMsg;
		}
		
		// 头像修改验证
		if (user.getAvatarFile().getSize() < 1) {
			// 头像可以为空
			return responseValidateMsg;
		}
		
		// 头像图片后缀名只能为png和jpg格式
		if (user.getAvatarFile().getName().contains(".png")
				&& user.getAvatarFile().getName().contains(".jpg")) {
			responseValidateMsg = "图片格式不正确，只能为png或jpg格式";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		// 头像大小不能超过100KB
		if (user.getAvatarFile().getSize() > 100*1024) {
			responseValidateMsg = "图片太大，不通超过100K";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		return responseValidateMsg;
	}
	
	/**
	 * 验证修改密码参数
	 */
	private String validateUserChangePwdInfo(User user, User sessionInfo) {
		String responseValidateMsg = null;
		
		if (StringUtils.isEmpty(user.getPassword())) {
			responseValidateMsg = "当前密码不能为空";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (StringUtils.isEmpty(user.getPassword1())) {
			responseValidateMsg = "新密码不能为空";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (StringUtils.isEmpty(user.getPassword2())) {
			responseValidateMsg = "新确认密码不能为空";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (user.getPassword().length() > 25 || user.getPassword().length() < 6) {
			responseValidateMsg = "当前密码长度无效 [6到25位]";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (user.getPassword1().length() > 25 || user.getPassword1().length() < 6) {
			responseValidateMsg = "新密码长度无效 [6到25位]";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (!user.getPassword1().equals(user.getPassword2())) {
			responseValidateMsg = "两次新密码输入不一致";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		if (!sessionInfo.getPassword().equals(MD5.encryptMD5Pwd(user.getPassword()))) {
			responseValidateMsg = "当前密码不正确";
			logger.debug("参数验证失败：{}", responseValidateMsg);
			return responseValidateMsg;
		}
		
		return responseValidateMsg;
	}
	
}
