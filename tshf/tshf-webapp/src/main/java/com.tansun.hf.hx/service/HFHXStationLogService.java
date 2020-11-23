package com.tansun.hf.hx.service;


import com.jeespring.common.json.CommonException;
import com.tansun.hf.hx.entity.HFHXStationLogEntity;
import com.tansun.hf.hx.vo.QueryStationLogVo;

import java.util.List;

public interface HFHXStationLogService {

    public List<HFHXStationLogEntity> selectByMemberIdnew(QueryStationLogVo QueryStationLogVo) throws CommonException;
    public int insert(HFHXStationLogEntity record) throws CommonException;
    public int insertSelective(HFHXStationLogEntity record) throws CommonException;
    public int updateByPrimaryKeySelective(HFHXStationLogEntity record) throws CommonException;
    public int updateByPrimaryKey(HFHXStationLogEntity record) throws CommonException;
    public List<HFHXStationLogEntity> selectByMemberId(String  memberId) throws CommonException;

}
