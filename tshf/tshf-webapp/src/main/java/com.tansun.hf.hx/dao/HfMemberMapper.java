package com.tansun.hf.hx.dao;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HfMember;

public interface HfMemberMapper extends InterfaceBaseDao<HfMember> {
    int deleteByPrimaryKey(String id);

    int insert(HfMember record);

    int insertSelective(HfMember record);

    HfMember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HfMember record);

    int updateByPrimaryKey(HfMember record);

    HfMember selectByMobile(String mobile);
}