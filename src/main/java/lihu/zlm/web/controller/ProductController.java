package lihu.zlm.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import lihu.zlm.web.Pager;
import lihu.zlm.web.model.Comment;
import lihu.zlm.web.model.ProdLike;
import lihu.zlm.web.model.Product;
import lihu.zlm.web.service.CommentService;
import lihu.zlm.web.service.ProdLikeService;
import lihu.zlm.web.service.ProductService;
import lihu.zlm.util.Constants;
import lihu.zlm.util.Validation;

/**
 * 产品管理
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月25日 下午7:01:02
 * 
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Resource
	private CommentService commentService;

	@Resource
	private ProductService productService;

	@Resource
	private ProdLikeService prodLikeService;

	@RequestMapping(value = "/list")
	@Deprecated
	public String list(Model model, HttpServletRequest request, String currentPage) {
		logger.info("显示产品列表");
		requestMessageProcess(request);

		// 根据产品发布的日期分组
		List<String> groupDates = productService.queryGroupByDate();

		if (null == groupDates || groupDates.size() < 1) {
			logger.info("没有查询到产品发布日期");
			return "product/list";
		}

		// 判断是否有用户登录
		String userid = getCurrentUserid(request);

		// 每次分页只显示三个日期下发布的产品
		Pager pager = productService.queryProductsByDate(groupDates, userid);
		logger.info("查询到产品信息");

		model.addAttribute("pager", pager);

		return "product/list";
	}

	@RequestMapping(value = "/postUI")
	public String postUI(Model model, HttpServletRequest request, String collectid) {
		logger.info("显示发布分享新产品页");
		requestMessageProcess(request);

		if (StringUtils.isNotEmpty(collectid)) {
			if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
				return "404";
			}

			model.addAttribute("collectid", collectid);
		}

		return "product/postUI";
	}

	/**
	 * 目前只支持在榜单中添加新的基金产品, 已不支持在TOP中首页添加产品
	 * 
	 * @param model
	 * @param request
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/doPost")
	public String doPost(Model model, HttpServletRequest request, Product product) {
		logger.info("处理发布分享新产品数据");

		try {
			// 发布产品
			String responseMsg = productService.post(product, getCurrentUserid(request));
			if (StringUtils.isNotEmpty(responseMsg)) {
				model.addAttribute(Constants.MSG_WARN, responseMsg);
			} else {
				logger.info("产品信息发布成功");
				model.addAttribute(Constants.MSG_SUCCESS, "产品信息发布成功");
			}
		} catch (Exception e) {
			logger.error("产品发布出现异常：", e);
			model.addAttribute(Constants.MSG_ERROR, "产品发布出现异常，请稍后重试！");
			return "product/postUI";
		}

		return product.getLikeState() == null ? "redirect:/collect/detail?collectid=" + product.getCollectid()
				: "product/success";
	}

	@RequestMapping(value = "/detail")
	@Deprecated
	public String detail(Model model, HttpServletRequest request, String prodid) {
		logger.info("显示产品详细页面 prodid={}", prodid);

		if (StringUtils.isEmpty(prodid) || !Validation.isIntPositive(prodid)) {
			return "404";
		}

		// 判断是否有用户登录
		String userid = getCurrentUserid(request);

		// 产品详细
		Product product = productService.queryDetailByProdid(prodid, userid);
		model.addAttribute("product", product);

		// 评论列表
		List<Comment> comments = commentService.queryByProductid(prodid);
		model.addAttribute("comments", comments);

		// 赞列表
		List<ProdLike> prodLikes = prodLikeService.queryLikeUserDetail(prodid);
		model.addAttribute("prodLikes", prodLikes);

		return "product/detail";
	}

	/**
	 * 点赞属于异步操作
	 */
	@RequestMapping(value = "/like")
	@ResponseBody
	@Deprecated
	public Map<String, String> like(HttpServletRequest request, String prodid) {
		logger.info("点赞异步操作 prodid={}", prodid);

		// 验证prodid和userid的合法性
		if (StringUtils.isEmpty(prodid) || !Validation.isIntPositive(prodid)) {
			return null;
		}

		// 获取当前登录用户ID
		String userid = getCurrentUserid(request);
		if (StringUtils.isEmpty(userid)) {
			return null;
		}

		Map<String, String> result = prodLikeService.like(prodid, userid);

		logger.info("点赞操作结果 result={}", result);

		return result;
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, String prodid) {
		return "product/detail";
	}

}
