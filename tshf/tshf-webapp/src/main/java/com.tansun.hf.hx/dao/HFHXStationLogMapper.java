package com.tansun.hf.hx.dao;

import java.util.List;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HFHXStationLogEntity;
import com.tansun.hf.hx.vo.QueryStationLogVo;
import org.apache.ibatis.annotations.Param;

public interface HFHXStationLogMapper extends InterfaceBaseDao<HFHXStationLogEntity> {
	List<HFHXStationLogEntity> selectByMemberIdnew(QueryStationLogVo QueryStationLogVo);
	int insert(HFHXStationLogEntity record);
	int insertSelective(HFHXStationLogEntity record);
	int updateByPrimaryKeySelective(HFHXStationLogEntity record);
	int updateByPrimaryKey(HFHXStationLogEntity record);
	List<HFHXStationLogEntity> selectByMemberId(@Param("memberId") String  memberId);






}