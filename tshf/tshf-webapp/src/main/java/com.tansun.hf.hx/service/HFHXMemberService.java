package com.tansun.hf.hx.service;


import com.jeespring.common.json.CommonException;
import com.tansun.hf.hx.entity.HFHXMemberEntity;

public interface HFHXMemberService {

    public HFHXMemberEntity selectByMemberId(String memberId) throws CommonException;
    public int insert(HFHXMemberEntity record) throws CommonException;
    public int insertSelective(HFHXMemberEntity record) throws CommonException;
    public int updateByPrimaryKeySelective(HFHXMemberEntity record) throws CommonException;
    public int updateByPrimaryKey(HFHXMemberEntity record) throws CommonException;
    public HFHXMemberEntity selectByMemberPhone(String memberPhone) throws CommonException;
    public HFHXMemberEntity selectByDingDingId(String dingDingId) throws CommonException;

}
