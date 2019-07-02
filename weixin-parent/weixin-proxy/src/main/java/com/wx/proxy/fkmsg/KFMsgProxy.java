package com.wx.proxy.fkmsg;

import com.alibaba.fastjson.JSONObject;
import com.weixin.net.WXNetUtil;
import com.wx.proxy.base.TokenUtil;

/**
 * 客服消息-发消息
 */
public class KFMsgProxy {


    /**
     * 发生消息
     *
     * @param msgBody 需要发送消息的json 格式的数据
     * @return
     */
    public boolean sendKfMsg(String msgBody) {

        String send_kf_msg_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + TokenUtil.getToken();

        String result = WXNetUtil.httpPost(send_kf_msg_url, msgBody);

        System.out.println("发送消息:" + result);

        //正确::{"errcode":0,"errmsg":"ok"}
        if ("ok".equalsIgnoreCase(JSONObject.parseObject(result).getString("errmsg"))) {
            return true;
        }

        return false;
    }


}
