package lihu.zlm.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lihu.zlm.web.model.Collect;

/**
 * 榜单Dao
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月24日 下午10:13:02
 * 
 */
@Repository("collectDao")
public class CollectDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Collect> queryAll() {
		return this.getSqlMapClientTemplate().queryForList("Collect.queryAll");
	}

	public Collect queryDetailByCollectid(String collectid) {
		return (Collect) this.getSqlMapClientTemplate().queryForObject("Collect.queryDetailByCollectid", collectid);
	}

	public void addProductSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.addProductSum", collectid);
	}

	public void removeProductSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.removeProductSum", collectid);
	}

	public void addCollectSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.addCollectSum", collectid);
	}

	public void cutCollectSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.cutCollectSum", collectid);
	}

	@SuppressWarnings("unchecked")
	public List<Collect> queryByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("Collect.queryByUserid", userid);
	}

	public void delete(String collectid) {
		this.getSqlMapClientTemplate().delete("Collect.delete", collectid);
	}

	public void update(Collect collect) {
		this.getSqlMapClientTemplate().update("Collect.update", collect);
	}

	@SuppressWarnings("unchecked")
	public List<Collect> queryTopHot(Integer topLimit) {
		return this.getSqlMapClientTemplate().queryForList("Collect.queryTopHot", topLimit);
	}

	public void plusCommentSum(String collectid) {
		this.getSqlMapClientTemplate().update("Collect.plusCommentSum", collectid);
	}

	public void plusLikeScore(Map<String, Object> updateMap) {
		this.getSqlMapClientTemplate().update("Collect.likeScore", updateMap);
	}

	public void updateLikeInfo(Map<String, Object> updateMap) {
		this.getSqlMapClientTemplate().update("Collect.updateLikeInfo", updateMap);
	}

	public void updateUnLikeInfo(Map<String, Object> updateMap) {
		this.getSqlMapClientTemplate().update("Collect.updateUnLikeInfo", updateMap);
	}

}
