package lihu.zlm.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lihu.zlm.web.dao.CollectDao;
import lihu.zlm.web.dao.FundMarketDao;
import lihu.zlm.web.dao.ProdLikeDao;
import lihu.zlm.web.dao.ProductDao;
import lihu.zlm.web.model.Collect;
import lihu.zlm.web.model.FundMarket;
import lihu.zlm.web.model.ProdLike;
import lihu.zlm.web.model.Product;
import lihu.zlm.util.Constants;
import lihu.zlm.util.DateUtil;

@Service("prodLikeService")
public class ProdLikeService {
	private static final Logger logger = LoggerFactory.getLogger(ProdLikeService.class);

	@Resource
	private ProdLikeDao prodLikeDao;

	@Resource
	private ProductDao productDao;

	@Resource
	private CollectDao collectDao;

	@Resource
	private FundMarketDao fundMarketDao;

	public void insert(ProdLike prodLike) {
		prodLikeDao.insert(prodLike);
	}

	public void delete(ProdLike prodLike) {
		prodLikeDao.delete(prodLike);
	}

	/**
	 * 用户点赞
	 * 
	 * @param prodid
	 *            产品主键
	 * @param userid
	 *            用户主键
	 * @return
	 */
	public Map<String, String> like(String prodid, String userid) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("prodid", prodid);

		ProdLike queryProdLike = new ProdLike();
		queryProdLike.setUserid(userid);
		queryProdLike.setProdid(prodid);
		// 查询
		ProdLike prodLike = prodLikeDao.query(queryProdLike);

		Map<String, Object> like = new HashMap<String, Object>();
		if (prodLike != null) { // 已经点赞
			// 取消点赞
			prodLikeDao.delete(queryProdLike);

			result.put("flag", "0"); // 点赞(1)或取消点赞(0)

			like.put("prodid", prodid);
			like.put("score", -1); // 关注度变化
			like.put("likeSum", -1); // 点赞数量变化
			productDao.postLikeScore(like);
		} else { // 还未点赞
			// 点赞
			queryProdLike.setLikeState(Constants.DEFAULT_STATE);
			queryProdLike.setLikeTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:Ss"));
			queryProdLike.setLikeType("product");
			prodLikeDao.insert(queryProdLike);

			result.put("flag", "1"); // 点赞(1)或取消点赞(0)

			like.put("prodid", prodid);
			like.put("score", 1);
			like.put("likeSum", 1);
			productDao.postLikeScore(like);
		}

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("prodid", prodid);
		queryMap.put("userid", userid);

		Product product = productDao.queryDetailByProdid(queryMap);
		result.put("score", product.getScore()); // 点赞后产品关注分数

		return result;
	}

	/**
	 * 查询点赞用户详细信息列表
	 * 
	 * @param prodid
	 * @return
	 */
	public List<ProdLike> queryLikeUserDetail(String prodid) {
		return prodLikeDao.queryLikeUserDetail(prodid);
	}

	/**
	 * 根据基金代码和用户主键查询赞信息
	 * 
	 * @param fundCode
	 *            基金代码
	 * @param userid
	 *            用户主键
	 * @param likeType
	 *            赞类型: fundMarket/product
	 * @return
	 */
	public ProdLike queryByFundCode(String fundCode, String userid) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("fundCode", fundCode);
		queryMap.put("userid", userid);
		return prodLikeDao.queryDetailByFundCode(queryMap);
	}

	/**
	 * 产品集的赞同与反对操作
	 * 
	 * @param collectid
	 *            产品集主键
	 * @param likeType
	 *            点赞或反对: 0-赞同, 1-反对
	 * @param userid
	 *            登录用户主键
	 * 
	 * @return 产品集赞同和反对分数
	 */
	public Integer[] collectLikeHandle(String collectid, String likeState, String userid) {
		logger.info("产品集的赞同与反对操作 collectid={}", collectid);
		logger.info("likeState={}, userid={}", likeState, userid);

		// 判断用户是否已经登录
		if (StringUtils.isEmpty(userid)) {
			logger.warn("用户登录无效");
			return null;
		}

		// 验证两个参数是否为空
		if (StringUtils.isEmpty(collectid) || StringUtils.isEmpty(likeState)) {
			logger.warn("参数都不能为空 collectid={}, likeType={}", collectid, likeState);
			return null;
		}

		// 查询该产品集信息是否存在
		Collect collect = collectDao.queryDetailByCollectid(collectid);
		if (null == collect) {
			logger.warn("产品集信息不存在 collect={}", collect);
			return null;
		}

		// 一个产品集一个用户的赞同和反对记录只有一条, 赞同和反对是单选的关系

		// 查询这个赞同或反对记录是否已经存在
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("collectid", collectid);
		queryMap.put("userid", userid);
		queryMap.put("likeType", "collect");

		ProdLike prodLike = prodLikeDao.queryDetailByCollectid(queryMap);

		if (null == prodLike) { // 如果不存在赞同或反对信息, 则新增
			logger.info("新增赞同或反对信息");

			prodLike = new ProdLike();
			prodLike.setCollectid(collectid);
			prodLike.setUserid(userid);
			prodLike.setLikeTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:Ss"));
			prodLike.setLikeState(likeState); // 点赞或反对: 0-赞同, 1-反对
			prodLike.setLikeType("collect"); // 赞类型: /product/collect
			prodLikeDao.insert(prodLike);
			logger.info("成功添加一条点赞记录");

			if ("0".equals(likeState)) { // 点赞
				// 更新赞同的人数和分数
				logger.info("更新赞同的人数和分数");
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("collectid", collectid);
				updateMap.put("likeSum", 1);
				updateMap.put("likeScore", 1);
				collectDao.updateLikeInfo(updateMap);
				logger.debug("更新赞同的人数和分数成功");

				// 重新查询产品集赞同的分数
				logger.info("重新查询产品集赞同的分数");
				collect = collectDao.queryDetailByCollectid(collectid);
				logger.debug("产品集赞同的分数 LikeScore={}", collect.getLikeScore());
				logger.debug("产品集反对的分数 unLikeScore={}", collect.getUnLikeScore());
				return new Integer[] { collect == null ? 0 : collect.getLikeScore(),
						collect == null ? 0 : collect.getUnLikeScore() };
			}

			if ("1".equals(likeState)) { // 反对
				// 更新反对的人数
				logger.info("更新反对的人数");
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("collectid", collectid);
				updateMap.put("unLikeSum", 1);
				updateMap.put("unLikeScore", 1);
				collectDao.updateUnLikeInfo(updateMap);
				logger.debug("更新反对的人数和分数成功");

				// 重新查询产品集反对的分数
				collect = collectDao.queryDetailByCollectid(collectid);
				logger.debug("产品集赞同的分数 LikeScore={}", collect.getLikeScore());
				logger.debug("产品集反对的分数 unLikeScore={}", collect.getUnLikeScore());
				return new Integer[] { collect == null ? 0 : collect.getLikeScore(),
						collect == null ? 0 : collect.getUnLikeScore() };
			}
		} else { // 如果存在更新like记录
			logger.info("更新like记录");
			// 如果是赞同
			if ("0".equals(prodLike.getLikeState())) {
				// 减少赞同人数及赞同分数
				logger.info("减少赞同人数及赞同分数");
				Map<String, Object> reduceCollectInfoMap = new HashMap<String, Object>();
				reduceCollectInfoMap.put("collectid", collectid);
				reduceCollectInfoMap.put("likeSum", -1);
				reduceCollectInfoMap.put("likeScore", -1);
				collectDao.updateLikeInfo(reduceCollectInfoMap);

				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("取消赞同操作");

					// 删除这条赞同记录
					logger.info("删除这条赞同记录");
					prodLikeDao.deleteCollectLike(prodLike);
					logger.debug("赞同记录删除成功");
				} else {
					// 更新赞同记录

					// 更新赞同类型为反对
					logger.info("更新赞同类型为反对");
					Map<String, Object> updateCollectLikeMap = new HashMap<String, Object>();
					updateCollectLikeMap.put("collectid", collectid);
					updateCollectLikeMap.put("userid", userid);
					updateCollectLikeMap.put("likeState", "1");
					prodLikeDao.updateCollectLike(updateCollectLikeMap);

					// 增加反对人数及赞同分数
					logger.info("增加反对人数及赞同分数");
					Map<String, Object> plusCollectInfoMap = new HashMap<String, Object>();
					plusCollectInfoMap.put("collectid", collectid);
					plusCollectInfoMap.put("unLikeSum", 1);
					plusCollectInfoMap.put("unLikeScore", 1);
					collectDao.updateUnLikeInfo(plusCollectInfoMap);
				}

				// 重新查询产品集反对的分数
				logger.info("重新查询产品集反对的分数");
				collect = collectDao.queryDetailByCollectid(collectid);
				logger.debug("产品集赞同的分数 LikeScore={}", collect.getLikeScore());
				logger.debug("产品集反对的分数 unLikeScore={}", collect.getUnLikeScore());
				return new Integer[] { collect == null ? 0 : collect.getLikeScore(),
						collect == null ? 0 : collect.getUnLikeScore() };
			}

			// 如果是反对
			if ("1".equals(prodLike.getLikeState())) {
				// 减少反对人数及赞同分数
				logger.info("减少反对人数及赞同分数");
				Map<String, Object> plusCollectInfoMap = new HashMap<String, Object>();
				plusCollectInfoMap.put("collectid", collectid);
				plusCollectInfoMap.put("unLikeSum", -1);
				plusCollectInfoMap.put("unLikeScore", -1);
				collectDao.updateUnLikeInfo(plusCollectInfoMap);

				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("取消反对操作");

					// 删除这条反对记录
					logger.info("删除这条反对记录");
					prodLikeDao.deleteCollectLike(prodLike);
					logger.debug("反对记录删除成功");
				} else {
					// 更新反对类型为赞同
					logger.info("更新反对类型为赞同");
					Map<String, Object> updateCollectLikeMap = new HashMap<String, Object>();
					updateCollectLikeMap.put("collectid", collectid);
					updateCollectLikeMap.put("userid", userid);
					updateCollectLikeMap.put("likeState", "0");
					prodLikeDao.updateCollectLike(updateCollectLikeMap);

					// 增加赞同人数及赞同分数
					logger.info("增加赞同人数及赞同分数");
					Map<String, Object> reduceCollectInfoMap = new HashMap<String, Object>();
					reduceCollectInfoMap.put("collectid", collectid);
					reduceCollectInfoMap.put("likeSum", 1);
					reduceCollectInfoMap.put("likeScore", 1);
					collectDao.updateLikeInfo(reduceCollectInfoMap);
				}

				// 重新查询产品集反对的分数
				logger.info("重新查询产品集反对的分数");
				collect = collectDao.queryDetailByCollectid(collectid);
				logger.debug("产品集赞同的分数 LikeScore={}", collect.getLikeScore());
				logger.debug("产品集反对的分数 unLikeScore={}", collect.getUnLikeScore());
				return new Integer[] { collect == null ? 0 : collect.getLikeScore(),
						collect == null ? 0 : collect.getUnLikeScore() };
			}
		}

		return null;
	}

	/**
	 * 基金行情的赞同与反对操作
	 * 
	 * @param fundCode
	 *            基金代码
	 * @param likeType
	 *            点赞或反对: 0-赞同, 1-反对
	 * @param userid
	 *            登录用户主键
	 * 
	 * @return 这条行情赞同和反对分数
	 */
	public Integer[] fundMarketLikeHandle(String fundCode, String likeState, String userid) {
		logger.info("基金行情的赞同与反对操作 fundCode={}", fundCode);
		logger.info("likeState={}, userid={}", likeState, userid);

		// 判断用户是否已经登录
		if (StringUtils.isEmpty(userid)) {
			logger.warn("用户登录无效");
			return null;
		}

		// 验证两个参数是否为空
		if (StringUtils.isEmpty(fundCode) || StringUtils.isEmpty(likeState)) {
			logger.warn("参数都不能为空 fundCode={}, likeType={}", fundCode, likeState);
			return null;
		}

		// 查询该行情信息是否存在
		FundMarket fundMarket = fundMarketDao.queryDetail(fundCode);
		if (null == fundMarket) {
			logger.warn("基金行情信息不存在 fundCode={}", fundCode);
			return null;
		}

		// 一条行情一个用户的赞同和反对记录只有一条, 赞同和反对是单选的关系

		// 查询这个赞同或反对记录是否已经存在
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("fundCode", fundCode);
		queryMap.put("userid", userid);
		queryMap.put("likeType", "fundMarket");

		ProdLike prodLike = prodLikeDao.queryDetailByFundCode(queryMap);

		if (null == prodLike) { // 如果不存在赞同或反对信息, 则新增
			logger.info("新增赞同或反对信息");

			prodLike = new ProdLike();
			prodLike.setFundCode(fundCode);
			prodLike.setUserid(userid);
			prodLike.setLikeTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:Ss"));
			prodLike.setLikeState(likeState); // 点赞或反对: 0-赞同, 1-反对
			prodLike.setLikeType("fundMarket"); // 赞类型: /product
			prodLikeDao.insert(prodLike);
			logger.info("成功添加一条点赞记录");

			if ("0".equals(likeState)) { // 点赞
				// 更新赞同的人数和分数
				logger.info("更新赞同的人数和分数");
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("fundCode", fundCode);
				updateMap.put("likeSum", 1);
				updateMap.put("likeScore", 1);
				fundMarketDao.updateLikeInfo(updateMap);
				logger.debug("更新赞同的人数和分数成功");

				// 重新查询行情赞同的分数
				logger.info("重新查询行情赞同的分数");
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}

			if ("1".equals(likeState)) { // 反对
				// 更新反对的人数
				logger.info("更新反对的人数");
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("fundCode", fundCode);
				updateMap.put("unLikeSum", 1);
				updateMap.put("unLikeScore", 1);
				fundMarketDao.updateUnLikeInfo(updateMap);
				logger.debug("更新反对的人数和分数成功");

				// 重新查询行情反对的分数
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}
		} else { // 如果存在更新like记录
			logger.info("更新like记录");
			// 如果是赞同
			if ("0".equals(prodLike.getLikeState())) {
				// 减少赞同人数及赞同分数
				logger.info("减少赞同人数及赞同分数");
				Map<String, Object> reduceFundInfoMap = new HashMap<String, Object>();
				reduceFundInfoMap.put("fundCode", fundCode);
				reduceFundInfoMap.put("likeSum", -1);
				reduceFundInfoMap.put("likeScore", -1);
				fundMarketDao.updateLikeInfo(reduceFundInfoMap);

				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("取消赞同操作");

					// 删除这条赞同记录
					logger.info("删除这条赞同记录");
					prodLikeDao.deleteFundLike(prodLike);
					logger.debug("赞同记录删除成功");
				} else {
					// 更新赞同记录

					// 更新赞同类型为反对
					logger.info("更新赞同类型为反对");
					Map<String, Object> updateMarketLikeMap = new HashMap<String, Object>();
					updateMarketLikeMap.put("fundCode", fundCode);
					updateMarketLikeMap.put("userid", userid);
					updateMarketLikeMap.put("likeState", "1");
					prodLikeDao.updateFundMarketLike(updateMarketLikeMap);

					// 增加反对人数及赞同分数
					logger.info("增加反对人数及赞同分数");
					Map<String, Object> plusFundInfoMap = new HashMap<String, Object>();
					plusFundInfoMap.put("fundCode", fundCode);
					plusFundInfoMap.put("unLikeSum", 1);
					plusFundInfoMap.put("unLikeScore", 1);
					fundMarketDao.updateUnLikeInfo(plusFundInfoMap);
				}

				// 重新查询行情反对的分数
				logger.info("重新查询行情反对的分数");
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}

			// 如果是反对
			if ("1".equals(prodLike.getLikeState())) {
				// 减少反对人数及赞同分数
				logger.info("减少反对人数及赞同分数");
				Map<String, Object> plusFundInfoMap = new HashMap<String, Object>();
				plusFundInfoMap.put("fundCode", fundCode);
				plusFundInfoMap.put("unLikeSum", -1);
				plusFundInfoMap.put("unLikeScore", -1);
				fundMarketDao.updateUnLikeInfo(plusFundInfoMap);

				if (likeState.equals(prodLike.getLikeState())) {
					logger.info("取消反对操作");

					// 删除这条反对记录
					logger.info("删除这条反对记录");
					prodLikeDao.deleteFundLike(prodLike);
					logger.debug("反对记录删除成功");
				} else {
					// 更新反对类型为赞同
					logger.info("更新反对类型为赞同");
					Map<String, Object> updateMarketLikeMap = new HashMap<String, Object>();
					updateMarketLikeMap.put("fundCode", fundCode);
					updateMarketLikeMap.put("userid", userid);
					updateMarketLikeMap.put("likeState", "0");
					prodLikeDao.updateFundMarketLike(updateMarketLikeMap);

					// 增加赞同人数及赞同分数
					logger.info("增加赞同人数及赞同分数");
					Map<String, Object> reduceFundInfoMap = new HashMap<String, Object>();
					reduceFundInfoMap.put("fundCode", fundCode);
					reduceFundInfoMap.put("likeSum", 1);
					reduceFundInfoMap.put("likeScore", 1);
					fundMarketDao.updateLikeInfo(reduceFundInfoMap);
				}

				// 重新查询行情反对的分数
				logger.info("重新查询行情反对的分数");
				fundMarket = fundMarketDao.queryDetail(fundCode);
				logger.debug("行情赞同的分数 LikeScore={}", fundMarket.getLikeScore());
				logger.debug("行情反对的分数 unLikeScore={}", fundMarket.getUnLikeScore());
				return new Integer[] { fundMarket == null ? 0 : fundMarket.getLikeScore(),
						fundMarket == null ? 0 : fundMarket.getUnLikeScore() };
			}
		}

		return null;
	}

}
