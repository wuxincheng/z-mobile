package lihu.zlm.util;

import org.apache.commons.lang.StringUtils;

/**
 * markdown标签处理
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年9月21日 上午10:50:40 
 *
 */
public class MarkDownUtil {

	public static String filterLink(String cotent){
		if (StringUtils.isEmpty(cotent)) {
			return null;
		}
		
		return cotent.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}
	
}
