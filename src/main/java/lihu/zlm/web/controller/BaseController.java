package lihu.zlm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import lihu.zlm.web.model.User;
import lihu.zlm.util.Constants;

/**
 * Controller基类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年6月29日 下午3:55:44
 * 
 */
@Controller
public class BaseController {

	/**
	 * 判断用户是否有创建产品集的权限
	 */
	protected boolean isCollectPermission(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (null == user) {
			return false;
		}

		if ("1".equals(user.getCollectPermission())) {
			return true;
		}

		return false;
	}

	/**
	 * 获取当前Session下的用户信息
	 */
	protected User getCurrentUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(Constants.CURRENT_USER);
	}

	/**
	 * 获取当前Session下的用户ID
	 */
	protected String getCurrentUserid(HttpServletRequest request) {
		User user = getCurrentUser(request);
		if (null == user) {
			return null;
		}
		return user.getUserid();
	}

	/**
	 * 处理信息提示
	 */
	protected void requestMessageProcess(HttpServletRequest request) {

		String msgError = request.getParameter(Constants.MSG_ERROR);
		String msgWarn = request.getParameter(Constants.MSG_WARN);
		String msgInfo = request.getParameter(Constants.MSG_INFO);
		String msgSuccess = request.getParameter(Constants.MSG_SUCCESS);

		request.setAttribute(Constants.MSG_ERROR, encode(msgError));
		request.setAttribute(Constants.MSG_WARN, encode(msgWarn));
		request.setAttribute(Constants.MSG_INFO, encode(msgInfo));
		request.setAttribute(Constants.MSG_SUCCESS, encode(msgSuccess));
	}

	/**
	 * GET请求乱码处理
	 */
	private String encode(String param) {
		return param;
	}

}
