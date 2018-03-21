package org.homedepo.metrics;

public class MetricsData {
	
	private String className;
	private String operation;
	private String inputParams;
	private String clientIp;
	private boolean success;
	private long responseTime;
	private String errMsg;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getInputParams() {
		return inputParams;
	}
	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "MetricsData [className=" + className + ", operation=" + operation + ", inputParams=" + inputParams
				+ ", clientIp=" + clientIp + ", success=" + success + ", responseTime=" + responseTime + ", errMsg="
				+ errMsg + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((clientIp == null) ? 0 : clientIp.hashCode());
		result = prime * result + ((errMsg == null) ? 0 : errMsg.hashCode());
		result = prime * result + ((inputParams == null) ? 0 : inputParams.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + (int) (responseTime ^ (responseTime >>> 32));
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetricsData other = (MetricsData) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (clientIp == null) {
			if (other.clientIp != null)
				return false;
		} else if (!clientIp.equals(other.clientIp))
			return false;
		if (errMsg == null) {
			if (other.errMsg != null)
				return false;
		} else if (!errMsg.equals(other.errMsg))
			return false;
		if (inputParams == null) {
			if (other.inputParams != null)
				return false;
		} else if (!inputParams.equals(other.inputParams))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (responseTime != other.responseTime)
			return false;
		if (success != other.success)
			return false;
		return true;
	}
	
	
}
