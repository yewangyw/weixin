package com.weixin.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 工具类
 */
public class WXNetUtil {
   static HttpClient httpClient = HttpClientBuilder.create().build();
    //get请求

    /**
     * http中get请求
     * @param url:需要请求的任意url路径
     * @return 该rul中response响应的内容
     */
    public static String httpGet(String url) {
        //1.创建httpClient对象

        //2.创建执行的方法 get
        HttpGet httpGet = new HttpGet(url);
        //3.执行
        try {
            HttpResponse response = httpClient.execute(httpGet);
            //4.解析结果
            StatusLine statusLine = response.getStatusLine();
            System.out.println("======> get请求状态:"+statusLine);
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "UTF-8");
            return string;
        } catch (IOException e) {
            System.out.println("调用weixin-net:get异常"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param url 请求路径
     * @param paramBody 请求体内容:必须是json 格式
     * @return
     */
    public static String httpPost(String url,String paramBody){

        //1.创建对象
        //2.创建请求方式
        HttpPost httpPost = new HttpPost(url);

        //3.封装请求体内容
        httpPost.setHeader("Content-Type","application/json;charset=utf-8");
        try {
            StringEntity entity = new StringEntity(paramBody,"UTF-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            //4.调用请求

            //5.解析结果
            StatusLine statusLine = response.getStatusLine();
            System.out.println("======> get请求状态:"+statusLine);
            HttpEntity responseEntity = response.getEntity();
            String string = EntityUtils.toString(responseEntity, "UTF-8");
            return string;


        } catch (UnsupportedEncodingException e) {
            System.out.println("wx-net:post操作封装异常，必须是json--->"+paramBody);
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("post调用异常:"+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("post调用异常:"+e.getMessage());
            e.printStackTrace();
        }

        return  null;
    }

    public static void main(String[] args) {

       // String string = WXNetUtil.httpGet("https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183");
        //System.out.println(string);

        String token = "22_MJyvIopsLQer6ezctC38lUGn4pDmoezyBO_pBbRq7GRj6m6N34f0uT3roL4gE3p6GOSARuKYJy4jbJz0ixYEb6i34urNoClAf5SqIBMKpYzhiGhuFuWAfymHPrviqkR9pq6cX90PzcXN6V18UYRcAGAXBU";
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
        String param ="{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";

        String post = httpPost(url, param);
        System.out.println(post);
    }



    //post请求
}
