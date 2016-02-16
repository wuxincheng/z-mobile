package lihu.zlm.web.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import lihu.zlm.web.model.Comment;

/**
 * 评论内容Dao
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月18日 下午8:45:30 
 *
 */
@Component("commentDao")
public class CommentDao extends BaseDao {

	/**
	 * 发表评论
	 * 
	 * @param commment
	 *            封装的评论内容
	 * @return
	 */
	public Integer post(Comment commment) {
		return (Integer) this.getSqlMapClientTemplate().update("Comment.post", commment);
	}

	/**
	 * 根据产品主键查询评论列表
	 * 
	 * @param productid
	 *            产品主键
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> queryByProductid(String productid) {
		return this.getSqlMapClientTemplate().queryForList("Comment.queryByProductid", productid);
	}

	/**
	 * 根据基金代码查询行情评论列表
	 * 
	 * @param fundCode
	 *            基金代码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> queryByFundCode(String fundCode) {
		return this.getSqlMapClientTemplate().queryForList("Comment.queryByFundCode", fundCode);
	}

	/**
	 * 根据产品集主键查询行情评论列表
	 * 
	 * @param collectid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> queryByCollectid(String collectid) {
		return this.getSqlMapClientTemplate().queryForList("Comment.queryByCollectid", collectid);
	}

}
