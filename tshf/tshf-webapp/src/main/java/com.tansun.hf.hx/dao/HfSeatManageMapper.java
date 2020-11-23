package com.tansun.hf.hx.dao;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HfSeatManage;

public interface HfSeatManageMapper extends InterfaceBaseDao<HfSeatManage> {
    int deleteByPrimaryKey(String id);

    int insert(HfSeatManage record);

    int insertSelective(HfSeatManage record);

    HfSeatManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HfSeatManage record);

    int updateByPrimaryKey(HfSeatManage record);
}