package lihu.zlm.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import lihu.zlm.web.model.CollectUser;

@Repository("collectUserDao")
public class CollectUserDao extends BaseDao {

	public void insert(CollectUser collectUser) {
		this.getSqlMapClientTemplate().update("CollectUser.insert", collectUser);
	}
	
	public void delete(CollectUser collectUser) {
		this.getSqlMapClientTemplate().delete("CollectUser.delete", collectUser);
	}

	public CollectUser query(CollectUser collectUser) {
		return (CollectUser)this.getSqlMapClientTemplate().queryForObject("CollectUser.query", collectUser);
	}

	@SuppressWarnings("unchecked")
	public List<CollectUser> queryCollects(String userid) {
		return this.getSqlMapClientTemplate().queryForList("CollectUser.queryCollects", userid);
	}

	public CollectUser queryByFundCode(CollectUser collectUser) {
		return (CollectUser)this.getSqlMapClientTemplate().queryForObject("CollectUser.queryByFundCode", collectUser);
	}

	public void deleteByFundCode(CollectUser collectUser) {
		this.getSqlMapClientTemplate().delete("CollectUser.deleteByFundCode", collectUser);
	}
	
}
