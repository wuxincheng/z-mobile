package lihu.zlm.web.oauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 新浪微博相关配置
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月7日 下午1:45:32
 * 
 */
@Component
public class WeiboConfig {

	@Value("#{weiboSettings.appKey}")
	private String appKey;

	@Value("#{weiboSettings.appSercet}")
	private String appSercet;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSercet() {
		return appSercet;
	}

	public void setAppSercet(String appSercet) {
		this.appSercet = appSercet;
	}

}
