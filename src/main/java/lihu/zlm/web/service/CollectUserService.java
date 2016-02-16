package lihu.zlm.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lihu.zlm.web.dao.CollectDao;
import lihu.zlm.web.dao.CollectUserDao;
import lihu.zlm.web.dao.FundMarketDao;
import lihu.zlm.web.model.CollectUser;
import lihu.zlm.util.Constants;
import lihu.zlm.util.DateUtil;

@Service("collectUserService")
public class CollectUserService {

	@Resource
	private CollectUserDao collectUserDao;

	@Resource
	private CollectDao collectDao;
	
	@Resource
	private FundMarketDao fundMarketDao;

	public void insert(CollectUser collectUser) {
		collectUserDao.insert(collectUser);
	}

	public void delete(CollectUser collectUser) {
		collectUserDao.delete(collectUser);
	}

	public CollectUser query(String collectid, String userid) {
		CollectUser collectUser = new CollectUser();
		collectUser.setCollectid(collectid);
		collectUser.setUserid(userid);
		return collectUser = collectUserDao.query(collectUser);
	}
	
	public CollectUser queryByFundCode(String fundCode, String userid) {
		CollectUser collectUser = new CollectUser();
		collectUser.setFundCode(fundCode);
		collectUser.setUserid(userid);
		return collectUser = collectUserDao.queryByFundCode(collectUser);
	}

	/**
	 * 收藏
	 * 
	 * @param collectid
	 * @param userid
	 */
	public void collect(String collectid, String userid) {
		CollectUser deleteOrQueryCollectUser = new CollectUser();
		deleteOrQueryCollectUser.setCollectid(collectid);
		deleteOrQueryCollectUser.setUserid(userid);

		CollectUser querycu = collectUserDao.query(deleteOrQueryCollectUser);
		if (null == querycu) { // 收藏
			CollectUser collectUser = new CollectUser();
			collectUser.setCollectid(collectid);
			collectUser.setUserid(userid);
			collectUser.setCreateTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:ss"));
			collectUser.setCollectState(Constants.DEFAULT_STATE);

			collectUserDao.insert(collectUser);

			// 产品集收藏+1
			collectDao.addCollectSum(collectid);
		} else { // 取消收藏
			collectUserDao.delete(deleteOrQueryCollectUser);

			// 产品集收藏-1
			collectDao.cutCollectSum(collectid);
		}
	}
	
	/**
	 * 收藏基金
	 * 
	 * @param collectid
	 * @param userid
	 */
	public String focusFund(String fundCode, String userid) {
		CollectUser deleteOrQueryCollectUser = new CollectUser();
		deleteOrQueryCollectUser.setFundCode(fundCode);
		deleteOrQueryCollectUser.setUserid(userid);

		CollectUser querycu = collectUserDao.queryByFundCode(deleteOrQueryCollectUser);
		if (null == querycu) { // 收藏
			CollectUser collectUser = new CollectUser();
			collectUser.setFundCode(fundCode);
			collectUser.setUserid(userid);
			collectUser.setCreateTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:ss"));
			collectUser.setCollectState(Constants.DEFAULT_STATE);

			collectUserDao.insert(collectUser);

			// 基金产品收藏人数+1, 分数增加5分
			Map<String, Object> updateInfoMap = new HashMap<String, Object>();
			updateInfoMap.put("focusSum", 1);
			updateInfoMap.put("focusScore", 5);
			updateInfoMap.put("fundCode", fundCode);
			fundMarketDao.updateFocusInfo(updateInfoMap);
			
			return "1";
		} else { // 取消收藏
			collectUserDao.deleteByFundCode(deleteOrQueryCollectUser);

			// 基金产品收藏人数-1, 分数减少5分
			Map<String, Object> updateInfoMap = new HashMap<String, Object>();
			updateInfoMap.put("focusSum", -1);
			updateInfoMap.put("focusScore", -5);
			updateInfoMap.put("fundCode", fundCode);
			fundMarketDao.updateFocusInfo(updateInfoMap);
			
			return "0";
		}
	}

	/**
	 * 根据用户主键查询产品集主键
	 * 
	 * @param userid
	 * @return
	 */
	public List<CollectUser> queryCollects(String userid) {
		if (null == userid) {
			return null;
		}
		return collectUserDao.queryCollects(userid);
	}

}
