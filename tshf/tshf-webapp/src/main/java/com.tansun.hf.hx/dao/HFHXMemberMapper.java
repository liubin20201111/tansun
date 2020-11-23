package com.tansun.hf.hx.dao;

import com.jeespring.common.persistence.InterfaceBaseDao;
import com.tansun.hf.hx.entity.HFHXMemberEntity;

public interface HFHXMemberMapper extends InterfaceBaseDao<HFHXMemberEntity> {
	HFHXMemberEntity selectByMemberId(String memberId);
	int insert(HFHXMemberEntity record);
	int insertSelective(HFHXMemberEntity record);
	int updateByPrimaryKeySelective(HFHXMemberEntity record);
	int updateByPrimaryKey(HFHXMemberEntity record);
	HFHXMemberEntity selectByMemberPhone(String memberPhone);
	HFHXMemberEntity selectByDingDingId(String dingDingId);






}