package com.tansun.hf.hx.dao;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HFHXMemberVisitEntity;

public interface HFHXMemberVisitMapper extends InterfaceBaseDao<HFHXMemberVisitEntity> {
	HFHXMemberVisitEntity selectByMemberId(String memberId);
	int insert(HFHXMemberVisitEntity record);
	int insertSelective(HFHXMemberVisitEntity record);
	int updateByPrimaryKeySelective(HFHXMemberVisitEntity record);
	int updateByPrimaryKey(HFHXMemberVisitEntity record);
	HFHXMemberVisitEntity selectByMemberPhone(String memberPhone);
	HFHXMemberVisitEntity selectByDingDingId(String dingDingId);






}