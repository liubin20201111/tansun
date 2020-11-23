package com.tansun.hf.hx.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.jeespring.common.json.CommonException;
import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HFHXStationLogMapper;
import com.tansun.hf.hx.entity.HFHXStationLogEntity;
import com.tansun.hf.hx.service.HFHXStationLogService;
import com.tansun.hf.hx.vo.QueryStationLogVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HFHXStationLogServiceImp extends AbstractBaseService<HFHXStationLogMapper, HFHXStationLogEntity> implements HFHXStationLogService {
    private static final Log log = LogFactory.getLog(HFHXStationLogServiceImp.class);

    @Resource
    private HFHXStationLogMapper HFHXStationLogMapper;


    @Override
    public List<HFHXStationLogEntity> selectByMemberIdnew(QueryStationLogVo QueryStationLogVo) throws CommonException {
        return HFHXStationLogMapper.selectByMemberIdnew(QueryStationLogVo);
    }
    @Override
    public int insert(HFHXStationLogEntity HFHXStationLogEntity) throws CommonException {
    	return HFHXStationLogMapper.insert(HFHXStationLogEntity);
    }
    @Override
    public int insertSelective(HFHXStationLogEntity HFHXStationLogEntity) throws CommonException {
    	return HFHXStationLogMapper.insertSelective(HFHXStationLogEntity);
    }
    @Override
    public int updateByPrimaryKeySelective(HFHXStationLogEntity HFHXStationLogEntity) throws CommonException {
    	return HFHXStationLogMapper.updateByPrimaryKeySelective(HFHXStationLogEntity);
    }
    @Override
    public int updateByPrimaryKey(HFHXStationLogEntity HFHXStationLogEntity) throws CommonException {
    	return HFHXStationLogMapper.updateByPrimaryKey(HFHXStationLogEntity);
    }
    @Override
    public List<HFHXStationLogEntity> selectByMemberId(String  memberId) throws CommonException {
        return HFHXStationLogMapper.selectByMemberId(memberId);
    }
}
