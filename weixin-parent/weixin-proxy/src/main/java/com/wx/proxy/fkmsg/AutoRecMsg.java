package com.wx.proxy.fkmsg;


import com.wx.proxy.base.WXRedisUtil;

import java.util.Date;
import java.util.Random;

/**
 * 自动回复消息
 */
public class AutoRecMsg {


    /**
     * @param toUser   接收人
     * @param fromUser 发送人
     * @param context  接收的消息
     * @return
     */
    public String autoMsg(String toUser, String fromUser, String context) {

        //1.关键字对应的回复的内容
        WXRedisUtil redisUtil = new WXRedisUtil();
        String contextMsg = redisUtil.get(context);

        if (contextMsg == null || contextMsg.equals("")) {
            contextMsg = "该关键字不存在,请联系管理员";
        }

        String rec = "<xml><ToUserName>" + toUser + "</ToUserName>" +
                "<FromUserName>" + fromUser + "</FromUserName>" +
                "<CreateTime>" + new Date().getTime() + "</CreateTime>" +
                "<MsgType>text</MsgType>" +
                "<Content>" + contextMsg + "</Content>" +
                "</xml>";
        return rec;

    }

    /**
     * 石头剪刀布
     * @param fromUser
     * @param toUser
     * @param eventKey
     * @return
     */
    public String game( String fromUser,String toUser,  String eventKey) {

        String[] answerArray = {"石头", "剪刀", "布"}; //0-2
        int index = new Random().nextInt(3);//0-2 不包括指定的值

        String key = "";
        System.out.println("fromUser:"+fromUser+"--toUser:"+toUser+"--eventKey:"+eventKey);
        String contextMsg = "";
        String answer = answerArray[index];
        switch (eventKey) {
            case "V1001_SHITOU":
                key = "石头";
                if (answer.equals(answerArray[0]))
                    contextMsg = "平局";
                else if (answer.equals(answerArray[1])) {
                    contextMsg = "赢";
                } else {
                    contextMsg = "输";
                }
                break;
            case "V1001_JIANDAN":
                key = "剪刀";
                if (answer.equals(answerArray[0]))
                    contextMsg = "输";
                else if (answer.equals(answerArray[1])) {
                    contextMsg = "平局";
                } else {
                    contextMsg = "赢";
                }
                break;
            case "V1001_BU":
                key = "布";
                if (answer.equals(answerArray[0]))
                    contextMsg = "赢";
                else if (answer.equals(answerArray[1])) {
                    contextMsg = "输";
                } else {
                    contextMsg = "平局";
                }
                break;
        }

        String reslut = "系统【"+answer+"】，您【"+key+"】 结果:"+contextMsg;

        String rec = "<xml><ToUserName>" + fromUser + "</ToUserName>" +
                "<FromUserName>" + toUser+ "</FromUserName>" +
                "<CreateTime>" + new Date().getTime() + "</CreateTime>" +
                "<MsgType>text</MsgType>" +
                "<Content>" + reslut + "</Content>" +
                "</xml>";
        System.out.println(reslut);
        return rec;

    }

}
