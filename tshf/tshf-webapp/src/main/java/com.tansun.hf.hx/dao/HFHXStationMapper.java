package com.tansun.hf.hx.dao;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HFHXStationEntity;

public interface HFHXStationMapper extends InterfaceBaseDao<HFHXStationEntity> {
	HFHXStationEntity selectByStationId(String stationId);
	int insert(HFHXStationEntity record);
	int insertSelective(HFHXStationEntity record);
	int updateByPrimaryKeySelective(HFHXStationEntity record);
	int updateByPrimaryKey(HFHXStationEntity record);






}