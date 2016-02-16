package lihu.zlm.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lihu.zlm.web.model.ProdLike;

@Repository("prodLikeDao")
public class ProdLikeDao extends BaseDao {

	public void insert(ProdLike prodLike) {
		this.getSqlMapClientTemplate().update("ProdLike.insert", prodLike);
	}

	public void delete(ProdLike prodLike) {
		this.getSqlMapClientTemplate().delete("ProdLike.delete", prodLike);
	}

	public ProdLike query(ProdLike prodLike) {
		return (ProdLike) this.getSqlMapClientTemplate().queryForObject("ProdLike.query", prodLike);
	}

	@SuppressWarnings("unchecked")
	public List<ProdLike> queryByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("ProdLike.queryByUserid", userid);
	}

	@SuppressWarnings("unchecked")
	public List<ProdLike> queryLikeUserDetail(String prodid) {
		return this.getSqlMapClientTemplate().queryForList("ProdLike.queryLikeUserDetail", prodid);
	}

	public ProdLike queryDetailByFundCode(Map<String, String> queryMap) {
		return (ProdLike) this.getSqlMapClientTemplate().queryForObject(
				"ProdLike.queryDetailByFundCode", queryMap);
	}
	
	public ProdLike queryDetailByCollectid(Map<String, String> queryMap) {
		return (ProdLike) this.getSqlMapClientTemplate().queryForObject(
				"ProdLike.queryDetailByCollectid", queryMap);
	}

	public void updateFundMarketLike(Map<String, Object> updateMarketLikeMap) {
		this.getSqlMapClientTemplate().update("ProdLike.updateFundMarketLike", updateMarketLikeMap);
	}
	
	public void updateCollectLike(Map<String, Object> updateCollectLikeMap) {
		this.getSqlMapClientTemplate().update("ProdLike.updateCollectLike", updateCollectLikeMap);
	}

	/**
	 * 删除基金行情赞同反对记录
	 * 
	 * @param prodLike fundCode/userid
	 */
	public void deleteFundLike(ProdLike prodLike) {
		this.getSqlMapClientTemplate().delete("ProdLike.deleteFundLike", prodLike);
	}
	
	/**
	 * 删除产品集赞同反对记录
	 * 
	 * @param prodLike fundCode/userid
	 */
	public void deleteCollectLike(ProdLike prodLike) {
		this.getSqlMapClientTemplate().delete("ProdLike.deleteCollectLike", prodLike);
	}

}
