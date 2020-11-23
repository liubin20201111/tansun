package com.tansun.hf.hx.service.imp;

import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HfSeatLogMapper;
import com.tansun.hf.hx.entity.HfMember;
import com.tansun.hf.hx.entity.HfSeatLog;
import com.tansun.hf.hx.entity.HfSeatManage;
import com.tansun.hf.hx.service.IHfSeatLogService;
import com.tansun.hf.hx.vo.SeateLogVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class HfSeatLogServiceImp extends AbstractBaseService<HfSeatLogMapper, HfSeatLog> implements IHfSeatLogService {

    @Resource
    private HfSeatLogMapper hfSeatLogMapper;

    @Override
    public boolean addSeatLog(HfSeatManage seat, HfMember member) {
        HfSeatLog log = new HfSeatLog();
        log.setId(UUID.randomUUID().toString().replace("-",""));
        if (member != null){
            log.setMid(member.getId());
        }
        log.setSeatId(seat.getId());
        log.setStatus(seat.getSeatStatus());
        hfSeatLogMapper.insert(log);
        return false;
    }

    @Override
    public List<SeateLogVo> findSeatByMember(HfSeatLog log) {
        return hfSeatLogMapper.findSeatByMember(log);
    }
}
