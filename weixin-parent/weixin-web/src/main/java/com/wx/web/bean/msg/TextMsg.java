package com.wx.web.bean.msg;

import com.alibaba.fastjson.JSONObject;

/**
 * 文本消息
 */
public class TextMsg extends BaseMsg {

    TextMsgItem text;

    @Override
    public String toString() {
        return "TextMsg{" +
                "text=" + text +
                "} " + super.toString();
    }

    public void setText(TextMsgItem text) {
        this.text = text;
    }

    public TextMsgItem getText() {

        return text;
    }

    public static void main(String[] args) {
        TextMsg textMsg = new TextMsg();
        textMsg.setTouser("xxxx");
        textMsg.setMsgtype(MsgType.TEXT_MSG);

        TextMsgItem text = new TextMsgItem();
        text.setContent("今天吃了啥?");

        textMsg.setText(text);


        String jsonString = JSONObject.toJSONString(textMsg);
        System.out.println(jsonString);

    }
}

