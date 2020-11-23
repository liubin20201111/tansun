package com.tansun.hf.hx.service;


import com.jeespring.common.json.CommonException;
import com.tansun.hf.hx.entity.HFHXMemberVisitEntity;

public interface HFHXMemberVisitService {

    public HFHXMemberVisitEntity selectByMemberId(String memberId) throws CommonException;
    public int insert(HFHXMemberVisitEntity record) throws CommonException;
    public int insertSelective(HFHXMemberVisitEntity record) throws CommonException;
    public int updateByPrimaryKeySelective(HFHXMemberVisitEntity record) throws CommonException;
    public int updateByPrimaryKey(HFHXMemberVisitEntity record) throws CommonException;
    public HFHXMemberVisitEntity selectByMemberPhone(String memberPhone) throws CommonException;
    public HFHXMemberVisitEntity selectByDingDingId(String dingDingId) throws CommonException;

}
