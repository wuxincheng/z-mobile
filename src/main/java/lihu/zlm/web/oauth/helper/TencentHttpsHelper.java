package lihu.zlm.web.oauth.helper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.qq.connect.utils.QQConnectConfig;
import lihu.zlm.util.JSONUtil;

/**
 * QQ Https请求
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月4日 上午9:29:02
 * 
 */
@Component
public class TencentHttpsHelper {
	private static final Logger logger = LoggerFactory.getLogger(TencentHttpsHelper.class);

	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 * @param userOpenID
	 * @return
	 */
	public Map<String, Object> getUserInfo(String accessToken, String userOpenID) {
		logger.info("获取QQ用户信息");

		if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(userOpenID)) {
			logger.info("accessToken或userOpenID为空");
			return null;
		}

		// 发送获取请求
		logger.info("发送获取请求");

		String userInfoURL = QQConnectConfig.getValue("getUserInfoURL") + "?access_token=" + accessToken
				+ "&oauth_consumer_key=" + QQConnectConfig.getValue("app_ID") + "&openid=" + userOpenID;
		logger.info("获取用户信息 userInfoURL={}", userInfoURL);

		String response = null;
		try {
			response = HttpsConnection.doGet(userInfoURL);
			logger.debug("获取QQ用户信息请求发送成功");
		} catch (Exception e) {
			logger.error("获取QQ用户信息请求出现异常", e);
		}

		// 处理返回数据
		Map<String, Object> userInfoMap = JSONUtil.parseResponseMap(response);
		logger.info("获取QQ用户信息请求返回 userInfoMap={}", userInfoMap);

		return userInfoMap;
	}

}
