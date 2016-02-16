package lihu.zlm.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lihu.zlm.util.Constants;
import lihu.zlm.web.dao.FundMarketDao;
import lihu.zlm.web.dao.ProdLikeDao;
import lihu.zlm.web.model.FundMarket;
import lihu.zlm.web.model.Product;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 基金行情信息服务类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月20日 上午8:53:38
 * 
 */
@Service("fundMarketService")
public class FundMarketService {
	private static final Logger logger = LoggerFactory.getLogger(FundMarketService.class);

	@Resource
	private FundMarketDao fundMarketDao;

	@Resource
	private ProdLikeDao prodLikeDao;

	/**
	 * 分页查询行情列表信息
	 * 
	 * @param queryParam
	 *            分页参数
	 * @param dataType
	 *            获取类型
	 * @return
	 */
	public Map<String, Object> queryPager(Map<String, Object> queryParam, String dataType) {
		Map<String, Object> result = new HashMap<String, Object>();

		List<FundMarket> fundMarkets = null; // 查询的数据
		int totalCount = 0; // 查询出总记录数

		if (Constants.DATE_TYPE_DB.equals(dataType)) { // 直接访问数据库
			fundMarkets = fundMarketDao.queryPager(queryParam);
			totalCount = fundMarketDao.queryCount(queryParam); // 总记录数
		} else { // 从缓存中获取
			if (StringUtils.isNotEmpty((String) queryParam.get("keyword"))) {
				fundMarkets = queryFundMarkets((String) queryParam.get("keyword"));
			} else {
				fundMarkets = getCacheFundMarkets();
			}

			totalCount = fundMarkets.size(); // 总记录数据
			if ((Integer) queryParam.get("end") < totalCount) {
				fundMarkets = fundMarkets.subList((Integer) queryParam.get("start"), (Integer) queryParam.get("end"));
			} else {
				fundMarkets = fundMarkets.subList((Integer) queryParam.get("start"), totalCount);
			}
		}

		result.put("fundMarkets", fundMarkets);
		result.put("totalCount", totalCount);

		return result;
	}

	/**
	 * 获取热门前几名
	 * 
	 * @param topIndex
	 *            范围5-100
	 * @return
	 */
	public List<FundMarket> queryTopHot(Integer topIndex) {
		// 如果为空, 或是不在5到100之前不做处理
		if (null == topIndex || topIndex < 5 || topIndex > 100) {
			return null;
		}

		// 从缓存中读取行情信息
		List<FundMarket> fundMarkets = getCacheFundMarkets();
		if (null == fundMarkets || fundMarkets.size() < topIndex) {
			return fundMarkets;
		}
		
		fundMarkets = fundMarkets.subList(0, topIndex);

		return fundMarkets;
	}

	/**
	 * 查询赞同排名
	 * 
	 * @return
	 */
	public List<FundMarket> queryRedTopHot(String dataType) {
		// 直接查询数据库获取数据
		if (Constants.DATE_TYPE_DB.equals(dataType)) {
			return fundMarketDao.queryTopRedMarkets();
		}

		// 验证是否使用缓存数据
		if (!Constants.DATE_TYPE_CACHE.equals(dataType)) {
			return null;
		}

		// 获取缓存数据
		List<FundMarket> fundMarkets = getCacheFundMarkets();
		List<FundMarket> sortFundMarkets = new ArrayList<FundMarket>();
		sortFundMarkets.addAll(fundMarkets);

		// 排序
		Collections.sort(sortFundMarkets, new Comparator<FundMarket>() {
			@Override
			public int compare(FundMarket fund1, FundMarket fund2) {
				return fund2.getLikeScore() == fund1.getLikeScore() ? 0
						: (fund2.getLikeScore() > fund1.getLikeScore() ? 1 : -1);
			}
		});

		if (null == sortFundMarkets || sortFundMarkets.size() < 5) {
			return sortFundMarkets;
		}
		
		return sortFundMarkets.subList(0, 5);
	}

	/**
	 * 查询反对排名
	 * 
	 * @return
	 */
	public List<FundMarket> queryGreenTopHot(String dataType) {
		// 直接查询数据库获取数据
		if (Constants.DATE_TYPE_DB.equals(dataType)) {
			return fundMarketDao.queryTopGreenMarkets();
		}

		// 验证是否使用缓存数据
		if (!Constants.DATE_TYPE_CACHE.equals(dataType)) {
			return null;
		}

		// 获取缓存数据
		List<FundMarket> fundMarkets = getCacheFundMarkets();
		List<FundMarket> sortFundMarkets = new ArrayList<FundMarket>();
		sortFundMarkets.addAll(fundMarkets);

		// 排序
		Collections.sort(sortFundMarkets, new Comparator<FundMarket>() {
			@Override
			public int compare(FundMarket fund1, FundMarket fund2) {
				return fund2.getUnLikeScore() == fund1.getUnLikeScore() ? 0 : (fund2.getUnLikeScore() > fund1
						.getUnLikeScore() ? 1 : -1);
			}
		});

		if (null == sortFundMarkets || sortFundMarkets.size() < 5) {
			return sortFundMarkets;
		}
		
		return sortFundMarkets.subList(0, 5);
	}

	/**
	 * 查询所有的基金公司
	 * 
	 * @return
	 */
	public List<String> queryFundCompanys() {
		List<String> fundCompanys = new ArrayList<String>();

		// 从缓存中读取行情信息
		List<FundMarket> fundMarkets = getCacheFundMarkets();
		for (FundMarket fundMarket : fundMarkets) {
			boolean flag = false;
			if (StringUtils.isNotEmpty(fundMarket.getFundCompany())) {
				for (String fundCompany : fundCompanys) {
					if (fundMarket.getFundCompany().equals(fundCompany)) {
						flag = true;
					}
				}
			}
			if (!flag) {
				fundCompanys.add(fundMarket.getFundCompany());
			}
		}

		return fundCompanys;
	}

	/**
	 * 查询所有的基金经理
	 * 
	 * @return
	 */
	public List<String> queryFundManagers() {
		List<String> fundManagers = new ArrayList<String>();

		// 从缓存中读取行情信息
		List<FundMarket> fundMarkets = getCacheFundMarkets();
		for (FundMarket fundMarket : fundMarkets) {
			boolean flag = false;
			if (StringUtils.isNotEmpty(fundMarket.getFundManager())) {
				for (String fundManager : fundManagers) {
					if (fundMarket.getFundManager().equals(fundManager)) {
						flag = true;
					}
				}
			}
			if (!flag) {
				fundManagers.add(fundMarket.getFundManager());
			}
		}

		return fundManagers;
	}

	/**
	 * 根据关键字查询详细基金信息
	 * 
	 * @param keyword
	 * @return
	 */
	public List<FundMarket> queryFundMarkets(String keyword) {
		if (StringUtils.isEmpty(keyword)) {
			return null;
		}

		// 存储查询基金信息
		List<FundMarket> funds = new ArrayList<FundMarket>();

		// 从缓存中读取行情信息
		List<FundMarket> fundMarkets = getCacheFundMarkets();

		for (FundMarket fundMarket : fundMarkets) {
			// 过滤基金代码fundCode
			if (StringUtils.isNotEmpty(fundMarket.getFundCode())) {
				if (fundMarket.getFundCode().indexOf(keyword) >= 0) {
					funds.add(fundMarket);
					continue;
				}
			}

			// 过滤基金名称fundName
			if (StringUtils.isNotEmpty(fundMarket.getFundName())) {
				if (fundMarket.getFundName().indexOf(keyword) >= 0) {
					funds.add(fundMarket);
					continue;
				}
			}

			// 过滤基金公司fundCompany
			if (StringUtils.isNotEmpty(fundMarket.getFundCompany())) {
				if (fundMarket.getFundCompany().indexOf(keyword) >= 0) {
					funds.add(fundMarket);
					continue;
				}
			}

			// 过滤基金经理fundManager
			if (StringUtils.isNotEmpty(fundMarket.getFundManager())) {
				if (fundMarket.getFundManager().indexOf(keyword) >= 0) {
					funds.add(fundMarket);
					continue;
				}
			}
		}

		return funds;
	}

	/**
	 * 根据关键字查询基金信息
	 * 
	 * @param keyword
	 *            关键字fundCode或fundName
	 * @return
	 */
	public List<Map<String, String>> queryFunds(String keyword) {
		if (StringUtils.isEmpty(keyword)) {
			return null;
		}

		// 存储查询基金信息
		List<Map<String, String>> funds = new ArrayList<Map<String, String>>();

		// 从缓存中读取行情信息
		List<FundMarket> fundMarkets = getCacheFundMarkets();

		for (FundMarket fundMarket : fundMarkets) {
			// 只存储基金名称(fundName)和基金代码(fundCode)
			Map<String, String> fund = new HashMap<String, String>();
			// 过滤基金代码fundCode
			if (StringUtils.isNotEmpty(fundMarket.getFundCode())) {
				if (fundMarket.getFundCode().indexOf(keyword) >= 0) {
					fund.put("fundCode", fundMarket.getFundCode());
					fund.put("fundName", fundMarket.getFundName());
					funds.add(fund);
					continue;
				}
			}

			// 过滤基金名称fundName
			if (StringUtils.isNotEmpty(fundMarket.getFundName())) {
				if (fundMarket.getFundName().indexOf(keyword) >= 0) {
					fund.put("fundCode", fundMarket.getFundCode());
					fund.put("fundName", fundMarket.getFundName());
					funds.add(fund);
					continue;
				}
			}
		}

		return funds;
	}

	/**
	 * 从缓存中获取基金行情信息
	 * 
	 * @return
	 */
	public List<FundMarket> getCacheFundMarkets() {
		logger.info("读取基金行情并放入缓存（移动端未实现）");
		List<FundMarket> fundMarkets = fundMarketDao.queryAll();
		return fundMarkets;
	}

	/**
	 * 根据产品列表查询基金信息
	 * 
	 * @param products
	 * @return
	 */
	public List<FundMarket> queryByProducts(List<Product> products) {
		if (null == products || products.size() < 1) {
			return null;
		}

		// 存放查询出来的基金列表
		List<FundMarket> fundMarkets = new ArrayList<FundMarket>();

		// 从缓存中查询基金列表
		List<FundMarket> cacheFundMarkets = getCacheFundMarkets();

		for (Product product : products) {
			if (StringUtils.isNotEmpty(product.getFundCode())) {
				for (FundMarket cacheFundMarket : cacheFundMarkets) {
					if (StringUtils.isNotEmpty(cacheFundMarket.getFundCode())) {
						if (product.getFundCode().equals(cacheFundMarket.getFundCode())) {
							cacheFundMarket.setFundMemo(product.getMemo());
							// 表示当前用户已经关注
							cacheFundMarket.setThisUserFocus(product.getLikeState());
							cacheFundMarket.setProdid(product.getProdid());
							fundMarkets.add(cacheFundMarket);
							break;
						}
					}
				}
			}
		}

		return fundMarkets;
	}

	/**
	 * 根据基金代码查询行情详细信息
	 * 
	 * @param fundCode
	 * @return
	 */
	public FundMarket queryDetailByFundCode(String fundCode) {
		return fundMarketDao.queryDetail(fundCode);
	}

}
