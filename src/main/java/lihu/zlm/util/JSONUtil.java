package lihu.zlm.util;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;

/**
 * JSON工具类
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月4日 上午10:37:01 
 *
 */
public class JSONUtil {

	/**
	 * 将JSON字符串转换成Map
	 * 
	 * @param responseString
	 * @return
	 */
	public static Map<String, Object> parseResponseMap(String responseString){
		if (StringUtils.isEmpty(responseString)) {
			return null;
		}
		
		// 将JSON字符串转换成JSON对象
		JSONObject jsonObject = JSONObject.fromObject(responseString);
		
		// 将JSON转换成Map
		@SuppressWarnings("unchecked")
		Map<String, Object> responseMap = jsonObject;
		
		return responseMap;
	}
	
}
