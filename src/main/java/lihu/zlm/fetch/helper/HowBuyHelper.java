package lihu.zlm.fetch.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lihu.zlm.fetch.util.HowBuyHtmlUtil;
import lihu.zlm.util.HttpClientHelper;

/**
 * 好买基金网信息抓取帮助类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月14日 下午6:08:04
 * 
 */
@Component
public class HowBuyHelper {
	private static final Logger logger = LoggerFactory.getLogger(HowBuyHelper.class);

	/**
	 * 抓取基金行情列表信息
	 * 
	 * @param fetchURL
	 *            抓取地址
	 * @param index
	 *            开始读取的位置
	 * @param end
	 *            结束读取的位置
	 * @param fundType
	 *            基金类型
	 * @return
	 */
	public List<Map<String, String>> fectFundInfos(String fetchURL, String fundType) {
		logger.info("抓取好买网基金行情信息 fetchURL={}", fetchURL);
		HttpClientHelper hp = new HttpClientHelper();

		String fetchData = null;
		try {
			logger.info("开始抓取");
			fetchData = hp.getData(fetchURL);
			logger.info("抓取成功");
		} catch (Exception e) {
			logger.error("抓取出现异常", e);
			return null;
		}

		if (StringUtils.isEmpty(fetchData)) {
			logger.info("抓取数据为空");
			return null;
		}

		logger.info("开始解析原始数据");
		StringBuffer fetchbf = new StringBuffer(fetchData);
		int indexflag = fetchData.indexOf("table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\"");
		fetchbf.delete(0, indexflag);
		fetchbf.delete(fetchbf.indexOf("div id=\"dbDivfloat\""), fetchbf.length() - 1);
		logger.debug("初步截取处理");

		// 每个表格行
		String[] fundsInfoRes = fetchbf.toString().split("<tr>");
		logger.debug("转换成数组处理");

		// 存放基金信息的列表
		List<Map<String, String>> fundInfos = new ArrayList<Map<String, String>>();

		for (int i = 3; i < fundsInfoRes.length; i++) {
			// 每一行的数据
			String fundInfoRes = fundsInfoRes[i];
			logger.debug("第{}行的数据 fundInfoRes={}", i, fundInfoRes);

			// 每一行中每个单元格的数据
			String[] temps = fundInfoRes.split("td width=");
			logger.debug("第{}行中每个单元格的数据 temps={}", i, temps);

			if (temps.length > 15 || temps.length < 15) {
				logger.debug("无效数据行 i={}", i);
				continue;
			}

			logger.debug("数组temps长度 length={}", temps.length);

			// 基金信息
			Map<String, String> fundInfoMap = new HashMap<String, String>();

			try {
				// 基金代码
				String fundCode = temps[3].substring(45, temps[3].length() - 10);
				fundInfoMap.put("fundCode", fundCode);
				logger.debug("fundCode={}", fundCode);
			} catch (Exception e) {
				logger.error("基金代码提取异常", e);
				continue;
			}

			try {
				// 基金名称
				String fundName = temps[4].substring(58, temps[4].length() - 12);
				fundInfoMap.put("fundName", HowBuyHtmlUtil.formatTag(fundName));
				logger.debug("fundName={}", temps[4]);
			} catch (Exception e) {
				logger.error("基金名称提取异常", e);
				fundInfoMap.put("fundName", null);
			}

			try {
				// 基金净值日期
				String fundNavDate = temps[5].substring(5, temps[5].length() - 6);
				fundInfoMap.put("fundNavDate", HowBuyHtmlUtil.formatTag(fundNavDate));
				logger.debug("fundNavDate={}", fundNavDate);
			} catch (Exception e) {
				logger.error("基金净值日期提取异常", e);
				fundInfoMap.put("fundNavDate", null);
			}
			
			try {
				// 基金净值
				String fundNav = temps[6].substring(17, temps[6].length() - 8);
				fundInfoMap.put("fundNav", HowBuyHtmlUtil.formatTag(fundNav));
				logger.debug("fundNav={}", temps[6]);
			} catch (Exception e) {
				logger.error("基金净值提取异常", e);
				fundInfoMap.put("fundNav", null);
			}

			/* 下面这几个字段要特殊处理下, 因为有--, 红色, 绿色三种显示风格 */

			try {
				// 近一周
				String fundRiseWeek = temps[7].substring(17, temps[7].length() - 6); // 无样式处理
				if (fundRiseWeek.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
					fundRiseWeek = fundRiseWeek.replaceAll("<span class=\"cRed\">", "").replaceAll("</span>", "");
				}
				if (fundRiseWeek.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
					fundRiseWeek = fundRiseWeek.replaceAll("<span class=\"cGreen\">", "").replaceAll("</span>", "");
				}
				fundInfoMap.put("fundRiseWeek", HowBuyHtmlUtil.formatTag(fundRiseWeek));
				logger.debug("fundRiseWeek={}", temps[7]);
			} catch (Exception e) {
				logger.error("近一周净值提取异常", e);
				fundInfoMap.put("fundRiseWeek", null);
			}

			try {
				// 近一个月
				String fundRiseMonth = temps[8].substring(17, temps[8].length() - 6);
				if (fundRiseMonth.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
					fundRiseMonth = fundRiseMonth.replaceAll("<span class=\"cRed\">", "").replaceAll("</span>", "");
				}
				if (fundRiseMonth.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
					fundRiseMonth = fundRiseMonth.replaceAll("<span class=\"cGreen\">", "").replaceAll("</span>", "");
				}
				fundInfoMap.put("fundRiseMonth", HowBuyHtmlUtil.formatTag(fundRiseMonth));
				logger.debug("fundRiseMonth={}", temps[8]);
			} catch (Exception e) {
				logger.error("近一个月净值提取异常", e);
				fundInfoMap.put("fundRiseMonth", null);
			}
			
			try {
				// 近三个月
				String fundRiseThreeMonth = temps[9].substring(17, temps[9].length() - 6);
				if (fundRiseThreeMonth.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
					fundRiseThreeMonth = fundRiseThreeMonth.replaceAll("<span class=\"cRed\">", "").replaceAll("</span>",
							"");
				}
				if (fundRiseThreeMonth.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
					fundRiseThreeMonth = fundRiseThreeMonth.replaceAll("<span class=\"cGreen\">", "").replaceAll("</span>",
							"");
				}
				fundInfoMap.put("fundRiseThreeMonth", HowBuyHtmlUtil.formatTag(fundRiseThreeMonth));
				logger.debug("fundRiseThreeMonth={}", temps[9]);
			} catch (Exception e) {
				logger.error("近三个月净值提取异常", e);
				fundInfoMap.put("fundRiseThreeMonth", null);
			}
			
			try {
				// 近半年
				String fundRiseHalfYear = temps[10].substring(17, temps[10].length() - 6);
				if (fundRiseHalfYear.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
					fundRiseHalfYear = fundRiseHalfYear.replaceAll("<span class=\"cRed\">", "").replaceAll("</span>", "");
				}
				if (fundRiseHalfYear.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
					fundRiseHalfYear = fundRiseHalfYear.replaceAll("<span class=\"cGreen\">", "").replaceAll("</span>", "");
				}
				fundInfoMap.put("fundRiseHalfYear", HowBuyHtmlUtil.formatTag(fundRiseHalfYear));
				logger.debug("fundRiseHalfYear={}", temps[10]);
			} catch (Exception e) {
				logger.error("近半年净值提取异常", e);
				fundInfoMap.put("fundRiseHalfYear", null);
			}
			
			try {
				// 近一年
				String fundRiseYear = temps[11].substring(17, temps[11].length() - 6);
				if (fundRiseYear.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
					fundRiseYear = fundRiseYear.replaceAll("<span class=\"cRed\">", "").replaceAll("</span>", "");
				}
				if (fundRiseYear.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
					fundRiseYear = fundRiseYear.replaceAll("<span class=\"cGreen\">", "").replaceAll("</span>", "");
				}
				fundInfoMap.put("fundRiseYear", HowBuyHtmlUtil.formatTag(fundRiseYear));
				logger.debug("fundRiseYear={}", temps[11]);
			} catch (Exception e) {
				logger.error("近一年净值提取异常", e);
				fundInfoMap.put("fundRiseYear", null);
			}
			
			try {
				// 今年
				String fundRiseThisYear = temps[12].substring(17, temps[12].length() - 6);
				if (fundRiseThisYear.contains("<span class=\"cRed\">")) { // 红色样式进一步处理
					fundRiseThisYear = fundRiseThisYear.replaceAll("<span class=\"cRed\">", "").replaceAll("</span>", "");
				}
				if (fundRiseThisYear.contains("<span class=\"cGreen\">")) { // 绿色样式进一步处理
					fundRiseThisYear = fundRiseThisYear.replaceAll("<span class=\"cGreen\">", "").replaceAll("</span>", "");
				}
				fundInfoMap.put("fundRiseThisYear", HowBuyHtmlUtil.formatTag(fundRiseThisYear));
				logger.debug("fundRiseThisYear={}", temps[12]);
			} catch (Exception e) {
				logger.error("今年净值提取异常", e);
				fundInfoMap.put("fundRiseThisYear", null);
			}
			
			// 基金类型
			fundInfoMap.put("fundType", fundType);

			logger.info("成功抓取一条行情记录 fundInfoMap={}", fundInfoMap);

			fundInfos.add(fundInfoMap);
		}

		logger.info("成功抓取size={}条行情信息", fundInfos.size());

		return fundInfos;
	}

	/**
	 * 根据基金代码抓取基金详细信息
	 * 
	 * @param fundCode
	 *            基金代码
	 * @return
	 */
	public Map<String, String> fectFundInfo(String fundCode) {
		logger.info("根据基金代码抓取基金详细信息 fundCode={}", fundCode);
		if (StringUtils.isEmpty(fundCode)) {
			logger.info("funcCode为空");
			return null;
		}

		String fetchURL = "http://www.howbuy.com/fund/" + fundCode + "/";
		logger.debug("抓取基金详细信息 fetchURL={}", fetchURL);

		HttpClientHelper hp = new HttpClientHelper();

		String fetchData = null;
		try {
			logger.info("开始抓取");
			fetchData = hp.getData(fetchURL);
			logger.info("抓取成功");
		} catch (Exception e) {
			logger.error("抓取出现异常", e);
			return null;
		}

		Map<String, String> fundInfoMap = new HashMap<String, String>();
		fundInfoMap.put("fundCode", fundCode);
		
		try {
			// 基金成立日期
			logger.debug("开始处理基金成立日期标签");
			String foundedDate = fetchData.substring(fetchData.indexOf("<li>成立时间<span>") + 14,
					fetchData.indexOf("<li>成立时间<span>") + 24);
			logger.info("基金成立日期 foundedDate={}", foundedDate);
			fundInfoMap.put("foundedDate", HowBuyHtmlUtil.formatFundDate(foundedDate));
		} catch (Exception e) {
			logger.error("抓取处理基金成立日期标签异常", e.getMessage());
			fundInfoMap.put("foundedDate", null);
		}
		
		try {
			// 基金经理
			logger.debug("开始处理基金经理标签");
			String fundManager = fetchData.substring(
					fetchData.indexOf("class=\"on\"><a href=\"javascript:void(0)\"\ntarget=\"_self\">") + 55,
					fetchData.indexOf("class=\"on\"><a href=\"javascript:void(0)\"\ntarget=\"_self\">") + 60);
			logger.info("基金经理 fundManager={}", fundManager);
			fundInfoMap.put("fundManager", HowBuyHtmlUtil.formatFundManager(fundManager));
		} catch (Exception e) {
			logger.error("抓取处理基金经理标签异常", e.getMessage());
			fundInfoMap.put("fundManager", null);
		}
		
		try {
			// 基金最新规模
			logger.debug("开始处理基金最新规模标签");
			String newScale = fetchData.substring(fetchData.indexOf("<li>最新规模<span>") + 14,
					fetchData.indexOf("<li>最新规模<span>") + 50);
			logger.info("基金最新规模 newScale={}", newScale);
			fundInfoMap.put("newScale", HowBuyHtmlUtil.formatNewScale(newScale));
		} catch (Exception e) {
			logger.error("抓取处理基金最新规模标签异常", e.getMessage());
			fundInfoMap.put("newScale", null);
		}

		try {
			// 公司简称
			logger.info("开始处理公司简称标签");
			String fundCompany = fetchData.substring(fetchData.indexOf("<li>公司简称：<a") + 64,
					fetchData.indexOf("<li>公司简称：<a") + 90);
			logger.info("公司简称 fundCompany={}", fundCompany);
			fundInfoMap.put("fundCompany", HowBuyHtmlUtil.formatFundCompany(fundCompany));
		} catch (Exception e) {
			logger.error("抓取处理公司简称标签异常", e.getMessage());
			fundInfoMap.put("fundCompany", null);
		}
		
		try {
			// 基金近3月排名/7日年化排名
			logger.info("开始处理基金近3月排名标签");
			String fundSortThreeMonth = fetchData.substring(fetchData.indexOf("</em><em class=\"b-rate b-2\">") - 30,
					fetchData.indexOf("</em><em class=\"b-rate b-2\">") + 50);
			logger.info("基金近3月排名/7日年化排名 fundSortThreeMonth={}", fundSortThreeMonth);
			String[] fundSorts = HowBuyHtmlUtil.formatFundSortThreeMonth(fundSortThreeMonth);
			fundInfoMap.put("fundSortThreeMonth", fundSorts[0]);
			fundInfoMap.put("fundTotalThreeMonth", fundSorts[1]);
		} catch (Exception e) {
			logger.error("抓取处理基金近3月排名/7日年化排名标签异常", e.getMessage());
			fundInfoMap.put("fundSortThreeMonth", null);
			fundInfoMap.put("fundTotalThreeMonth", null);
		}
		
		/* 以下有几种处理情况 */
		
		try {
			// 基金风险级别
			logger.debug("开始处理基金风险级别标签");
			String fundRiskLevel = fetchData.substring(fetchData.indexOf("<p class=\"risk\"><span>") + 22,
					fetchData.indexOf("<p class=\"risk\"><span>") + 30);
			logger.info("基金风险级别 fundRiskLevel={}", fundRiskLevel);
			fundRiskLevel = HowBuyHtmlUtil.getFundRiskLevel(fundRiskLevel);
			fundInfoMap.put("fundRiskLevel", fundRiskLevel);
		} catch (Exception e) {
			logger.error("抓取处理基金风险级别标签异常", e.getMessage());
			fundInfoMap.put("fundRiskLevel", null);
		}
		
		try {
			// 基金涨跌幅/7日年化
			logger.debug("开始处理基金涨跌幅标签");
			String rateChange = fetchData.substring(fetchData.indexOf("<div class=\"b-0 b-1\">涨跌幅</div>") - 35,
					fetchData.indexOf("<div class=\"b-0 b-1\">涨跌幅</div>") - 15);
			logger.info("基金涨跌幅 rateChange={}", rateChange);
			fundInfoMap.put("rateChange", HowBuyHtmlUtil.formatRateChange(rateChange));
		} catch (Exception e) {
			logger.error("抓取处理基金涨跌幅/7日年化标签异常", e.getMessage());
			fundInfoMap.put("rateChange", null);
		}
		
		// 基金净值/年化收益

		logger.info("处理后的基金信息 fundInfoMap={}", fundInfoMap);

		return fundInfoMap;
	}

	public static void main(String[] args) {
		HowBuyHelper hb = new HowBuyHelper();
		hb.fectFundInfo("001078");
	}

}
