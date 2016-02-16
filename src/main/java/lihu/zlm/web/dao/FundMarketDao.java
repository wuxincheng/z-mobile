package lihu.zlm.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lihu.zlm.web.model.FundMarket;

/**
 * 当天基金行情信息
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月16日 下午5:13:09
 * 
 */
@Component("fundMarketDao")
public class FundMarketDao extends BaseDao {

	/**
	 * 插入一条基金行情记录
	 * 
	 * @param fundMarket
	 *            基金行情
	 */
	public void insert(FundMarket fundMarket) {
		this.getSqlMapClientTemplate().update("FundMarket.insert", fundMarket);
	}

	/**
	 * 更新一条基金行情信息
	 * 
	 * @param fundMarketFlag
	 *            基金行情
	 */
	public void update(FundMarket fundMarket) {
		this.getSqlMapClientTemplate().update("FundMarket.update", fundMarket);
	}

	/**
	 * 查询所有基金行情信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryAll");
	}

	/**
	 * 查询所有基金经理为空的行情列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryAllNull() {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryAllNull");
	}

	/**
	 * 分页查询基金行情列表
	 * 
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryPager(Map<String, Object> queryParam) {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryPager", queryParam);
	}

	/**
	 * 统计所有基金行情记录数
	 * 
	 * @param queryParam
	 * @return
	 */
	public int queryCount(Map<String, Object> queryParam) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("FundMarket.queryCount",
				queryParam);
	}

	/**
	 * 根据基金代码查询基金行情详细信息
	 * 
	 * @param fundCode
	 * @return
	 */
	public FundMarket queryDetail(String fundCode) {
		return (FundMarket) this.getSqlMapClientTemplate().queryForObject("FundMarket.queryDetail",
				fundCode);
	}

	/**
	 * 更新基金行情的评论数
	 */
	public void plusCommentSum(String fundCode) {
		this.getSqlMapClientTemplate().update("FundMarket.plusCommentSum", fundCode);
	}

	/**
	 * 更新基金行情关注度
	 * 
	 * @param scoreMap
	 */
	public void plusLikeScore(Map<String, Object> scoreMap) {
		this.getSqlMapClientTemplate().update("FundMarket.likeScore", scoreMap);
	}

	/**
	 * 增加关注度
	 * 
	 * @param likeScore
	 */
	public void postLikeScore(Map<String, Object> likeScore) {
		this.getSqlMapClientTemplate().update("FundMarket.postLikeScore", likeScore);
	}

	/**
	 * 根据fundCode更新likeSum和likeScore
	 * 
	 * @param updateMap
	 */
	public void updateLikeInfo(Map<String, Object> updateMap) {
		this.getSqlMapClientTemplate().update("FundMarket.updateLikeInfo", updateMap);
	}

	/**
	 * 根据fundCode更新unLikeSum和unLikeScore
	 * 
	 * @param updateMap
	 */
	public void updateUnLikeInfo(Map<String, Object> updateMap) {
		this.getSqlMapClientTemplate().update("FundMarket.updateUnLikeInfo", updateMap);
	}

	/**
	 * 查询红榜
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryTopRedMarkets() {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryTopRedMarkets");
	}

	/**
	 * 查询绿榜
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FundMarket> queryTopGreenMarkets() {
		return this.getSqlMapClientTemplate().queryForList("FundMarket.queryTopGreenMarkets");
	}

	/**
	 * 更新关注度和关注人数
	 * 
	 * @param updateInfoMap
	 */
	public void updateFocusInfo(Map<String, Object> updateInfoMap) {
		this.getSqlMapClientTemplate().update("FundMarket.updateFocusInfo", updateInfoMap);
	}

}
