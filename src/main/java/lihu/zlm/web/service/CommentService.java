package lihu.zlm.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lihu.zlm.web.dao.CollectDao;
import lihu.zlm.web.dao.CommentDao;
import lihu.zlm.web.dao.FundMarketDao;
import lihu.zlm.web.dao.ProductDao;
import lihu.zlm.web.model.Comment;
import lihu.zlm.util.Constants;
import lihu.zlm.util.MarkDownUtil;

/**
 * 评论
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月29日 下午3:17:13
 * 
 */
@Service("commentService")
public class CommentService {
	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

	@Resource
	private CommentDao commentDao;

	@Resource
	private ProductDao productDao;
	
	@Resource
	private FundMarketDao fundMarketDao;
	
	@Resource
	private CollectDao collectDao;

	/**
	 * 发表回复
	 */
	public String post(Comment comment, String userid) {
		// 返回信息
		String responseMessage = null;
		
		if (StringUtils.isEmpty(userid)) {
			responseMessage = "用户登录无效";
			logger.warn(responseMessage);
			return responseMessage;
		}
		
		if (StringUtils.isEmpty(comment.getContent())) {
			responseMessage = "评论内容不能为空";
			logger.warn(responseMessage);
			return responseMessage;
		}
		
		// 处理HTML中的A标签
		comment.setContent(MarkDownUtil.filterLink(comment.getContent()));
		
		try {
			// 发表评论
			comment.setUserid(userid);
			comment.setCommentState(Constants.DEFAULT_STATE);
			comment.setLikeSum(0);
			comment.setReplySum(0);
			commentDao.post(comment);
			logger.info("评论发表成功");
		} catch (Exception e) {
			responseMessage = "添加评论异常";
			logger.error(responseMessage, e);
			return responseMessage;
		}

		// 基金行情的评论
		if (StringUtils.isNotEmpty(comment.getFundCode())) {
			// 更新基金行情的评论数
			logger.info("更新基金行情的评论数");
			fundMarketDao.plusCommentSum(comment.getFundCode());
			
			// 更新基金行情的关注度
			logger.info("更新基金行情的关注度");
			Map<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("fundCode", comment.getFundCode());
			updateMap.put("likeScore", 5); // 一次评论增加5分
			fundMarketDao.plusLikeScore(updateMap);
		}
		
		// 产品集的评论
		if (StringUtils.isNotEmpty(comment.getCollectid())) {
			// 更新产品集的评论数
			collectDao.plusCommentSum(comment.getCollectid());
			
			// 更新产品集关注度
			Map<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("collectid", comment.getCollectid());
			updateMap.put("likeScore", 5);
			collectDao.plusLikeScore(updateMap);
		}
		
		// 产品的评论
		if (StringUtils.isNotEmpty(comment.getProductid())) {
			// 更新产品的评论数
			productDao.plusCommentSum(comment.getProductid());

			// 更新产品的关注度
			Map<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("prodid", comment.getProductid());
			updateMap.put("score", 3);
			productDao.score(updateMap);
		}

		return responseMessage;
	}

	/**
	 * 根据产品ID查询评论列表
	 */
	public List<Comment> queryByProductid(String productid) {
		return commentDao.queryByProductid(productid);
	}

	/**
	 * 根据基金代码查询行情评论列表
	 * 
	 * @param fundCode
	 *            基金代码
	 * @return
	 */
	public List<Comment> queryByFundCode(String fundCode) {
		if (StringUtils.isEmpty(fundCode)) {
			return null;
		}

		return commentDao.queryByFundCode(fundCode);
	}

	/**
	 * 根据collectid查询评论列表
	 * 
	 * @param collectid
	 * @return
	 */
	public List<Comment> queryByCollectid(String collectid) {
		if (StringUtils.isEmpty(collectid)) {
			return null;
		}

		return commentDao.queryByCollectid(collectid);
	}

}
