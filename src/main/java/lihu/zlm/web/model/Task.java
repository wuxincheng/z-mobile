package lihu.zlm.web.model;

/**
 * 任务表
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月17日 上午9:10:35
 * 
 */
public class Task {

	/**
	 * 任务名称
	 * 
	 * fundMarketInfo: 抓取基金行情列表数据任务; fundDetailInfo: 补充基金行情详细信息任务; fundInfo:
	 * 从基金行情信息表中提取信息到基金信息表任务; fundMarketHistory: 把当天的行情信息转移到历史表中任务
	 */
	private String taskName;

	/** 任务日期 */
	private String taskDate;

	/** 任务标志: 0-未执行, 1-已执行, 2-执行失败 */
	private String taskFlag;

	/** 任务状态: 0-正常任务, 1-不使用任务 */
	private String taskState;

	/** 任务执行时间 */
	private String updateTime;

	/** 任务说明 */
	private String taskMemo;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public String getTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(String taskFlag) {
		this.taskFlag = taskFlag;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getTaskMemo() {
		return taskMemo;
	}

	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}

}
