package com.tansun.hf.hx.service.imp;

import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HfSeatManageMapper;
import com.tansun.hf.hx.entity.HfSeatManage;
import com.tansun.hf.hx.service.IHfSeatManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class HfSeatManageServiceImp extends AbstractBaseService<HfSeatManageMapper, HfSeatManage> implements IHfSeatManageService {
    @Resource
    private HfSeatManageMapper hfSeatManageMapper;

    @Override
    public int seatIn(String seatNo) {
        HfSeatManage manage = new HfSeatManage();
        manage.setId(seatNo);
        manage.setSeatStatus("1");
        manage.setSeatInTime(new Date());
        int count = hfSeatManageMapper.updateByPrimaryKey(manage);
        return count;
    }

    @Override
    public int seatOut(String seatNo) {
        HfSeatManage manage = new HfSeatManage();
        manage.setId(seatNo);
        manage.setSeatStatus("0");
        manage.setSeatInTime(new Date());
        int count = hfSeatManageMapper.updateByPrimaryKey(manage);
        return count;
    }

    @Override
    public HfSeatManage findBySeatNo(String seatNo) {
        return hfSeatManageMapper.selectByPrimaryKey(seatNo);
    }
}
