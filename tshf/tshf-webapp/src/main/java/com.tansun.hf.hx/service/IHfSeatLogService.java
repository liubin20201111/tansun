package com.tansun.hf.hx.service;

import com.tansun.hf.hx.entity.HfMember;
import com.tansun.hf.hx.entity.HfSeatLog;
import com.tansun.hf.hx.entity.HfSeatManage;
import com.tansun.hf.hx.vo.SeateLogVo;

import java.util.List;

public interface IHfSeatLogService {
    public boolean addSeatLog(HfSeatManage seat, HfMember member);

    public List<SeateLogVo> findSeatByMember(HfSeatLog log);
}
