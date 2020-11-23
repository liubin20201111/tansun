package com.tansun.hf.hx.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.jeespring.common.json.CommonException;
import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HFHXMemberVisitMapper;
import com.tansun.hf.hx.entity.HFHXMemberVisitEntity;
import com.tansun.hf.hx.service.HFHXMemberVisitService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class HFHXMemberVisitServiceImp extends AbstractBaseService<HFHXMemberVisitMapper, HFHXMemberVisitEntity> implements HFHXMemberVisitService {
    private static final Log log = LogFactory.getLog(HFHXMemberVisitServiceImp.class);

    @Resource
    private HFHXMemberVisitMapper HFHXMemberVisitMapper;


    @Override
    public HFHXMemberVisitEntity selectByMemberId(String memberId) throws CommonException {
        return HFHXMemberVisitMapper.selectByMemberId(memberId);
    }
    @Override
    public int insert(HFHXMemberVisitEntity HFHXMemberVisitEntity) throws CommonException {
    	return HFHXMemberVisitMapper.insert(HFHXMemberVisitEntity);
    }
    @Override
    public int insertSelective(HFHXMemberVisitEntity HFHXMemberVisitEntity) throws CommonException {
    	return HFHXMemberVisitMapper.insertSelective(HFHXMemberVisitEntity);
    }
    @Override
    public int updateByPrimaryKeySelective(HFHXMemberVisitEntity HFHXMemberVisitEntity) throws CommonException {
    	return HFHXMemberVisitMapper.updateByPrimaryKeySelective(HFHXMemberVisitEntity);
    }
    @Override
    public int updateByPrimaryKey(HFHXMemberVisitEntity HFHXMemberVisitEntity) throws CommonException {
    	return HFHXMemberVisitMapper.updateByPrimaryKey(HFHXMemberVisitEntity);
    }
    @Override
    public HFHXMemberVisitEntity selectByMemberPhone(String memberPhone) throws CommonException {
    	return HFHXMemberVisitMapper.selectByMemberPhone(memberPhone);
    }
    @Override
    public HFHXMemberVisitEntity selectByDingDingId(String dingDingId) throws CommonException {
    	return HFHXMemberVisitMapper.selectByDingDingId(dingDingId);
    }
}
