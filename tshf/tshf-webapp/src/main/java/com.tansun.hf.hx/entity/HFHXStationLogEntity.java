package com.tansun.hf.hx.entity;

import com.jeespring.common.persistence.AbstractBaseEntity;

import java.util.Date;

public class HFHXStationLogEntity extends AbstractBaseEntity<HFHXStationLogEntity> {
    private String id;

    private String memberId;

    private String stationId;

    private String stationLogType;

    private String stationStatus;

    private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationLogType() {
		return stationLogType;
	}

	public void setStationLogType(String stationLogType) {
		this.stationLogType = stationLogType;
	}

	public String getStationStatus() {
		return stationStatus;
	}

	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    


    
    

}