package lihu.zlm.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lihu.zlm.web.Pager;
import lihu.zlm.web.dao.CollectDao;
import lihu.zlm.web.dao.ProductDao;
import lihu.zlm.web.model.Product;
import lihu.zlm.util.Constants;
import lihu.zlm.util.DateUtil;

/**
 * 产品Service
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月26日 下午8:25:13
 * 
 */
@Service("productService")
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Resource
	private ProductDao productDao;
	@Resource
	private CollectDao collectDao;
	@Resource
	private ProdLikeService prodLikeService;

	/**
	 * 发布产品(基金产品+简单评论)
	 */
	public String post(Product product, String userid) throws Exception {
		String responseMessage = null;

		logger.debug("验证产品发布参数");

		// 验证主要参数
		if (StringUtils.isEmpty(product.getFundName())) {
			responseMessage = "产品名称不能为空";
			logger.info(responseMessage);
			return responseMessage;
		}

		if (StringUtils.isEmpty(product.getFundCode())) {
			responseMessage = "产品代码不能为空";
			logger.info(responseMessage);
			return responseMessage;
		}

		if (StringUtils.isEmpty(product.getMemo())) {
			responseMessage = "产品说明您就简单说两句吧！";
			logger.info(responseMessage);
			return responseMessage;
		}

		if (StringUtils.isEmpty(product.getCollectid())) {
			responseMessage = "榜单无效";
			logger.info(responseMessage);
			return responseMessage;
		}

		// TODO 同一个榜单下不能重复添加同一个基金产品
		/*
		Product queryProduct = productDao.queryByFundCodeAndCollectid(product.getFundCode(), product.getCollectid());
		if (queryProduct != null) {
			responseMessage = "该基金榜单中已经存在";
			logger.info(responseMessage);
			return responseMessage;
		}
		 */

		Date date = new Date();

		product.setCommentSum(0);
		product.setLikeSum(0);
		product.setPostDate(DateUtil.getCurrentDate(date, Constants.DEFAULT_DATE));
		product.setProdState(Constants.DEFAULT_STATE);
		product.setPostDateTime(DateUtil.getCurrentDate(date, Constants.DEFAULT_DATE_FORMAT));
		product.setUserid(userid);
		product.setScore("0"); // 产品关注度初始为0

		// 发布这个产品
		productDao.post(product);
		
		// 更新产品集中产品的数量
		collectDao.addProductSum(product.getCollectid());
		
		// TODO 每增加一个产品, 榜单分数加3
		// TODO 查询用户是否已经赞这个榜单
		// TODO 如果已经赞, 不进行操作, 如果没有赞则赞同操作, 并加上赞同人数和分数
		// TODO 更新榜单赞人数和赞分数

		/*
		// 当前用户在发布产品的时默认赞这个产品
		List<Product> products = productDao.queryPostByUserid(userid);
		if (null == products || products.size() < 1) {
			return responseMessage;
		}
		// 最新发布的产品
		prodLikeService.like(products.get(0).getProdid(), userid);
		 */

		return responseMessage;
	}

	/**
	 * 根据日期分组统计
	 */
	public List<String> queryGroupByDate() {
		return productDao.queryGroupByDate();
	}

	/**
	 * 查询产品
	 */
	public Pager queryProductsByDate(List<String> groupDates, String userid) {
		Pager pager = new Pager();

		List<Map<String, List<Product>>> productMapList = new ArrayList<Map<String, List<Product>>>();

		Map<String, String> queryMap = null;
		for (String queryPostDate : groupDates) {
			queryMap = new HashMap<String, String>();
			queryMap.put("queryPostDate", queryPostDate);
			queryMap.put("userid", userid);
			List<Product> products = productDao.queryByPostDate(queryMap);
			Map<String, List<Product>> productMap = new HashMap<String, List<Product>>();
			productMap.put(queryPostDate, products);

			productMapList.add(productMap);
		}

		pager.setProductMapList(productMapList);

		return pager;
	}

	/**
	 * 根据产品编号查询详细信息
	 */
	public Product queryDetailByProdid(String prodid, String userid) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("prodid", prodid);
		queryMap.put("userid", userid);
		return productDao.queryDetailByProdid(queryMap);
	}

	/**
	 * 根据产品集主键查询产品列表
	 */
	public List<Product> queryProductsByCollectid(Map<String, String> queryMap) {
		return productDao.queryProductsByCollectid(queryMap);
	}

	/**
	 * 登录用户查询自己发布和赞过的产品
	 * 
	 * @param userid
	 * @return
	 */
	public List<Product> queryUserHome(String userid) {
		// Set<Product> products = new HashSet<Product>();

		/*
		 * // 查询用户发布的所有产品 List<Product> postProducts =
		 * productDao.queryPostByUserid(userid); if (postProducts != null &&
		 * postProducts.size() > 0) { for (Product postProduct : postProducts) {
		 * products.add(postProduct); } }
		 */

		// 查询用户赞过的所有产品
		List<Product> likeProducts = productDao.queryLikeByUserid(userid);
		/*
		 * if (likeProducts != null && likeProducts.size() > 0) { for (Product
		 * likeProduct : likeProducts) { products.add(likeProduct); } }
		 * 
		 * if (products == null || products.size() < 1) { return null; }
		 * 
		 * List<Product> userProducts = new ArrayList<Product>();
		 * 
		 * // 转换成List集合 Iterator<Product> itp = products.iterator();
		 * while(itp.hasNext()){ userProducts.add(itp.next()); }
		 */

		return likeProducts;
	}
	
	public List<Product> queryCollectProductUser(Map<String, String> queryMap) {
		return productDao.queryCollectProductUser(queryMap);
	}

	/**
	 * 查询用户主页，和已登录用户赞过这个用户的产品列表
	 */
	public List<Product> queryUserMain(Map<String, String> queryMap) {
		return productDao.queryUserMain(queryMap);
	}

}
