package com.wx.proxy.base;

import com.alibaba.fastjson.JSONObject;
import com.weixin.net.WXNetUtil;

/**
 * 获取用户凭据
 */
public class TokenUtil {


    private static String applid = "wx380a000a13ee093b";
    private static String appsecret = "24f652f68b35dd17e0d57dfc583ff47e";


    /**
     * 获取token
     * @return token
     */
    public static String getToken(){

        //1.先从redis获取，如果为空。没有token
        WXRedisUtil wxRedisUtil = new WXRedisUtil();
        String token = wxRedisUtil.get("wx-token");
        if(token!=null)
        {
            return token;
        }
        else
        {
            System.out.println("======>远程获取token<===========");
            //重新去远程微信获取
            String token1 = remoteGetToken();
            return token1;

        }
        //2.需要远程获取。并且将远程获取的token放入redis.
    }


    /**
     * 从微信平台 获取token
     * @return
     */
    private static String remoteGetToken(){

        String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+applid+"&secret="+appsecret;

        //通过 httpGet 直接调用
        String reslut = WXNetUtil.httpGet(token_url);

        //结果的返回值:由于腾讯决定
        //正确:{"access_token":"ACCESS_TOKEN","expires_in":7200}
        //错误:{"errcode":40013,"errmsg":"invalid appid"}

        //需要的是:ACCESS_TOKEN
        //json字符串 转为===>json对象
        JSONObject jsonObject = JSONObject.parseObject(reslut);

        if(jsonObject.containsKey("access_token")){ //返回正确结果:access_token腾讯api获取

            String token = jsonObject.getString("access_token");
            WXRedisUtil redisUtil = new WXRedisUtil();

            //key:随意 获取的 通过这个key来获取
            redisUtil.set("wx-token",token,7000); //设置有效时间
            return token;
        }else
        {
            //没有包含access_token。返回错误结果
            System.out.println("=====>获取token异常【"+reslut+"】");
        }

        return null;
    }


    public static void main(String[] args) {
        String token = getToken();
        System.out.println(token);
    }



}
