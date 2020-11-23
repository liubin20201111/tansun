package com.tansun.hf.hx.entity;

import com.jeespring.common.persistence.AbstractBaseEntity;

import java.util.Date;

public class HFHXStationEntity extends AbstractBaseEntity<HFHXStationEntity> {
    private String id;

    private String stationId;

    private String stationCity;

    private String stationPlace;

    private String stationAddress;

    private String stationfloor;
    
    private String stationroom;
    
    private String stationType;
    
    private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationCity() {
		return stationCity;
	}

	public void setStationCity(String stationCity) {
		this.stationCity = stationCity;
	}

	public String getStationPlace() {
		return stationPlace;
	}

	public void setStationPlace(String stationPlace) {
		this.stationPlace = stationPlace;
	}

	public String getStationAddress() {
		return stationAddress;
	}

	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}

	public String getStationfloor() {
		return stationfloor;
	}

	public void setStationfloor(String stationfloor) {
		this.stationfloor = stationfloor;
	}

	public String getStationroom() {
		return stationroom;
	}

	public void setStationroom(String stationroom) {
		this.stationroom = stationroom;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    
    

}