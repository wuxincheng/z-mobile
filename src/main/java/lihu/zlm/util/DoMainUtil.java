package lihu.zlm.util;

import org.apache.commons.lang.StringUtils;

public class DoMainUtil {

	public static final String IP_WUXINCHENG_COM_CN = "115.28.156.183";
	
	public static final String DOMAIN_WUXINCHENG_COM_CN = "wuxincheng.com.cn";
	
	public static String domainFormart (String url) {
		if (StringUtils.isNotEmpty(url)) {
			return url.replace(IP_WUXINCHENG_COM_CN, DOMAIN_WUXINCHENG_COM_CN);
		}
		
		return "";
	}
	
}
