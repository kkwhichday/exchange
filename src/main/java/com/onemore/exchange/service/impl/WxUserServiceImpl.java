package com.onemore.exchange.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.onemore.exchange.mapper.ExMsgMapper;
import com.onemore.exchange.mapper.WxUserMapper;
import com.onemore.exchange.model.ExMsg;
import com.onemore.exchange.model.ExMsgExample;
import com.onemore.exchange.model.WxUser;
import com.onemore.exchange.model.WxUserExample;
import com.onemore.exchange.service.WxUserService;
import com.onemore.exchange.utils.HttpsUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Resource
    private WxUserMapper wxUserDao;

    @Resource
    private ExMsgMapper exMsgMapper;

    /**
     * 微信用戶登錄
     * @param appid 小程序ID
     * @param secret 小程序密鑰
     * @param code 登錄憑證
     * @return String JSON格式數據，包括openid以及session_key
     */
    public String login(String appid, String secret, String code) throws Exception {
        String json = HttpsUtil.getSession_keyAndOpenID(appid, secret, code);
        System.out.println(json);
        return null;
    }

    /**
     * 獲取所有微信用戶
     * @return List<WxUser> 微信用戶信息列表
     */
    public List<WxUser> listAllWxUser() {
        WxUserExample wxUserExample = new WxUserExample();
        wxUserExample.createCriteria().andDeletedEqualTo(0);
        List<WxUser> wxUsers = wxUserDao.selectByExample(wxUserExample);
        if (wxUsers.size() <= 0) {
            System.out.println("數據庫表為空！");
        }
        return wxUsers;
    }

    /**
     * 添加新微信用戶
     * @param wxUser WxUser類對象
     * @return int 用戶id號
     */
    public int saveWxUser(WxUser wxUser) {
        wxUserDao.insert(wxUser);
        int i = wxUser.getId();
        if (i > 0) {
            System.out.println("添加成功，微信用戶Id號為：" + i);
        } else {
            System.out.println("添加失敗！");
        }
        return i;
    }
    
    


    /**
     * 更新发布消息
     * @param exMsg 
     * @return int 
     */
    public int updateExMsg(ExMsg exMsg) {
    	int i =exMsgMapper.updateByPrimaryKeySelective(exMsg);
  
        if (i > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失敗！");
        }
        return i;
    }
   

    /**
     * 根據用戶openId獲取用戶Id
     * @param openId 微信用戶openId
     * @return 用戶Id
     */
    public WxUser getWxUser(String openId) {

        WxUserExample wxUserExample = new WxUserExample();
        wxUserExample.createCriteria().andOpenidEqualTo(openId);
        return Optional.of(wxUserDao.selectByExample(wxUserExample))
        		.filter((t)->t.size()>0).
                orElse(Arrays.asList(new WxUser())).get(0);

    }
    
  



    public int saveExMsg(ExMsg exMsg){
        exMsgMapper.insert(exMsg);
        int i = exMsg.getId();
        if (i > 0) {
            System.out.println("添加成功，微信用戶openId號為：" + exMsg.getOpenid());
        } else {
            System.out.println("添加失敗！");
        }
        return i;

    }


    public List<ExMsg> listAllExMsg(Integer pageSize, Integer pageNum,String openid){
        PageHelper.startPage(pageNum,pageSize);
        ExMsgExample example = new ExMsgExample();
        example.setOrderByClause("updateTime desc");
        if(StringUtils.isEmpty(openid)){
        	example.createCriteria()
                .andDeletedEqualTo(0);
        }else{
        	example.createCriteria()
            .andDeletedEqualTo(0).andOpenidEqualTo(openid);
        }
              
        return exMsgMapper.selectByExample(example);
    }


    public static void main(String args[]){
    	System.out.println(Optional.ofNullable(null).orElse(123));
        System.out.println(Arrays.asList(new WxUser()).get(0));
    }

}