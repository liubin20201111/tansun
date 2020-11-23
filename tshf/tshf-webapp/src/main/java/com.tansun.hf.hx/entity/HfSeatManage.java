package com.tansun.hf.hx.entity;

import com.jeespring.common.persistence.AbstractBaseEntity;

import java.util.Date;

public class HfSeatManage extends AbstractBaseEntity<HfSeatManage> {
    private String id;

    private String seatName;

    private String seatStatus;

    private Date seatInTime;

    private Date seatOutTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName == null ? null : seatName.trim();
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus == null ? null : seatStatus.trim();
    }

    public Date getSeatInTime() {
        return seatInTime;
    }

    public void setSeatInTime(Date seatInTime) {
        this.seatInTime = seatInTime;
    }

    public Date getSeatOutTime() {
        return seatOutTime;
    }

    public void setSeatOutTime(Date seatOutTime) {
        this.seatOutTime = seatOutTime;
    }
}