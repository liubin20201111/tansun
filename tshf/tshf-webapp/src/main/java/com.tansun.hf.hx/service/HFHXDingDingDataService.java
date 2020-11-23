package com.tansun.hf.hx.service;


import com.jeespring.common.json.CommonException;
import com.tansun.hf.hx.entity.HFHXDingDingDataEntity;

import java.util.Date;

public interface HFHXDingDingDataService {

    public HFHXDingDingDataEntity selectBygroupId(String memberId) throws CommonException;
    public int insert(HFHXDingDingDataEntity record) throws CommonException;
    public int insertSelective(HFHXDingDingDataEntity record) throws CommonException;
    public int updateByPrimaryKeySelective(HFHXDingDingDataEntity record) throws CommonException;
    public int updateByPrimaryKey(HFHXDingDingDataEntity record) throws CommonException;
    
}
