package lihu.zlm.util;

import org.apache.commons.lang.StringUtils;

/**
 * 基金标签处理
 * 
 * @author wuxincheng(wxcking) 
 * @date 2015年8月18日 上午9:20:32 
 *
 */
public class FundTldUtil {

	/**
	 * 显示基金风险级别名称
	 * 
	 * @param fundRiskLevel
	 * @return
	 */
	public static String fundRiskLevelName(String fundRiskLevel){
		if (StringUtils.isEmpty(fundRiskLevel)) {
			return null;
		}
		
		if ("1".equals(fundRiskLevel)) {
			return "低风险";
		}
		
		if ("2".equals(fundRiskLevel)) {
			return "中低风险";
		}
		
		if ("3".equals(fundRiskLevel)) {
			return "中高风险";
		}
		
		if ("4".equals(fundRiskLevel)) {
			return "高风险";
		}
		
		if ("5".equals(fundRiskLevel)) {
			return "中风险";
		}
		
		return null;
	}
	
	/**
	 * 基金类型名称
	 * 
	 * @param fundType
	 * @return
	 */
	public static String fundTypeName(String fundType){
		if (StringUtils.isEmpty(fundType)) {
			return null;
		}
		
		if ("1".equals(fundType)) {
			return "股票型";
		}
		
		if ("2".equals(fundType)) {
			return "债券型";
		}
		
		if ("3".equals(fundType)) {
			return "混合型";
		}
		
		if ("4".equals(fundType)) {
			return "理财型";
		}
		
		if ("5".equals(fundType)) {
			return "货币型";
		}
		
		if ("6".equals(fundType)) {
			return "指数型";
		}
		
		if ("7".equals(fundType)) {
			return "结构型";
		}
		
		if ("8".equals(fundType)) {
			return "QDII";
		}
		
		return null;
	}
	
	/**
	 * 处理搜索关键字高不]亮显示
	 * 
	 * @param fundName
	 * @param keyword
	 * @return
	 */
	public static String huntHigh(String fundName, String keyword){
		if (StringUtils.isEmpty(fundName)) {
			return null;
		}
		
		if (StringUtils.isEmpty(keyword)) {
			return fundName;
		}
		// 处理样式
		String highKeyword = "<span class='hunt-high'>"+keyword+"</span>";
		return fundName.replaceAll(keyword, highKeyword);
	}
	
}
