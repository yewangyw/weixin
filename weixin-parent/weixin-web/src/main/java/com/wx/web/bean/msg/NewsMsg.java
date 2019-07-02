package com.wx.web.bean.msg;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsMsg extends BaseMsg {


    private NewsMsgItem news;

    public NewsMsgItem getNews() {
        return news;
    }

    public void setNews(NewsMsgItem news) {
        this.news = news;
    }


    public static void main(String[] args) {


        NewsMsg newsMsg = new NewsMsg();
        newsMsg.setTouser("xxx");
        newsMsg.setMsgtype(MsgType.IMG_MSG);
        NewsMsgItem item = new NewsMsgItem();
         List<NewsContent> list = new ArrayList<>();

        NewsContent content = new NewsContent();
        content.setTitle("油炸蚂蚱");
        content.setDescription("很香很脆");
        content.setPicurl("图片");
        content.setUrl("xxxxxx");
        list.add(content);

        item.setArticles(list);
        newsMsg.setNews(item);


        String s = JSONObject.toJSONString(newsMsg);
        System.out.println(s);


    }

}
