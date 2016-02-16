package lihu.zlm.fetch.helper;

/**
 * 抓取应用常量
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月17日 下午8:28:52
 * 
 */
public class FetchConstants {

	/** 任务类型1: 抓取基金行情列表数据任务 */
	public static final String TASK_FUND_MARKET_INFO = "fundMarketInfo";

	/** 任务类型2: 补充基金行情详细信息任务 */
	public static final String TASK_FUND_MARKET_DETAIL = "fundMarketDetail";

	/** 任务执行状态: 0-未执行 */
	public static final String TASK_PROCESS_INIT = "0";

	/** 任务执行状态: 1-执行成功 */
	public static final String TASK_PROCESS_SUCCESS = "1";

	/** 任务执行状态: 2-执行失败 */
	public static final String TASK_PROCESS_FAIL = "2";

}
