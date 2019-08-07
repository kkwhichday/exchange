package com.onemore.exchange.service;


import com.onemore.exchange.model.ExMsg;
import com.onemore.exchange.model.WxUser;

import java.util.List;

public interface WxUserService {


        /**
         * 微信用戶登錄
         * @param appid 小程序ID
         * @param secret 小程序密鑰
         * @param code 登錄憑證
         * @return String JSON格式數據，包括openid以及session_key
         */
        String login(String appid, String secret, String code) throws Exception;

        /**
         * 獲取所有微信用戶
         * @return List<WxUser> 微信用戶信息列表
         */
        List<WxUser> listAllWxUser();

        /**
         * 添加新微信用戶
         * @param wxUser WxUser類對象
         * @return int 用戶id號
         */
        int saveWxUser(WxUser wxUser);

        int saveExMsg(ExMsg exMsg);
        int updateExMsg(ExMsg exMsg);
   
        
        
        List<ExMsg> listAllExMsg(Integer pageSize, Integer pageNum,String openid);


        WxUser getWxUser(String openId);

}
