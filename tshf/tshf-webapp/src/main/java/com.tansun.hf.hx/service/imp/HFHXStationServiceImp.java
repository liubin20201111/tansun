package com.tansun.hf.hx.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.jeespring.common.json.CommonException;
import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HFHXStationLogMapper;
import com.tansun.hf.hx.dao.HFHXStationMapper;
import com.tansun.hf.hx.entity.HFHXStationEntity;
import com.tansun.hf.hx.entity.HFHXStationLogEntity;
import com.tansun.hf.hx.service.HFHXStationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class HFHXStationServiceImp extends AbstractBaseService<HFHXStationMapper, HFHXStationEntity> implements HFHXStationService {
    private static final Log log = LogFactory.getLog(HFHXStationServiceImp.class);

    @Resource
    private HFHXStationMapper HFHXStationMapper;
    @Resource
    private HFHXStationLogMapper HFHXStationLogMapper;


    @Override
    public HFHXStationEntity selectByStationId(String memberId) throws CommonException {
        return HFHXStationMapper.selectByStationId(memberId);
    }
    @Override
    public int insert(HFHXStationEntity HFHXStationEntity) throws CommonException {
    	return HFHXStationMapper.insert(HFHXStationEntity);
    }
    @Override
    public int insertSelective(HFHXStationEntity HFHXStationEntity) throws CommonException {
    	return HFHXStationMapper.insertSelective(HFHXStationEntity);
    }
    @Override
    public int updateByPrimaryKeySelective(HFHXStationEntity HFHXStationEntity) throws CommonException {
    	return HFHXStationMapper.updateByPrimaryKeySelective(HFHXStationEntity);
    }
    @Override
    public int updateByPrimaryKey(HFHXStationEntity HFHXStationEntity) throws CommonException {
    	return HFHXStationMapper.updateByPrimaryKey(HFHXStationEntity);
    }


    @Override
    @Transactional
    public boolean StationInOrOut(String memberId,String stationId,String stationLogType,String stationStatus,String id,String stationType,Date createTime) throws CommonException{
    	boolean flag = false;
    	HFHXStationEntity HFHXStationEntity = new HFHXStationEntity();
    	HFHXStationEntity.setStationId(stationId);
    	HFHXStationEntity.setStationType(stationType);
    	HFHXStationEntity.setCreateTime(new Date());
        HFHXStationEntity.setId(id);
    	
    	HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
        HFHXStationLogEntity.setStationId(stationId);
        HFHXStationLogEntity.setMemberId(memberId);
        HFHXStationLogEntity.setStationLogType(stationLogType);
    	HFHXStationLogEntity.setStationStatus(stationStatus);
        HFHXStationLogEntity.setCreateTime(createTime);
        HFHXStationLogEntity.setId(UUID.randomUUID().toString().replaceAll("-",""));
    	
    	int i = HFHXStationMapper.updateByPrimaryKeySelective(HFHXStationEntity);
    	if(i>0){
            int p = HFHXStationLogMapper.insert(HFHXStationLogEntity);
            if(p>0){
                flag = true;
            }
        }
    	return flag;
    }
}
