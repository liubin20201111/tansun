package com.tansun.hf.hx.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeespring.common.json.CommonException;
import com.jeespring.common.json.ResultModelMap;
import com.jeespring.common.web.AbstractBaseController;
import com.tansun.hf.hx.dao.HfMemberMapper;
import com.tansun.hf.hx.entity.HfMember;
import com.tansun.hf.hx.entity.HfSeatLog;
import com.tansun.hf.hx.entity.HfSeatManage;
import com.tansun.hf.hx.service.IHfMemberService;
import com.tansun.hf.hx.service.IHfSeatLogService;
import com.tansun.hf.hx.service.IHfSeatManageService;
import com.tansun.hf.hx.vo.SeateLogVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
//@RequestMapping("${mbankPath}/hf/member")
public class HFMemberController extends AbstractBaseController {

    private static final Log log = LogFactory.getLog(HFMemberController.class);

    @Resource
    private IHfMemberService iHfMemberService;
    @Resource
    private IHfSeatLogService iHfSeatLogService;
    @Resource
    private IHfSeatManageService iHfSeatManageService;

    /**
     * 手机号登录/验证
     * @param
     * {
     * 	"route": "00001",//00001：发送短信验证码，00002：验证登录，00003：查询用户信息
     * 	"mobileNo": "138xxxxxxxx",
     * 	"mobileCode": "123456"
     * }
     * @return  ResultModel
     */
    @RequestMapping(value = "${hfHxPath}/hf/member/mobileLogin",method = RequestMethod.POST)
    public ResultModelMap mobileLoginOrVerification(@RequestBody String param){
        ResultModelMap rmm = new ResultModelMap();
        try {
            //通过手机号登录
           /* String sql = "select * from t_citycode GROUP BY provinceid";
            List<Province> list = provinceDao.getProvinceBySql(sql);*/

            JSONObject jsonParam = JSON.parseObject(param);

            String route = jsonParam.getString("route");
            HfMember member = new HfMember();
            String mobileNo = "";
            String mobileCode = "";
            //发送短信验证码
            if ("00001".equals(route)){
                mobileNo = jsonParam.getString("mobileNo");
            }
            if ("00002".equals(route)){
                mobileNo = jsonParam.getString("mobileNo");
                mobileCode = jsonParam.getString("mobileCode");
                boolean b = iHfMemberService.verificationLogin(mobileNo, mobileCode);
                if(!b){
                    throw new CommonException("queryProvinceException","登录验证失败，请稍后重试");
                }
            }
            if ("00003".equals(route)) {
                mobileNo = jsonParam.getString("mobileNo");
                HfMember memberInfo = iHfMemberService.getMemberInfo(mobileNo);
                rmm.put("memberInfo",memberInfo);
            }
        }catch (CommonException e){
            rmm.setException(e);
        }
        return rmm;
    }

    /**
     * 入座申请/出座盛申请
     * @param param
     *{
     * 	"route": "00001",//00001：入座申请，00002：出座申请，00003：查询拜访记录
     * 	"mobileNo": "138xxxxxxxx",//用户手机号
     * 	"seatNo": "14110"//工作编号
     * }
     * @return
     */
    @RequestMapping(value = "${hfHxPath}/hf/member/seatInOrOut",method = RequestMethod.POST)
    public ResultModelMap seatInOrOut(@RequestBody String param){
        ResultModelMap rmm = new ResultModelMap();
        try {
            //修改
           /* String sql = "select * from t_citycode GROUP BY provinceid";
            List<Province> list = provinceDao.getProvinceBySql(sql);*/

            JSONObject jsonParam = JSON.parseObject(param);

            String route = jsonParam.getString("route");
            HfMember member = new HfMember();
            String mobileNo = jsonParam.getString("mobileNo");
            String seatNo = jsonParam.getString("seatNo");
            //00001：入座申请，00002：出座申请,00003:拜访记录（送手机号即可）
            if ("00001".equals(route)){
                //接收到手机号查询数据库用户
                HfMember memberInfo = iHfMemberService.getMemberInfo(mobileNo);
                //接收工位号修改工位的状态
                int count = iHfSeatManageService.seatIn(seatNo);
                HfSeatManage SeatInfo = iHfSeatManageService.findBySeatNo(seatNo);
                if (SeatInfo != null) {
                    //插入拜访记录表数据
                    boolean s = iHfSeatLogService.addSeatLog(SeatInfo, memberInfo);
                }
            }
            if ("00002".equals(route)){
                //接收到手机号查询数据库用户
                HfMember memberInfo = iHfMemberService.getMemberInfo(mobileNo);
                //接收工位号修改工位的状态
                int count = iHfSeatManageService.seatOut(seatNo);
                HfSeatManage SeatInfo = iHfSeatManageService.findBySeatNo(seatNo);
                if (SeatInfo != null) {
                    //插入拜访记录表数据
                    boolean s = iHfSeatLogService.addSeatLog(SeatInfo, memberInfo);
                }
            }
            if ("00003".equals(route)){
                HfMember memberInfo = iHfMemberService.getMemberInfo(mobileNo);
                //查询拜访记录
                HfSeatLog log = new HfSeatLog();
                log.setMid(memberInfo.getId());
                List<SeateLogVo> seatList = iHfSeatLogService.findSeatByMember(log);
                rmm.put("seatLog",seatList);
            }
        }catch (CommonException e){
            rmm.setException(e);
        }
        return rmm;
    }
}
