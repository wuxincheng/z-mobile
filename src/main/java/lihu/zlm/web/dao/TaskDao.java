package lihu.zlm.web.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lihu.zlm.web.model.Task;

@Repository("taskDao")
public class TaskDao extends BaseDao {

	/**
	 * 根据任务名称和任务日期查询任务信息
	 * 
	 * @param taskName
	 * @param taskDate
	 * @return
	 */
	public Task query(String taskName, String taskDate) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("taskName", taskName);
		queryMap.put("taskDate", taskDate);
		return (Task) this.getSqlMapClientTemplate().queryForObject("Task.query", queryMap);
	}

	/**
	 * 插入一条任务信息
	 * 
	 * @param task
	 */
	public void insert(Task task) {
		this.getSqlMapClientTemplate().update("Task.insert", task);
	}
	
	/**
	 * 更新一条任务信息
	 * 
	 * @param task
	 */
	public void update(Task task) {
		this.getSqlMapClientTemplate().update("Task.update", task);
	}

}
