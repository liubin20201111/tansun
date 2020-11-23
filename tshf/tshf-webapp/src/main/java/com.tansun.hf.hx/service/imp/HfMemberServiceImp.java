package com.tansun.hf.hx.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.jeespring.common.json.CommonException;
import com.jeespring.common.service.AbstractBaseService;
import com.tansun.hf.hx.dao.HfMemberMapper;
import com.tansun.hf.hx.entity.HfMember;
import com.tansun.hf.hx.service.IHfMemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class HfMemberServiceImp extends AbstractBaseService<HfMemberMapper, HfMember> implements IHfMemberService {
    private static final Log log = LogFactory.getLog(HfMemberServiceImp.class);

    private static final String projectFirstName = "hxgc";
    @Resource
    private HfMemberMapper memberMapper;

    public boolean verificationLogin(String mobileNo, String mobileCode) throws CommonException {
        Boolean flag = false;
        try {
            //手机号后四位

            String str = null;
            if (mobileNo.length() > 4) {
                str = mobileNo.substring(mobileNo.length()-4,mobileNo.length());
            }else {
                throw new CommonException("EBLN0001","请输入正确的手机号码");
            }

            String newStr = projectFirstName + str;

            System.out.println(newStr); // 将输bai出字符串最后三位字符fgh

            //验证短信验证码
            if (!newStr.equals(mobileCode)){
                throw new CommonException("EBLN0002","短信验证码错误");
            }

            //通过手机号查询是否有数据存在
            HfMember hfMember = memberMapper.selectByMobile(mobileNo);

            HfMember member = new HfMember();
            member.setCreateTime(new Date());
            member.setMobile(mobileNo);
            member.setName("恒心用户");
            member.setStatus("1");
            member.setCreateTime(new Date());
            if (hfMember != null) {
                member.setId(hfMember.getId());
                int count = memberMapper.updateByPrimaryKeySelective(member);
                if (count > 0){
                    flag = true;
                }
            }else {
                throw new CommonException("","用户不存在");
            }
        }catch (CommonException e){
            e.printStackTrace();
            throw new CommonException(e.geteCode(),e.getMessage());
        }
        return flag;
    }

    @Override
    public HfMember getMemberInfo(String mobileNo) throws CommonException {
        return memberMapper.selectByMobile(mobileNo);
    }
}
