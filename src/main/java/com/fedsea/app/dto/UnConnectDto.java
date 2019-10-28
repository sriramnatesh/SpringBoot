package com.fedsea.app.dto;

import java.util.Date;

public class UnConnectDto {
	private Long requestId;
	private Integer status;
	private Date unfriendDate;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUnfriendDate() {
		return unfriendDate;
	}

	public void setUnfriendDate(Date unfriendDate) {
		this.unfriendDate = unfriendDate;
	}

}
