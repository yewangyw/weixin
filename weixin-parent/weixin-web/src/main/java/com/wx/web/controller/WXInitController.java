package com.wx.web.controller;

import com.wx.proxy.fkmsg.AutoRecMsg;
import com.wx.web.util.SecurityKit;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class WXInitController {

    String token = "yewang";  //令牌

    @RequestMapping(value = "/wxinit", method = RequestMethod.GET)
    @ResponseBody
    public String init(String signature, String timestamp, String nonce, String echostr) {

        //1）将token、timestamp、nonce三个参数进行字典序排序
        String paramArray[] = {token, timestamp, nonce};
        Arrays.sort(paramArray);

        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String param = "";
        for (String s : paramArray) {
            param += s;
        }
        String sha1 = SecurityKit.sha1(param);

        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (sha1.equals(signature)) {
            System.out.println("接入成功!!");
            return echostr;
        }

        //4.请原样返回echostr参数内容，则接入生效

        return null;
    }

    @RequestMapping(value = "/wxinit", method = RequestMethod.POST)
    @ResponseBody
    public String init2(HttpServletRequest request) {


        try {
            ServletInputStream inputStream = request.getInputStream();

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream); //文档类型
            Element xml = document.getRootElement(); //根节点

            String msgType = xml.elementText("MsgType");

            System.out.println(document.getText());

            String toUserName = xml.elementText("ToUserName"); //接收 ToUserName 回复:FromUserName
            String fromUserName = xml.elementText("FromUserName"); //接收 ToUserName 回复:FromUserName
            if ("text".equals(msgType)) {

                /*String toUserName = xml.elementText("ToUserName"); //接收 ToUserName 回复:FromUserName
                String fromUserName = xml.elementText("FromUserName"); //接收 ToUserName 回复:FromUserName*/
                String content = xml.elementText("Content");
                AutoRecMsg autoRecMsg = new AutoRecMsg();
                String autoMsg = autoRecMsg.autoMsg(fromUserName, toUserName, content);

                System.out.println("收到:【" + content + "】 回复:" + autoMsg);

                return autoMsg;
            } else if ("event".equals(msgType)) {

                String eventKey = xml.elementText("EventKey");
                String game = new AutoRecMsg().game(fromUserName, toUserName, eventKey);
                return game;

            } else {
                System.out.println("该功能正在开发中....");
                //直接回复  该功能正在开发中....
                /*String toUserName = xml.elementText("ToUserName"); //接收 ToUserName 回复:FromUserName
                String fromUserName = xml.elementText("FromUserName"); //接收 ToUserName 回复:FromUserName*/
                String rec = "<xml><ToUserName>" + fromUserName + "</ToUserName>" +
                        "<FromUserName>" + toUserName + "</FromUserName>" +
                        "<CreateTime>" + new Date().getTime() + "</CreateTime>" +
                        "<MsgType>text</MsgType>" +
                        "<Content>已收到！该功能正在开发中....</Content>" +
                        "</xml>";
                return rec;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getText(InputStream inputStream) {

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();//获取所有子节点
            System.out.println(root.getText());
            List<Element> elements = root.elements();
            for (Element element : elements) {
                System.out.println(element.getName() + "---" + element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
