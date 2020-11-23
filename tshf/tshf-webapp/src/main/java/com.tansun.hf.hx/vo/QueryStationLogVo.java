package com.tansun.hf.hx.vo;

public class QueryStationLogVo {
    private String memberId;//
    private String startTime;//
    private String endTime;//
    private String stationLogType;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStationLogType() {
        return stationLogType;
    }

    public void setStationLogType(String stationLogType) {
        this.stationLogType = stationLogType;
    }
}
