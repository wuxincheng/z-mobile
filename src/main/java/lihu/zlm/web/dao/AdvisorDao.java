package lihu.zlm.web.dao;

import java.util.List;

import lihu.zlm.web.model.Advisor;

import org.springframework.stereotype.Repository;

/**
 * 理财师（目前无理财师表，统计出的用户）
 * 
 * @author wuxincheng(wxcking)
 * 
 * @Date 2016年1月27日 下午7:54:05
 * 
 */
@Repository("advisorDao")
public class AdvisorDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Advisor> queryTopHot(int topLimit) {
		return this.getSqlMapClientTemplate().queryForList("Advisor.queryTopHot", topLimit);
	}

}
