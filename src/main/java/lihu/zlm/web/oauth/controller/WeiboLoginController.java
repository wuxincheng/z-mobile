package lihu.zlm.web.oauth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lihu.zlm.util.Constants;

/**
 * 新浪微博登录
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年7月30日 下午1:21:29 
 *
 */
@Controller
@RequestMapping("/oauth/weibo")
public class WeiboLoginController {
	private static final Logger logger = LoggerFactory.getLogger(WeiboLoginController.class);

	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		logger.info("跳转到新浪微博登录授权页面");
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			response.sendRedirect("");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/callback", method=RequestMethod.GET)
	public String callback(Model model, HttpServletRequest request) {
		model.addAttribute(Constants.MSG_WARN, "新浪微博登录操作");
		
		return "redirect:/product/list";
	}
	
}
