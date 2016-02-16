package lihu.zlm.web.service;

import java.util.List;

import javax.annotation.Resource;

import lihu.zlm.web.dao.AdvisorDao;
import lihu.zlm.web.model.Advisor;

import org.springframework.stereotype.Service;

@Service("advisorService")
public class AdvisorService {

	@Resource
	private AdvisorDao advisorDao;
	
	/**
	 * 查询热门的理财师
	 * 
	 * @param topLimit
	 * @return 理财师
	 */
	public List<Advisor> queryTopHot(int topLimit) {
		return advisorDao.queryTopHot(topLimit);
	}

}
