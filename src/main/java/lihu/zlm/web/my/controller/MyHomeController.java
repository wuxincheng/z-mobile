package lihu.zlm.web.my.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lihu.zlm.web.controller.BaseController;
import lihu.zlm.web.model.Product;
import lihu.zlm.web.model.User;
import lihu.zlm.web.service.ProductService;
import lihu.zlm.web.service.UserService;
import lihu.zlm.util.Constants;

/**
 * 个人中心：我的主页
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月3日 下午5:39:04 
 *
 */
@Controller
@RequestMapping("/my/home")
public class MyHomeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MyHomeController.class);
	
	@Resource
	private ProductService productService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request, String userid) {
		logger.info("显示用户中心");

		// 验证userid不能为空
		if (StringUtils.isEmpty(userid)) {
			userid = getCurrentUserid(request);
		}
		if (StringUtils.isEmpty(userid)) {
			model.addAttribute(Constants.MSG_WARN, "用户指定不明，无法查询信息");
			logger.debug("用户指定不明，无法查询信息");
			return "redirect:/login/";
		}

		// 判断是否有登录用户
		User user = getCurrentUser(request);
		if (null == user) { 
			// 如果为空，则根据userid查询用户信息
			user = userService.queryByUserid(userid);
			model.addAttribute("user", user);
		}
		
		List<Product> products = productService.queryUserHome(userid);
		
		model.addAttribute("products", products);
		
		return "my/main";
	}
}
