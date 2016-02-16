package lihu.zlm.web;

import java.util.List;
import java.util.Map;

import lihu.zlm.web.model.Product;

/**
 * 分页
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月27日 下午5:28:35
 * 
 */
public class Pager {

	private Integer pagerSize;

	private Integer currentPager;

	private Integer lastPager;

	private List<Map<String, List<Product>>> productMapList;

	public Integer getPagerSize() {
		return pagerSize;
	}

	public void setPagerSize(Integer pagerSize) {
		this.pagerSize = pagerSize;
	}

	public Integer getCurrentPager() {
		return currentPager;
	}

	public void setCurrentPager(Integer currentPager) {
		this.currentPager = currentPager;
	}

	public Integer getLastPager() {
		return lastPager;
	}

	public void setLastPager(Integer lastPager) {
		this.lastPager = lastPager;
	}

	public List<Map<String, List<Product>>> getProductMapList() {
		return productMapList;
	}

	public void setProductMapList(List<Map<String, List<Product>>> productMapList) {
		this.productMapList = productMapList;
	}

}
