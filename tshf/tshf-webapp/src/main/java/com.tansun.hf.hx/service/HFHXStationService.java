package com.tansun.hf.hx.service;


import com.jeespring.common.json.CommonException;
import com.tansun.hf.hx.entity.HFHXStationEntity;

import java.util.Date;

public interface HFHXStationService {

    public HFHXStationEntity selectByStationId(String memberId) throws CommonException;
    public int insert(HFHXStationEntity record) throws CommonException;
    public int insertSelective(HFHXStationEntity record) throws CommonException;
    public int updateByPrimaryKeySelective(HFHXStationEntity record) throws CommonException;
    public int updateByPrimaryKey(HFHXStationEntity record) throws CommonException;
    
    public boolean StationInOrOut(String memberId, String stationId, String stationLogType, String stationStatus, String id, String stationType, Date createTime) throws CommonException;
}
