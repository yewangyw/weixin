package com.hzit.dom4j;

import com.thoughtworks.xstream.XStream;
import com.wx.web.bean.msg.BaseMsg;
import com.wx.web.bean.msg.MsgType;
import org.junit.Test;

public class Test02 {

    /**
     * 对象转为xml
     */
    @Test
    public void test01(){

        BaseMsg baseMsg = new BaseMsg();
        baseMsg.setMsgtype(MsgType.TEXT_MSG);
        baseMsg.setTouser("1001");

        //对象转为xml
        XStream xStream = new XStream();
        xStream.alias("xml",BaseMsg.class); //去别名  将当前对象的路径设置为xml
        String toXML = xStream.toXML(baseMsg);
        System.out.println(toXML);

        //<xml><touser>1001</touser><msgtype>text</msgtype></xml>



    }

    /**
     * xml转为对象
     */
    @Test
    public void test02(){


        String xml = "<xml><touser>1001</touser><msgtype>text</msgtype></xml>";
        //对象转为xml
        XStream xStream = new XStream();
        xStream.alias("xml",BaseMsg.class); //去别名  将当前对象的路径设置为xml
        BaseMsg bseMsg =(BaseMsg) xStream.fromXML(xml);
        System.out.println(bseMsg);

        //<xml><touser>1001</touser><msgtype>text</msgtype></xml>



    }



}
