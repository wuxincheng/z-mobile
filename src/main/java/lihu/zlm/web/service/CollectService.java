package lihu.zlm.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lihu.zlm.util.Validation;
import lihu.zlm.web.dao.CollectDao;
import lihu.zlm.web.dao.ProductDao;
import lihu.zlm.web.model.Collect;
import lihu.zlm.web.model.Product;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("collectService")
public class CollectService {
	private static final Logger logger = LoggerFactory.getLogger(CollectService.class);

	@Resource
	private CollectDao collectDao;

	@Resource
	private ProductDao productDao;

	/**
	 * 查询所有产品集
	 */
	public List<Collect> queryAll() {
		return collectDao.queryAll();
	}

	/**
	 * 根据产品集主键查询详细
	 */
	public Collect queryDetailByCollectid(String collectid) {
		if (StringUtils.isEmpty(collectid)) {
			return null;
		}
		return collectDao.queryDetailByCollectid(collectid);
	}

	/**
	 * 查询用户发布的榜单
	 */
	public List<Collect> queryByUserid(String userid) {
		if (StringUtils.isEmpty(userid)) {
			return null;
		}
		return collectDao.queryByUserid(userid);
	}

	/**
	 * 查询热门榜单
	 * 
	 * @param topLimit
	 * @return
	 */
	public List<Collect> queryTopHot(Integer topLimit) {
		return collectDao.queryTopHot(topLimit);
	}

	/**
	 * 删除榜单
	 */
	public String delete(String collectid, String userid) {
		logger.info("删除榜单 collectid={}", collectid);
		String responseMessage = null;

		if (StringUtils.isEmpty(collectid) || !Validation.isIntPositive(collectid)) {
			responseMessage = "删除失败：collectid为空";
			logger.debug(responseMessage);
			return responseMessage;
		}

		// 查询榜单信息
		Collect queryCollect = collectDao.queryDetailByCollectid(collectid);
		logger.debug("查询出要删除的榜单信息");

		if (!userid.equals(queryCollect.getUserid())) {
			responseMessage = "您无权限删除非您创建的榜单";
			logger.warn(responseMessage);
			return responseMessage;
		}

		// 查询这个榜单中是否有发布的产品
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("collectid", collectid);

		logger.debug("查询该榜单是否存在产品信息");
		List<Product> products = productDao.queryProductsByCollectid(queryMap);
		if (products != null && products.size() > 0) {
			responseMessage = "删除失败：该榜单产品不为空";
			logger.debug(responseMessage);
			return responseMessage;
		}

		logger.debug("该榜单中的产品为空，可以删除");

		collectDao.delete(collectid);
		logger.debug("已删除");

		return responseMessage;
	}

	/**
	 * 从榜单中移除产品
	 */
	public String remove(String collectid, String prodid, String userid) {
		if (StringUtils.isBlank(prodid) || StringUtils.isBlank(collectid)) {
			logger.debug("参数不合法：prodid或collectid为空");
			return "参数不合法";
		}

		Collect collect = collectDao.queryDetailByCollectid(collectid);
		if (null == collect) {
			logger.debug("榜单不存在");
			return "榜单不存在";
		}

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("prodid", prodid);
		queryMap.put("userid", userid);
		Product product = productDao.queryDetailByProdid(queryMap);
		if (null == product) {
			logger.debug("产品已移除或不存在");
			return "产品已移除或不存在";
		}

		if (!collectid.equals(product.getCollectid())) {
			logger.debug("产品已移除或不存在");
			return "产品已移除或不存在";
		}

		if (!userid.equals(collect.getUserid())) {
			logger.debug("没有权限操作，不是当前用户创建的榜单");
			return "您没有权限";
		}

		Map<String, String> removeMap = new HashMap<String, String>();
		removeMap.put("collectid", collectid);
		removeMap.put("prodid", prodid);
		// 从榜单中删除这个产品
		productDao.remove(removeMap);
		logger.debug("榜单移除成功");

		if (collect.getProductSum() > 0) {
			collectDao.removeProductSum(product.getCollectid());
		}

		return null;
	}

}
