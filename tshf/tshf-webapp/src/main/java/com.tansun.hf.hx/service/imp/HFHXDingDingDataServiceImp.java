package com.tansun.hf.hx.service.imp;

import com.jeespring.common.json.CommonException;
import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HFHXDingDingDataMapper;
import com.tansun.hf.hx.entity.HFHXDingDingDataEntity;
import com.tansun.hf.hx.service.HFHXDingDingDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class HFHXDingDingDataServiceImp extends AbstractBaseService<HFHXDingDingDataMapper, HFHXDingDingDataEntity> implements HFHXDingDingDataService {
    private static final Log log = LogFactory.getLog(HFHXDingDingDataServiceImp.class);

    @Resource
    private HFHXDingDingDataMapper HFHXDingDingDataMapper;


    @Override
    public HFHXDingDingDataEntity selectBygroupId(String memberId) throws CommonException {
        return HFHXDingDingDataMapper.selectBygroupId(memberId);
    }
    @Override
    public int insert(HFHXDingDingDataEntity HFHXStationEntity) throws CommonException {
    	return HFHXDingDingDataMapper.insert(HFHXStationEntity);
    }
    @Override
    public int insertSelective(HFHXDingDingDataEntity HFHXStationEntity) throws CommonException {
    	return HFHXDingDingDataMapper.insertSelective(HFHXStationEntity);
    }
    @Override
    public int updateByPrimaryKeySelective(HFHXDingDingDataEntity HFHXStationEntity) throws CommonException {
    	return HFHXDingDingDataMapper.updateByPrimaryKeySelective(HFHXStationEntity);
    }
    @Override
    public int updateByPrimaryKey(HFHXDingDingDataEntity HFHXStationEntity) throws CommonException {
    	return HFHXDingDingDataMapper.updateByPrimaryKey(HFHXStationEntity);
    }

}
