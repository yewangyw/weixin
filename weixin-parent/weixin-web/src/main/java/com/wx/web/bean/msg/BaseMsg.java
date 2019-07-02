package com.wx.web.bean.msg;

/**
 * 消息 公共部分
 */
public class BaseMsg {

    private String touser;
    private String msgtype;

    @Override
    public String toString() {
        return "BaseMsg{" +
                "touser='" + touser + '\'' +
                ", msgtype='" + msgtype + '\'' +
                '}';
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
