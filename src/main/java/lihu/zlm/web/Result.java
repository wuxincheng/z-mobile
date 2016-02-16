package lihu.zlm.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lihu.zlm.util.RedirectUtil;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<String, Object> values = new HashMap<String, Object>();

	private boolean success;

	private String successMsg;

	private String errorType;

	private String errorCode;

	private String errorMsg;

	private String errorField;

	private String redirectUrl;

	public void put(String key, Object value) {
		this.values.put(key, value);
	}

	public void putAll(Map<String, Object> map) {
		this.values.putAll(map);
	}

	public Result success() {
		this.success = true;
		return this;
	}

	public Result success(String successMsg) {
		this.success = true;
		this.successMsg = successMsg;
		return this;
	}

	public Result redirect(String redirectUrl) {
		this.success = true;
		this.redirectUrl = RedirectUtil.redirectModelMap(redirectUrl, this.values);
		this.values.clear();
		return this;
	}

	@SuppressWarnings("unchecked")
	public Result redirect(String redirectUrl, Object model) {
		try {
			Map<String, Object> m = null;
			if (model instanceof Map) {
				m = (Map<String, Object>) model;
			} else {
				m = PropertyUtils.describe(model);
				m.remove("class");
			}
			this.values.putAll(m);
		} catch (Exception e) {
		}

		return redirect(redirectUrl);
	}

	public Result reject(String errorMsg) {
		this.success = false;
		this.errorType = "validate";
		this.errorMsg = errorMsg;
		return this;
	}

	public Result reject(String errorField, String errorMsg) {
		this.success = false;
		this.errorType = "validate";
		this.errorField = errorField;
		this.errorMsg = errorMsg;
		return this;
	}

	public Result reject(Errors errors) {
		List<ObjectError> allErrors = errors.getAllErrors();
		this.success = false;
		if (errors.hasErrors()) {
			this.errorType = "validate";
			this.errorMsg = allErrors.get(0).getDefaultMessage();
			if (allErrors.get(0) instanceof FieldError) {
				FieldError fieldError = (FieldError) allErrors.get(0);
				this.errorField = fieldError.getField();
			}
		}
		return this;
	}

	public Result error(String errorMsg) {
		this.success = false;
		this.errorType = "exception";
		this.errorMsg = errorMsg;
		return this;
	}

	public Result error(String errorCode, String errorMsg) {
		this.success = false;
		this.errorType = "exception";
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		return this;
	}

	public Result error(Exception ex) {
		this.success = false;
		this.errorType = "exception";

		if (ex instanceof Exception) {
			Exception sex = (Exception) ex;
			this.errorMsg = sex.getMessage();
			return this;
		} else if (ex instanceof RuntimeException) {
			this.errorCode = "9999";
			this.errorMsg = "应用程序异常，请联系客服";
			return this;
		} else {
			this.errorCode = "9999";
			this.errorMsg = ex.getMessage();
			return this;
		}

	}

	public HashMap<String, Object> getValues() {
		return values;
	}

	public void setValues(HashMap<String, Object> values) {
		this.values = values;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.success = true;
		this.successMsg = successMsg;
	}

}
