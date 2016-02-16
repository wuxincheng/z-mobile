package lihu.zlm.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lihu.zlm.web.model.Advisor;
import lihu.zlm.web.model.Collect;
import lihu.zlm.web.service.AdvisorService;
import lihu.zlm.web.service.CollectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 发现
 * 
 * @author wuxincheng(wxcking)
 *
 * @Date 2016年1月27日 下午7:40:59
 *
 */
@Controller
@RequestMapping("/discovery")
public class DiscoveryController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(DiscoveryController.class);
	
	@Autowired
	private CollectService collectService;
	
	@Autowired
	private AdvisorService advisorService;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		logger.info("显示榜单列表");

		// 1. 显示前10名热门榜单
		List<Collect> collects = collectService.queryTopHot(10);
		model.addAttribute("collects", collects);
		
		// 2. 热门的理财师
		List<Advisor> advisors = advisorService.queryTopHot(5);
		model.addAttribute("advisors", advisors);

		return "discovery/list";
	}
	
}
