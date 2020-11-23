package com.tansun.hf.hx.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeespring.common.json.CommonException;
import com.jeespring.common.json.ResultModelMap;
import com.jeespring.common.web.AbstractBaseController;
import com.tansun.hf.hx.dao.HfMemberMapper;
import com.tansun.hf.hx.entity.HFHXMemberEntity;
import com.tansun.hf.hx.entity.HFHXMemberVisitEntity;
import com.tansun.hf.hx.entity.HFHXStationEntity;
import com.tansun.hf.hx.entity.HFHXStationLogEntity;
import com.tansun.hf.hx.service.HFHXMemberService;
import com.tansun.hf.hx.service.HFHXMemberVisitService;
import com.tansun.hf.hx.service.HFHXStationLogService;
import com.tansun.hf.hx.service.HFHXStationService;
import com.tansun.hf.hx.utils.MD5;
import com.tansun.hf.hx.vo.HFHXMemberVo;
import com.tansun.hf.hx.vo.HFHXStationVo;
import com.tansun.hf.hx.vo.QueryStationLogVo;
import com.tansun.hf.hx.vo.SeateLogVo;
import javafx.scene.input.DataFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
//@RequestMapping("${mbankPath}/hf/member")
public class HFHXMemberController extends AbstractBaseController {

    private static final Log log = LogFactory.getLog(HFHXMemberController.class);

    @Resource
    private HFHXMemberService HFHXMemberService;
    @Resource
    private HFHXMemberVisitService HFHXMemberVisitService;
    @Resource
    private HFHXStationService HFHXStationService;
    @Resource
    private HFHXStationLogService HFHXStationLogService;

    /**
     * 用户登录/验证
     * @param
     * {
     * 	"route": "00002",//00002：验证登录，00003：查询用户信息
	 *  "mobileNo": "138xxxxxxxx",
     * 	"mobileCode": "123456"，密码
     *  "dingdingid":"ddddddddd",钉钉唯一标识
     * }
     * @return  ResultModel
     */
    @RequestMapping(value = "${hfHxPath}/hf/hxMember/Login",method = RequestMethod.POST)
    public ResultModelMap Login(@RequestBody String param){
        ResultModelMap rmm = new ResultModelMap();
        try {

            JSONObject jsonParam = JSON.parseObject(param);

            String route = jsonParam.getString("route");
            String dingdingid = jsonParam.getString("dingdingid");
            //HFHXMemberEntity HFHXMemberEntity = new HFHXMemberEntity();
            String mobileNo = "";
            String mobileCode = "";
            if(route == null || route.trim() == "" || dingdingid == null || dingdingid.trim() == "" ){
            	throw new CommonException("ERROR000003","上送信息有误！");
            }
            if ("00002".equals(route)){
            	mobileNo = jsonParam.getString("mobileNo");
            	mobileCode = jsonParam.getString("mobileCode");
            	 if(mobileNo == null || mobileNo.trim() == "" || mobileCode == null || mobileCode.trim() == "" ){
                 	throw new CommonException("ERROR000003","上送信息有误！");
                 }
            	HFHXMemberEntity HFHXMemberEntity = HFHXMemberService.selectByMemberPhone(mobileNo);
            	if(HFHXMemberEntity != null){
            		String password = HFHXMemberEntity.getPassword();
            		//将输入密码进行MD5加密
            		MD5 MD5 = new MD5();
            		String s = MD5.GetMD5Code(mobileCode);
            		if(s.equals(password)){
            			//更新钉钉唯一标识
            			HFHXMemberEntity.setDingDingId(dingdingid);
            			int i = HFHXMemberService.updateByPrimaryKey(HFHXMemberEntity);
            			if(i <= 0){
            				throw new CommonException("ERROR000012","数据更新失败！");
            			}
            			//查询工位信息
            			String stationId = HFHXMemberEntity.getStationId();
                		HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
                		if(HFHXStationEntity != null){
                			HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
                			HFHXMemberVo HFHXMemberVo = MemberEntityToVo(HFHXMemberEntity,HFHXStationEntity,HFHXStationLogEntity);
                			rmm.put("memberInfo",HFHXMemberVo);
                		}else{
                			throw new CommonException("ERROR000008","未查询到您的工位信息，请联系管理员！");
                		}
            		}else{
            			throw new CommonException("ERROR000004","登录验证失败，请确认输入信息是否正确！");
            		}
            	}else{
            		HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByMemberPhone(mobileNo);
            		if(HFHXMemberVisitEntity != null){
            			
            			String password = HFHXMemberVisitEntity.getPassword();
                		//将输入密码进行MD5加密
						MD5 MD5 = new MD5();
						String s = MD5.GetMD5Code(mobileCode);
                		if(s.equals(password)){
                			//更新钉钉唯一标识
                			HFHXMemberVisitEntity.setDingDingId(dingdingid);
                			int i = HFHXMemberVisitService.updateByPrimaryKey(HFHXMemberVisitEntity);
                			if(i <= 0){
                				throw new CommonException("ERROR000012","数据更新失败！");
                			}
                			//查询工位信息
                			String stationId = HFHXMemberVisitEntity.getStationId();
                    		HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
                    		if(HFHXStationEntity != null){
                    			HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
                    			HFHXMemberVo HFHXMemberVo = MemberEntityToVo(HFHXMemberEntity,HFHXStationEntity,HFHXStationLogEntity);
                    			rmm.put("memberInfo",HFHXMemberVo);
                    		}else{
                    			throw new CommonException("ERROR000008","未查询到您的工位信息，请联系管理员！");
                    		}
                		}else{
                			throw new CommonException("ERROR000004","登录验证失败，请确认输入信息是否正确！");
                		}
            		}else{
            			throw new CommonException("ERROR000005","对不起！您没有访问权限！");
            		}
            	}
            }else if ("00003".equals(route)) {
            	HFHXMemberEntity HFHXMemberEntity = HFHXMemberService.selectByDingDingId(dingdingid);
            	if(HFHXMemberEntity != null){
            		String stationId = HFHXMemberEntity.getStationId();
            		HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
            		if(HFHXStationEntity != null){
            			HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
            			HFHXMemberVo HFHXMemberVo = MemberEntityToVo(HFHXMemberEntity,HFHXStationEntity,HFHXStationLogEntity);
            			rmm.put("memberInfo",HFHXMemberVo);
            		}else{
            			throw new CommonException("ERROR000008","未查询到您的工位信息，请联系管理员！");
            		}
            	}else{
            		HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByDingDingId(dingdingid);
            		if(HFHXMemberVisitEntity != null){
            			String stationId = HFHXMemberVisitEntity.getStationId();
            			HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
                		if(HFHXStationEntity != null){
                			HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
                			HFHXMemberVo HFHXMemberVo = MemberVisitEntityToVo(HFHXMemberVisitEntity,HFHXStationEntity,HFHXStationLogEntity);
                			rmm.put("memberInfo",HFHXMemberVo);
                		}else{
                			throw new CommonException("ERROR000008","未查询到您的工位信息，请联系管理员！");
                		}
            		}else{
            			throw new CommonException("ERROR000002","对不起，未查询到用户信息！");
            		}
            	}
            }else{
            	throw new CommonException("ERROR000001","非法请求！");
            }
        }catch (CommonException e){
            rmm.setException(e);
        }
        return rmm;
    }
    
    
    public HFHXMemberVo MemberEntityToVo(HFHXMemberEntity HFHXMemberEntity,HFHXStationEntity HFHXStationEntity,HFHXStationLogEntity HFHXStationLogEntity){
    	HFHXMemberVo HFHXMemberVo = new HFHXMemberVo();
    	if(HFHXMemberEntity != null){
    		HFHXMemberVo.setId(HFHXMemberEntity.getId());
    		HFHXMemberVo.setMemberId(HFHXMemberEntity.getMemberId());
    		HFHXMemberVo.setMemberNumber(HFHXMemberEntity.getMemberNumber());
    		HFHXMemberVo.setMemberName(HFHXMemberEntity.getMemberName());
    		HFHXMemberVo.setMemberPhone(HFHXMemberEntity.getMemberPhone());
    		HFHXMemberVo.setPassword(HFHXMemberEntity.getPassword());
    		HFHXMemberVo.setMemberType(HFHXMemberEntity.getMemberType());
    		HFHXMemberVo.setCompany(HFHXMemberEntity.getCompany());
    		HFHXMemberVo.setStationId(HFHXMemberEntity.getStationId());
    		HFHXMemberVo.setStatus(HFHXMemberEntity.getStatus());
    		HFHXMemberVo.setStatusType(HFHXMemberEntity.getStatusType());
    		HFHXMemberVo.setDingDingId(HFHXMemberEntity.getDingDingId());
    	}
    	if(HFHXStationEntity != null){
    		HFHXMemberVo.setsId(HFHXStationEntity.getId());
    		HFHXMemberVo.setStationCity(HFHXStationEntity.getStationCity());
    		HFHXMemberVo.setStationPlace(HFHXStationEntity.getStationPlace());
    		HFHXMemberVo.setStationAddress(HFHXStationEntity.getStationAddress());
    		HFHXMemberVo.setStationfloor(HFHXStationEntity.getStationfloor());
    		HFHXMemberVo.setStationroom(HFHXStationEntity.getStationroom());
    		HFHXMemberVo.setStationType(HFHXStationEntity.getStationType());
    	}
    	if(HFHXStationLogEntity != null){
    		HFHXMemberVo.setlId(HFHXStationLogEntity.getId());
    		HFHXMemberVo.setStationLogType(HFHXStationLogEntity.getStationLogType());
    		HFHXMemberVo.setStationStatus(HFHXStationLogEntity.getStationStatus());
			Date createTime = HFHXStationLogEntity.getCreateTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = formatter.format(createTime);
			HFHXMemberVo.setCreateTime(format);
    	}
    	return HFHXMemberVo;
    	
    }
    public HFHXMemberVo MemberVisitEntityToVo(HFHXMemberVisitEntity HFHXMemberVisitEntity, HFHXStationEntity HFHXStationEntity, HFHXStationLogEntity HFHXStationLogEntity){
    	HFHXMemberVo HFHXMemberVo = new HFHXMemberVo();
    	if(HFHXMemberVisitEntity != null){
    		HFHXMemberVo.setId(HFHXMemberVisitEntity.getId());
    		HFHXMemberVo.setMemberId(HFHXMemberVisitEntity.getMemberId());
    		HFHXMemberVo.setMemberNumber(HFHXMemberVisitEntity.getMemberNumber());
    		HFHXMemberVo.setMemberName(HFHXMemberVisitEntity.getMemberName());
    		HFHXMemberVo.setMemberPhone(HFHXMemberVisitEntity.getMemberPhone());
    		HFHXMemberVo.setPassword(HFHXMemberVisitEntity.getPassword());
    		HFHXMemberVo.setMemberType(HFHXMemberVisitEntity.getMemberType());
    		HFHXMemberVo.setCompany(HFHXMemberVisitEntity.getCompany());
    		HFHXMemberVo.setStationId(HFHXMemberVisitEntity.getStationId());
    		HFHXMemberVo.setStatus(HFHXMemberVisitEntity.getStatus());
    		HFHXMemberVo.setStatusType(HFHXMemberVisitEntity.getStatusType());
    		HFHXMemberVo.setDingDingId(HFHXMemberVisitEntity.getDingDingId());
    	}
    	if(HFHXStationEntity != null){
    		HFHXMemberVo.setsId(HFHXStationEntity.getId());
    		HFHXMemberVo.setStationCity(HFHXStationEntity.getStationCity());
    		HFHXMemberVo.setStationPlace(HFHXStationEntity.getStationPlace());
    		HFHXMemberVo.setStationAddress(HFHXStationEntity.getStationAddress());
    		HFHXMemberVo.setStationfloor(HFHXStationEntity.getStationfloor());
    		HFHXMemberVo.setStationroom(HFHXStationEntity.getStationroom());
    		HFHXMemberVo.setStationType(HFHXStationEntity.getStationType());
    	}
    	if(HFHXStationLogEntity != null){
    		HFHXMemberVo.setlId(HFHXStationLogEntity.getId());
    		HFHXMemberVo.setStationLogType(HFHXStationLogEntity.getStationLogType());
    		HFHXMemberVo.setStationStatus(HFHXStationLogEntity.getStationStatus());
			Date createTime = HFHXStationLogEntity.getCreateTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = formatter.format(createTime);
			HFHXMemberVo.setCreateTime(format);
    	}
    	return HFHXMemberVo;
    	
    }

    /**
     * 用户工位信息查询
     *{
     * 	"route": "00001",//00001:查询工位信息
     *  "memberId": "HF0000001",//用户唯一标识	
     *  "stationId": "14110"//工位号
     * }
     * @return
     */
    @RequestMapping(value = "${hfHxPath}/hf/hxMember/stationstatus",method = RequestMethod.POST)
    public ResultModelMap stationstatus(@RequestBody String param){
        ResultModelMap rmm = new ResultModelMap();
        try {

            JSONObject jsonParam = JSON.parseObject(param);

            String route = jsonParam.getString("route");
            String memberId = jsonParam.getString("memberId");
            String stationId = jsonParam.getString("stationId");
            if(route == null || route.trim() == "" || memberId == null || memberId.trim() == "" || stationId == null || stationId.trim() == "" ){
            	throw new CommonException("ERROR000003","上送信息有误！");
            }
            if ("00001".equals(route)){
            	HFHXMemberVo HFHXMemberVo =memberQuert(memberId,stationId);
            	if(HFHXMemberVo != null){
            		rmm.put("memberInfo",HFHXMemberVo);
            	}
            } else{
            	throw new CommonException("ERROR000001","非法请求！");
            }
        }catch (CommonException e){
            rmm.setException(e);
        }
        return rmm;
    }
    /**
     * 用户入座出座申请
     *{
     * 	"route": "00004",//00004:入座申请,00005出座申请
     *  "memberId": "HF0000001",//用户唯一标识	
     *  "stationId": "14110"//工位号
     *  "statusType": "14110"//用户表：0:用户表，1:拜访表
     * }
     * @return
     */
    @RequestMapping(value = "${hfHxPath}/hf/hxMember/stationInToOut",method = RequestMethod.POST)
    public ResultModelMap stationInToOut(@RequestBody String param){
        ResultModelMap rmm = new ResultModelMap();
        try {
        	JSONObject jsonParam = JSON.parseObject(param);

            String route = jsonParam.getString("route");
            String memberId = jsonParam.getString("memberId");
            String stationId = jsonParam.getString("stationId");
            String statusType = jsonParam.getString("statusType");
            if(route == null || route.trim() == "" || memberId == null || memberId.trim() == "" || stationId == null || stationId.trim() == "" || statusType == null || statusType.trim() == "" ){
            	throw new CommonException("ERROR000003","上送信息有误！");
            }
            HFHXMemberVo HFHXMemberVo =memberQuert(memberId,stationId);
        	if(HFHXMemberVo != null){
        		String stationType = HFHXMemberVo.getStationType();
        		if("00004".equals(route)){
        			if("1".equals(stationType)){
        				String id = HFHXMemberVo.getsId();
						Date createTime= new Date();
        				boolean a = HFHXStationService.StationInOrOut(memberId,stationId,statusType,"0",id,"2",createTime);
        				if(a){
        					String memberNumber ="";
        					if("0".equals(statusType)){
								HFHXMemberEntity HFHXMemberEntity = HFHXMemberService.selectByMemberId(memberId);
								memberNumber = HFHXMemberEntity.getMemberNumber();
							}else{
								HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByMemberId(memberId);
								memberNumber = HFHXMemberVisitEntity.getMemberNumber();
							}
							HFHXStationVo HFHXStationVo = new HFHXStationVo();
							HFHXStationVo.setFlag("01");
							HFHXStationVo.setMemberId(memberId);
							HFHXStationVo.setMemberNumber(memberNumber);
							HFHXStationVo.setStationId(stationId);
							HFHXStationVo.setFlagMsg("入座成功！");
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String dateString = formatter.format(createTime);
							HFHXStationVo.setDate(dateString);
        					rmm.put("HFHXStationVo",HFHXStationVo);
        				}else{
        					throw new CommonException("ERROR000010","入座失败，请稍后重试！");
        				}
        			}else if("0".equals(stationType)){
        				throw new CommonException("ERROR000013","工位信息有误！请联系管理员");
        			}else{
        				throw new CommonException("ERROR000014","该工位已被签到！");
        			}
        		}else if("00005".equals(route)){
        			if("2".equals(stationType)){
        				String id = HFHXMemberVo.getsId();
        				Date createTime= new Date();
        				boolean a = HFHXStationService.StationInOrOut(memberId,stationId,statusType,"1",id,"1",createTime);
        				if(a){
							String memberNumber ="";
							if("0".equals(statusType)){
								HFHXMemberEntity HFHXMemberEntity = HFHXMemberService.selectByMemberId(memberId);
								memberNumber = HFHXMemberEntity.getMemberNumber();
							}else{
								HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByMemberId(memberId);
								memberNumber = HFHXMemberVisitEntity.getMemberNumber();
							}
							HFHXStationVo HFHXStationVo = new HFHXStationVo();
							HFHXStationVo.setFlag("02");
							HFHXStationVo.setMemberId(memberId);
							HFHXStationVo.setMemberNumber(memberNumber);
							HFHXStationVo.setStationId(stationId);
							HFHXStationVo.setFlagMsg("出座成功！");
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String dateString = formatter.format(createTime);
							HFHXStationVo.setDate(dateString);
							rmm.put("HFHXStationVo",HFHXStationVo);
        				}else{
        					throw new CommonException("ERROR000010","入座失败，请稍后重试！");
        				}
        			}else if("0".equals(stationType)){
        				throw new CommonException("ERROR000013","工位信息有误！请联系管理员");
        			}else{
        				throw new CommonException("ERROR000014","该工位已被签到！");
        			}
        		}else{
        			throw new CommonException("ERROR000001","非法请求！");
        		}
        	}
        }catch(CommonException e){
        	rmm.setException(e);
        }
    	return rmm;
	}
    
    /**
     * 拜访记录查询
     *{
     * 	"route": "00006",//拜访记录查询
     *  "memberId": "HF0000001",//用户唯一标识	
     *  "date": "2020-11-10",//日期
     * }
     * @return
     */
    @RequestMapping(value = "${hfHxPath}/hf/hxMember/stationVisitQuery",method = RequestMethod.POST)
    public ResultModelMap stationVisitQuery(@RequestBody String param){
        ResultModelMap rmm = new ResultModelMap();
        try {
        	JSONObject jsonParam = JSON.parseObject(param);

            String route = jsonParam.getString("route");
            String memberId = jsonParam.getString("memberId");
            String date = jsonParam.getString("date");
            if(route == null || route.trim() == "" || memberId == null || memberId.trim() == ""|| date == null || date.trim() == ""){
            	throw new CommonException("ERROR000003","上送信息有误！");
            }
            if("00006".equals(route)){
				String startTime = date;
				String endTime = "";
				try{
					Calendar calendar   =   new   GregorianCalendar();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					//String dateString = formatter.format(date);
					Date parse = formatter.parse(startTime);
					calendar.setTime(parse);
					calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
					Date date1 =calendar.getTime();   //这个时间就是日期往后推一天的结果
					System.out.println(date1.toString());
					endTime = formatter.format(date1);
				}catch ( ParseException e){
					throw new CommonException("ERROR000001","非法请求！");
				}
				QueryStationLogVo QueryStationLogVo = new QueryStationLogVo();
				QueryStationLogVo.setMemberId(memberId);
				QueryStationLogVo.setStartTime(startTime);
				QueryStationLogVo.setEndTime(endTime);
				QueryStationLogVo.setStationLogType("1");
            	List<HFHXStationLogEntity> HFHXStationLogEntityList = HFHXStationLogService.selectByMemberIdnew(QueryStationLogVo);
            	System.out.println("HFHXStationLogEntityList=====:"+HFHXStationLogEntityList.size());
            	HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByMemberId(memberId);
				List<HFHXMemberVo> HFHXMemberVoList = new ArrayList<HFHXMemberVo>();
				System.out.println("HFHXMemberVoList=====:"+HFHXMemberVoList.toString());
				if(HFHXMemberVisitEntity != null){
					String stationId = HFHXMemberVisitEntity.getStationId();
					HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
					for(HFHXStationLogEntity out:HFHXStationLogEntityList){
						HFHXMemberVo HFHXMemberVo = MemberVisitEntityToVo(HFHXMemberVisitEntity,HFHXStationEntity,out);
						HFHXMemberVoList.add(HFHXMemberVo);
					}
					rmm.put("HFHXMemberVoList",HFHXMemberVoList);
				}else{
					rmm.put("HFHXMemberVoList",HFHXMemberVoList);
				}
            }else{
            	throw new CommonException("ERROR000001","非法请求！");
            }
        }catch(CommonException e){
        	rmm.setException(e);
        }
        return rmm;
    }
    public HFHXMemberVo memberQuert(String memberId,String stationId) throws CommonException{
    	HFHXMemberVo HFHXMemberVo;
    	HFHXMemberEntity HFHXMemberEntity = HFHXMemberService.selectByMemberId(memberId);
    	if(HFHXMemberEntity != null){
    		//用户表
    		String s = HFHXMemberEntity.getStationId();
    		if(stationId.equals(s)){
    			HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
    			if(HFHXStationEntity != null){
    				HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
    				HFHXMemberVo = MemberEntityToVo(HFHXMemberEntity,HFHXStationEntity,HFHXStationLogEntity);
    			}else{
    				throw new CommonException("ERROR000008","未查询到您的工位信息，请联系管理员！");
    			}
    		}else{
    			//拜访表
    			HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByMemberId(memberId);
    			if(HFHXMemberVisitEntity != null){
    				String n = HFHXMemberVisitEntity.getStationId();
    				if(stationId.equals(n)){
    					HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
            			if(HFHXStationEntity != null){
            				HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
            				HFHXMemberVo = MemberVisitEntityToVo(HFHXMemberVisitEntity,HFHXStationEntity,HFHXStationLogEntity);
            			}else{
            				throw new CommonException("ERROR000008","未查询到您的工位信息，请联系管理员！");
            			}
    				}else{
    					throw new CommonException("ERROR000006","请确认是否是您的工位！");
    				}
    			}else{
    				throw new CommonException("ERROR000006","请确认是否是您的工位！");
    			}
    		}
    	}else{
    		//拜访表
			HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByMemberId(memberId);
			if(HFHXMemberVisitEntity != null){
				String n = HFHXMemberVisitEntity.getStationId();
				if(stationId.equals(n)){
					HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
        			if(HFHXStationEntity != null){
        				HFHXStationLogEntity HFHXStationLogEntity = new HFHXStationLogEntity();
        				HFHXMemberVo = MemberVisitEntityToVo(HFHXMemberVisitEntity,HFHXStationEntity,HFHXStationLogEntity);
        			}else{
        				throw new CommonException("ERROR000008","未查询到您的工位信息，请联系管理员！");
        			}
				}else{
					throw new CommonException("ERROR000006","请确认是否是您的工位！");
				}
			}else{
				throw new CommonException("ERROR000005","对不起！您没有访问权限！");
			}
    	}
    	return HFHXMemberVo;
    }
	/*public static void main(String[] args) throws CommonException {
    	String date = "2020-11-23 00:00:00";
    	String memberId ="HF0000000002";
		String startTime = date;
		String endTime = "";

		try{
			Calendar calendar   =   new   GregorianCalendar();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//String dateString = formatter.format(date);
			Date parse = formatter.parse(date);
			calendar.setTime(parse);
			calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			Date date1 =calendar.getTime();   //这个时间就是日期往后推一天的结果
			System.out.println(date1.toString());
			endTime = formatter.format(date1);
		}catch ( ParseException e){
			System.out.println("11111");
		}
		QueryStationLogVo QueryStationLogVo = new QueryStationLogVo();
		QueryStationLogVo.setMemberId(memberId);
		QueryStationLogVo.setStartTime(startTime);
		QueryStationLogVo.setEndTime(endTime);
		System.out.println(memberId);
		System.out.println(startTime);
		System.out.println(endTime);
		List<HFHXStationLogEntity> HFHXStationLogEntityList = HFHXStationLogService.selectByMemberId("HF0000000002");
		//List<HFHXStationLogEntity> HFHXStationLogEntityList = HFHXStationLogService.selectByMemberIdnew(QueryStationLogVo);
		System.out.println("HFHXStationLogEntityList=====:"+HFHXStationLogEntityList.size());
		HFHXMemberVisitEntity HFHXMemberVisitEntity = HFHXMemberVisitService.selectByMemberId(memberId);
		List<HFHXMemberVo> HFHXMemberVoList = new ArrayList<HFHXMemberVo>();
		System.out.println("HFHXMemberVoList=====:"+HFHXMemberVoList.toString());
		if(HFHXMemberVisitEntity != null){
			String stationId = HFHXMemberVisitEntity.getStationId();
			HFHXStationEntity HFHXStationEntity = HFHXStationService.selectByStationId(stationId);
			for(HFHXStationLogEntity out:HFHXStationLogEntityList){
				HFHXMemberVo HFHXMemberVo = MemberVisitEntityToVo(HFHXMemberVisitEntity,HFHXStationEntity,out);
				HFHXMemberVoList.add(HFHXMemberVo);
			}
		}else{
		}
	}*/
}
