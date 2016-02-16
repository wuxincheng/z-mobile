package lihu.zlm.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lihu.zlm.web.service.CollectService;
import lihu.zlm.web.service.FundMarketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月24日 下午9:16:44
 * 
 */
@Controller
public class IndexController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private CollectService collectService;

	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("显示首页");
		response.sendRedirect(request.getContextPath() + "/collect/list");
		logger.info("已跳转至榜单列表页面");
		return "index";
	}

	@RequestMapping(value = "/sessionExpired")
	public String sessionExpired(Model model) {
		return "sessionExpired";
	}

}
