package com.fedsea.app.dto;

import java.util.Date;

public class ConnectDto {
	private Long requestId;
	private Integer status;
	private Date friendDate;

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

	public Date getFriendDate() {
		return friendDate;
	}

	public void setFriendDate(Date friendDate) {
		this.friendDate = friendDate;
	}

}
