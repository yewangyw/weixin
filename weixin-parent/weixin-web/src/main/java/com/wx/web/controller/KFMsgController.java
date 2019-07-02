package com.wx.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.proxy.fkmsg.KFMsgProxy;
import com.wx.web.bean.msg.*;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送客服消息
 */
@Controller
public class KFMsgController {


    /**
     * @param touser 需要接收消息 openId
     * @return
     */
    @RequestMapping("/send/kfmsg")
    @ResponseBody
    public boolean sendKFMsg(String touser, String msgBody, String msgtype, NewsContent content) {

        String msgParam = null;
        if (MsgType.TEXT_MSG.equals(msgtype)) {
            //消息的文本
             msgParam = createKfMsgParam(touser, msgBody);
        } else if (MsgType.IMG_MSG.equals(msgtype)) {
            msgParam = createKfMsgParamImg(touser, content);
        }
        else{
            System.out.println("暂不支持其他消息类型.....");
            return false;
        }
        KFMsgProxy msgProxy = new KFMsgProxy();
        boolean msg = msgProxy.sendKfMsg(msgParam);
        return msg;
    }

    /**
     * 封装客服消息  文本消息
     *
     * @param touser
     * @param msgBody
     * @return
     */
    private String createKfMsgParam(String touser, String msgBody) {
        //封装参数
        TextMsg textMsg = new TextMsg();
        textMsg.setTouser(touser);
        textMsg.setMsgtype(MsgType.TEXT_MSG);
        TextMsgItem text = new TextMsgItem();
        text.setContent(msgBody);
        textMsg.setText(text);
        String jsonString = JSONObject.toJSONString(textMsg);
        System.out.println(jsonString);
        return jsonString;
    }

    private String createKfMsgParamImg(String touser, NewsContent content) {
        //封装参数

        NewsMsg newsMsg = new NewsMsg();
        newsMsg.setTouser(touser);
        newsMsg.setMsgtype(MsgType.IMG_MSG);
        NewsMsgItem item = new NewsMsgItem();
        List<NewsContent> list = new ArrayList<>();

        //NewsContent content = new NewsContent();
        //content.setTitle("油炸蚂蚱");
        //content.setDescription("很香很脆");
        //content.setPicurl("图片");
        //content.setUrl("xxxxxx");
        list.add(content);

        item.setArticles(list);
        newsMsg.setNews(item);


        String s = JSONObject.toJSONString(newsMsg);
        System.out.println(s);
        return s;
    }


}
