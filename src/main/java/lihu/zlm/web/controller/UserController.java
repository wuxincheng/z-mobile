package lihu.zlm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lihu.zlm.web.model.Product;
import lihu.zlm.web.model.User;
import lihu.zlm.web.service.ProductService;
import lihu.zlm.web.service.UserService;
import lihu.zlm.util.Validation;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private ProductService productService;

	@Resource
	private UserService userService;

	@RequestMapping(value = "/main")
	public String main(Model model, HttpServletRequest request, String queryUserid) {
		logger.info("显示用户中心 queryUserid={}", queryUserid);

		// 验证userid
		if (StringUtils.isEmpty(queryUserid) || !Validation.isIntPositive(queryUserid)) {
			logger.debug("查询失败：queryUserid为空");
			return "404";
		}

		// 查询用户
		User userQuery = userService.queryByUserid(queryUserid);

		if (null == userQuery) {
			logger.debug("查询失败：没有查询到用户信息");
			return "404";
		}

		logger.debug("已查询到用户信息");

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("queryUserid", queryUserid); // 需要查看用户信息的userid

		// 判断当前是否有登录用户
		String sessionUserid = getCurrentUserid(request);
		if (StringUtils.isNotEmpty(sessionUserid)) {
			queryMap.put("sessionUserid", sessionUserid);
		}

		// 查询登录用户赞过的产品
		List<Product> products = productService.queryUserMain(queryMap);

		logger.debug("查询登录用户赞过的产品");

		model.addAttribute("products", products);
		model.addAttribute("userQuery", userQuery);

		return "my/main";
	}

}
