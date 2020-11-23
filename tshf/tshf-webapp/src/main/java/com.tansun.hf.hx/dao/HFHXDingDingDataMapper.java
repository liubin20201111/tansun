package com.tansun.hf.hx.dao;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HFHXDingDingDataEntity;

public interface HFHXDingDingDataMapper extends InterfaceBaseDao<HFHXDingDingDataEntity> {
	HFHXDingDingDataEntity selectBygroupId(String groupId);
	int insert(HFHXDingDingDataEntity record);
	int insertSelective(HFHXDingDingDataEntity record);
	int updateByPrimaryKeySelective(HFHXDingDingDataEntity record);
	int updateByPrimaryKey(HFHXDingDingDataEntity record);






}