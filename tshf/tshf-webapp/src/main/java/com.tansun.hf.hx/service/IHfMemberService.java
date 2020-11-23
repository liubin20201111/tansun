package com.tansun.hf.hx.service;


import com.jeespring.common.json.CommonException;
import com.tansun.hf.hx.entity.HfMember;

public interface IHfMemberService {

    public boolean verificationLogin(String mobileNo, String mobileCode) throws CommonException;

    public HfMember getMemberInfo(String mobileNo) throws CommonException;

}
