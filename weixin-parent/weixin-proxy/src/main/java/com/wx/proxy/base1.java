package com.wx.proxy;

import com.weixin.net.WXNetUtil;

/**
 * 获取token
 */
public class base1 {


    public static void main(String[] args) {
        String httpGet = WXNetUtil.httpGet("https://www.baidu.com");
        System.out.println(httpGet);
    }


}
