package lihu.zlm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lihu.zlm.util.Validation;
import lihu.zlm.web.Result;
import lihu.zlm.web.model.Collect;
import lihu.zlm.web.model.CollectUser;
import lihu.zlm.web.model.Comment;
import lihu.zlm.web.model.FundMarket;
import lihu.zlm.web.model.Product;
import lihu.zlm.web.model.User;
import lihu.zlm.web.service.CollectService;
import lihu.zlm.web.service.CollectUserService;
import lihu.zlm.web.service.CommentService;
import lihu.zlm.web.service.FundMarketService;
import lihu.zlm.web.service.ProdLikeService;
import lihu.zlm.web.service.ProductService;
import lihu.zlm.web.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 榜单
 * 
 * @author wuxincheng(wxcking)
 * 
 * @Date 2016年1月22日 下午3:38:07
 * 
 */
@Controller
@RequestMapping("/collect")
public class CollectController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CollectController.class);

	@Autowired
	private CollectService collectService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CollectUserService collectUserService;

	@Autowired
	private FundMarketService fundMarketService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ProdLikeService prodLikeService;

	@Resource
	private UserService userService;

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request) {
		logger.info("显示榜单列表");

		List<Collect> collects = collectService.queryAll();

		if (null == collects || collects.size() < 1) {
			logger.debug("目前还没发布榜单");
		}

		request.setAttribute("collects", collects);

		return "collect/list";
	}

	@RequestMapping(value = "/detail")
	public String detail(Model model, HttpServletRequest request, String collectid) {
		logger.info("显示榜单详细 collectionid={}", collectid);

		// 判断collectid
		if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
			logger.debug("详细显示失败：collectid为空");
			return "redirect:list";
		}

		// 是否存在这个榜单
		Collect collect = collectService.queryDetailByCollectid(collectid);
		if (null == collect) {
			logger.debug("详细显示失败：榜单不存在 collectid={}", collectid);
			return "redirect:list";
		}
		request.setAttribute("collect", collect);

		// 查询用户
		User userQuery = userService.queryByUserid(collect.getUserid());
		request.setAttribute("userQuery", userQuery);

		String userid = null;

		// 判断用户是否已经登录
		if (getCurrentUserid(request) != null) {
			// 如果登录，查询该用户是否已经收藏该榜单
			CollectUser collectUser = collectUserService
					.query(collectid, getCurrentUserid(request));
			request.setAttribute("collectUser", collectUser);
			userid = getCurrentUserid(request);
		}

		// 查询这个榜单下的所有产品
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("collectid", collectid);
		queryMap.put("userid", userid);
		List<Product> products = productService.queryProductsByCollectid(queryMap);

		// 关联查询所有基金信息
		List<FundMarket> fundMarkets = fundMarketService.queryByProducts(products);
		request.setAttribute("fundMarkets", fundMarkets);

		// 查询这个榜单的所有评论
		List<Comment> comments = commentService.queryByCollectid(collectid);
		model.addAttribute("comments", comments);

		// 显示前5名热门榜单
		List<Collect> collects = collectService.queryTopHot(5);
		model.addAttribute("collects", collects);

		return "collect/detail";
	}

	/**
	 * 榜单收藏和取消收藏操作
	 * 
	 * @param collectid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/collect")
	public String collect(String collectid, String userid) {
		logger.info("榜单收藏和取消收藏操作 collectionid={} userid={}", collectid, userid);

		if (StringUtils.isNotBlank(collectid) && StringUtils.isNotBlank(userid)) {
			collectUserService.collect(collectid, userid);
			logger.debug("榜单收藏和取消收藏操作成功");
		} else {
			logger.debug("榜单收藏和取消收藏操作失败：collectid或userid为空");
		}

		return "redirect:/collect/detail?collectid=" + collectid;
	}

	@RequestMapping(value = "/like")
	@ResponseBody
	public Map<String, Object> like(Model model, HttpServletRequest request, String collectid,
			String likeState) {
		logger.info("用户点赞同和反对操作 collectid={}, likeState={}", collectid, likeState);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", false);
		result.put("collectid", collectid);

		// 用户是否登录
		String userid = getCurrentUserid(request);

		if (StringUtils.isEmpty(userid)) {
			result.put("message", "您还没有登录");
			logger.info("用户还没有登录 result={}", result);
			return result;
		}

		logger.info("调用点赞服务");
		Integer[] scores = prodLikeService.collectLikeHandle(collectid, likeState, userid);
		if (scores != null && scores.length == 2) {
			result.put("flag", true);
			result.put("likeScore", scores[0]);
			result.put("unLikeScore", scores[1]);

			logger.info("用户点赞成功 result={}", result);
		}

		return result;
	}

	@RequestMapping(value = "/focus")
	@ResponseBody
	public Result focus(String fundCode, HttpServletRequest request) {
		logger.info("基金收藏和取消收藏操作 fundCode={}", fundCode);

		Result result = new Result();

		String userid = getCurrentUserid(request);
		if (StringUtils.isEmpty(userid)) {
			return result.redirect("/login/");
		}

		if (fundCode != null && userid != null) {
			String msg = collectUserService.focusFund(fundCode, userid);
			result.setSuccess(true);
			result.setSuccessMsg(msg);
			return result;
		} else {
			logger.debug("基金收藏和取消收藏操作失败：collectid或userid为空");
			return result.reject("操作失败");
		}
	}

}
