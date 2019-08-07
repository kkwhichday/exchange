package com.onemore.exchange.controller;

import javax.annotation.Resource;

import com.onemore.exchange.common.CommonResult;
import com.onemore.exchange.model.ExMsg;
import com.onemore.exchange.model.WxUser;
import com.onemore.exchange.service.WxUserService;
import com.onemore.exchange.utils.HttpsUtil;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 微信用戶信息JSON控制
 */
@Controller
@RequestMapping(value="/wxUser")
public class WxUserJSONController {

    @Resource
    private WxUserService wxUserService;

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    /**
     * 用戶登錄獲取openId以及已綁定的手機號
     * @param code 微信用戶code
     * @return String 微信用戶openId以及已綁定的手機號
     * @throws Exception
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public  String login(@RequestParam String code) throws Exception {
        System.out.println("code为：" + code+"appid="+appid+"secret="+secret);
        // 發起方羅請求，通過appid，secret以及code獲取微信用戶數據
        String json = HttpsUtil.getSession_keyAndOpenID(appid, secret, code);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String openId = jsonObject.getString("openid");	// 微信用戶openId
        System.out.println("openId为：" + openId);

        // 創建JSONObject對象
        JSONObject result = new JSONObject();
        // 將openId，phone以及id添加到JSONOjbect對象中
        result.put("openId", openId);


        return result.toJSONString();
    }



    /**
     * 保存用戶信息
     * @return String
     */
    @ApiOperation("首次登录保存用户信息")
    @RequestMapping(value = "/saveUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public  Object saveUserInfo(@RequestBody WxUser wxUser) {
//        System.out.println("手机号：" + phone + "\nopenID：" + openid);
        // 通過用戶openId獲取用戶id號
    	System.out.println(wxUser);
        WxUser oldwxUser = wxUserService.getWxUser(wxUser.getOpenid());
        if(oldwxUser.getId()==null){
            wxUser.setDeleted(0);
            wxUser.setLastlogintime(new Date());
            int i = wxUserService.saveWxUser(wxUser);
            if (i > 0) return new CommonResult().success("用户信息保存成功");
        }
        return new CommonResult().success("非首次登录,无需保存");
    }


    @ApiOperation("发布换币信息")
    @RequestMapping(value = "/publishMsg",method = RequestMethod.POST)
    @ResponseBody
    public  Object publishMsg(@RequestBody ExMsg exMsg) throws Exception {
    	
    	exMsg.setCreatetime(new Date());
    	exMsg.setUpdatetime(new Date());
        int result = wxUserService.saveExMsg(exMsg);
//        return result.toJSONString();
        return result>0?new CommonResult().success("信息保存成功"):new CommonResult().success("信息保存失败");
    }

    @ApiOperation("更新发布换币信息")
    @RequestMapping(value = "/updateMsg",method = RequestMethod.POST)
    @ResponseBody
    public  Object updateMsg(@RequestBody ExMsg exMsg) throws Exception {
    	
    	exMsg.setUpdatetime(new Date());
        int result = wxUserService.updateExMsg(exMsg);
//        return result.toJSONString();
        return result>0?new CommonResult().success("信息更新成功"):new CommonResult().success("信息保存失败");
    }

    @ApiOperation("分页获取换币发布信息")
    @RequestMapping(value = "/getAllMsg", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllMsg(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "openid",required=false) String openid) {
        List<ExMsg> exMsgList = wxUserService.listAllExMsg(pageSize, pageNum,openid);
        return new CommonResult().success(exMsgList);
    }



}