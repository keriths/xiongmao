package com.xm.service.dao.util;

import java.io.Serializable;

public class ResultVo implements Serializable {

	private static final long serialVersionUID = -6970210322701677387L;

	private boolean success = true;

	private String errorCode;

	private String errorMessage;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
