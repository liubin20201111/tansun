package com.tansun.hf.hx.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.jeespring.common.json.CommonException;
import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HFHXMemberMapper;
import com.tansun.hf.hx.entity.HFHXMemberEntity;
import com.tansun.hf.hx.service.HFHXMemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class HFHXMemberServiceImp extends AbstractBaseService<HFHXMemberMapper, HFHXMemberEntity> implements HFHXMemberService {
    private static final Log log = LogFactory.getLog(HFHXMemberServiceImp.class);

    @Resource
    private HFHXMemberMapper HFHXMemberMapper;


    @Override
    public HFHXMemberEntity selectByMemberId(String memberId) throws CommonException {
        return HFHXMemberMapper.selectByMemberId(memberId);
    }
    @Override
    public int insert(HFHXMemberEntity HFHXMemberEntity) throws CommonException {
    	return HFHXMemberMapper.insert(HFHXMemberEntity);
    }
    @Override
    public int insertSelective(HFHXMemberEntity HFHXMemberEntity) throws CommonException {
    	return HFHXMemberMapper.insertSelective(HFHXMemberEntity);
    }
    @Override
    public int updateByPrimaryKeySelective(HFHXMemberEntity HFHXMemberEntity) throws CommonException {
    	return HFHXMemberMapper.updateByPrimaryKeySelective(HFHXMemberEntity);
    }
    @Override
    public int updateByPrimaryKey(HFHXMemberEntity HFHXMemberEntity) throws CommonException {
    	return HFHXMemberMapper.updateByPrimaryKey(HFHXMemberEntity);
    }
    @Override
    public HFHXMemberEntity selectByMemberPhone(String memberPhone) throws CommonException {
    	return HFHXMemberMapper.selectByMemberPhone(memberPhone);
    }
    @Override
    public HFHXMemberEntity selectByDingDingId(String dingDingId) throws CommonException {
    	return HFHXMemberMapper.selectByDingDingId(dingDingId);
    }
}
