package lihu.zlm.fetch.util;

import org.springframework.util.StringUtils;

/**
 * 好买网抓取信息处理工具类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月14日 下午10:42:26
 * 
 */
public class HowBuyHtmlUtil {

	public static void main(String[] args) {
		System.out.println(formatTag("--"));
	}

	/**
	 * "--"全部处理为null
	 * 
	 * @param format
	 * @return
	 */
	public static String formatTag(String format) {
		if (StringUtils.isEmpty(format)) {
			return null;
		}
		if (format.contains("--")) {
			return null;
		}
		if (format.contains("span")) {
			return null;
		}
		if (format.contains("</") && format.length() == 7) {
			format = format.substring(0, 5);
		}
		return format;
	}

	/**
	 * 处理基金成立日期标签数据
	 * 
	 * @param foundedDate
	 * @return
	 */
	public static String formatFundDate(String foundedDate) {
		if (foundedDate.contains("--")) {
			foundedDate = null;
		}
		if (foundedDate.contains("PUBLIC")) {
			foundedDate = null;
		}
		return foundedDate;
	}

	/**
	 * 处理基金公司标签数据
	 * 
	 * @param fundCompany
	 * @return
	 */
	public static String formatFundCompany(String fundCompany) {
		fundCompany = fundCompany.substring(0, fundCompany.indexOf("</a>"));
		return fundCompany;
	}

	/**
	 * 处理基金近3月排名标签数据
	 * 
	 * @param fundSortThreeMonth
	 * @return
	 */
	public static String[] formatFundSortThreeMonth(String fundSortThreeMonth) {
		String sortIndex = fundSortThreeMonth.substring(fundSortThreeMonth.indexOf("class=\"b-rate\">") + 15,
				fundSortThreeMonth.indexOf("</em><em class=\"b-rate b-2\">"));

		String sortTotal = fundSortThreeMonth.substring(
				fundSortThreeMonth.indexOf("</em><em class=\"b-rate b-2\">") + 29,
				fundSortThreeMonth.lastIndexOf("</em>") - 1);

		return new String[] { sortIndex, sortTotal };
	}

	/**
	 * 处理基金涨跌幅标签数据
	 * 
	 * @param rateChange
	 * @return
	 */
	public static String formatRateChange(String rateChange) {
		if (rateChange.contains("b-rate")) {
			rateChange = rateChange.substring(rateChange.indexOf("b-rate") + 8, rateChange.length());
		}
		if (rateChange.contains("--")) {
			rateChange = null;
		}
		if (rateChange.contains("le=\"color:")) {
			rateChange = null;
		}
		return rateChange;
	}

	/**
	 * 处理最新规模标签数据
	 * 
	 * @param newScale
	 * @return
	 */
	public static String formatNewScale(String newScale) {
		if (newScale.contains("亿</span>")) {
			newScale = newScale.substring(0, newScale.indexOf("亿</span>"));
		}
		if (newScale.contains("--")) {
			newScale = null;
		}
		if (newScale.contains("PUBLIC")) {
			newScale = null;
		}
		return newScale;
	}

	/**
	 * 处理基金经理字符串
	 * 
	 * @param fundManager
	 * @return
	 */
	public static String formatFundManager(String fundManager) {
		if (fundManager.contains("<")) {
			fundManager = fundManager.substring(0, fundManager.indexOf("<"));
		}
		if (fundManager.contains("nal//")) {
			fundManager = null;
		}
		return fundManager;
	}

	/**
	 * 获取基金风险级别
	 * 
	 * @param fetchFundRiskLevel
	 * @return
	 */
	public static String getFundRiskLevel(String fetchFundRiskLevel) {
		fetchFundRiskLevel = fetchFundRiskLevel.replaceAll(" ", "");

		if (fetchFundRiskLevel.contains("低风险")) {
			return "1";
		}
		if (fetchFundRiskLevel.contains("中低风险")) {
			return "2";
		}
		if (fetchFundRiskLevel.contains("中高风险")) {
			return "3";
		}
		if (fetchFundRiskLevel.contains("高风险")) {
			return "4";
		}
		if (fetchFundRiskLevel.contains("中")) {
			return "5";
		}

		return "0";
	}

}
