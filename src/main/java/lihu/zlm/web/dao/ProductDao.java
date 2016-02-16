package lihu.zlm.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lihu.zlm.web.model.Product;

/**
 * 产品及产品相关（赞）的数据库操作
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月7日 上午8:49:12
 * 
 */
@Repository("productDao")
public class ProductDao extends BaseDao {

	/**
	 * 发布产品
	 */
	public void post(Product product) {
		this.getSqlMapClientTemplate().update("Product.post", product);
	}

	/**
	 * 统计出发布的日期
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryGroupByDate() {
		return this.getSqlMapClientTemplate().queryForList("Product.queryGroupByDate");
	}

	/**
	 * 根据发布日期查询产品列表
	 * 
	 * @param queryMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> queryByPostDate(Map<String, String> queryMap) {
		return this.getSqlMapClientTemplate().queryForList("Product.queryByPostDate", queryMap);
	}

	public Product queryDetailByProdid(Map<String, String> queryMap) {
		return (Product) this.getSqlMapClientTemplate().queryForObject("Product.queryDetailByProdid", queryMap);
	}

	/**
	 * 更新产品的评论数
	 */
	public void plusCommentSum(String prodid) {
		this.getSqlMapClientTemplate().update("Product.plusCommentSum", prodid);
	}

	@SuppressWarnings("unchecked")
	public List<Product> queryProductsByCollectid(Map<String, String> queryMap) {
		return this.getSqlMapClientTemplate().queryForList("Product.queryProductsByCollectid", queryMap);
	}

	public void score(Map<String, Object> like) {
		this.getSqlMapClientTemplate().update("Product.score", like);
	}

	public void postLikeScore(Map<String, Object> likeScore) {
		this.getSqlMapClientTemplate().update("Product.postLikeScore", likeScore);
	}

	@SuppressWarnings("unchecked")
	public List<Product> queryPostByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("Product.queryPostByUserid", userid);
	}

	@SuppressWarnings("unchecked")
	public List<Product> queryLikeByUserid(String userid) {
		return this.getSqlMapClientTemplate().queryForList("Product.queryLikeByUserid", userid);
	}

	/**
	 * 登录用户查询其它用户赞过这个用户产品的列表
	 */
	@SuppressWarnings("unchecked")
	public List<Product> queryUserMain(Map<String, String> queryMap) {
		return this.getSqlMapClientTemplate().queryForList("Product.queryUserMain", queryMap);
	}

	@SuppressWarnings("unchecked")
	public List<Product> queryCollectProductUser(Map<String, String> queryMap) {
		return this.getSqlMapClientTemplate().queryForList("Product.queryCollectProductUser", queryMap);
	}

	public void remove(Map<String, String> removeMap) {
		this.getSqlMapClientTemplate().delete("Product.remove", removeMap);
	}

}
