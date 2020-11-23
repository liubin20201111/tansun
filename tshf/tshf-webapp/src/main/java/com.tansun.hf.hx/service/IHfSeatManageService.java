package com.tansun.hf.hx.service;

import com.tansun.hf.hx.entity.HfSeatManage;

public interface IHfSeatManageService {
    public int seatIn(String seatNo);

    public int seatOut(String seatNo);

    public HfSeatManage findBySeatNo(String seatNo);
}
