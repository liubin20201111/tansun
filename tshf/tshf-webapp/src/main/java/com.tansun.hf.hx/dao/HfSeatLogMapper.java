package com.tansun.hf.hx.dao;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HfSeatLog;
import com.tansun.hf.hx.vo.SeateLogVo;

import java.util.List;

public interface HfSeatLogMapper extends InterfaceBaseDao<HfSeatLog> {
    int deleteByPrimaryKey(Long id);

    int insert(HfSeatLog record);

    int insertSelective(HfSeatLog record);

    HfSeatLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HfSeatLog record);

    int updateByPrimaryKey(HfSeatLog record);

    List<SeateLogVo> findSeatByMember(HfSeatLog log);
}