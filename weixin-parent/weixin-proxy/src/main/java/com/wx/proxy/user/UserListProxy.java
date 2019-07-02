package com.wx.proxy.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weixin.net.WXNetUtil;
import com.wx.proxy.base.TokenUtil;

import java.util.*;

/**
 * 获取用户列表
 */
public class UserListProxy {


    public static void main(String[] args) {
        List<Map> list = new UserListProxy().getUserInfoList();
        System.out.println(list);
    }

    /**
     * 获取用户的详细列表
     * @return
     */
    public List<Map> getUserInfoList(){

        //url
        String user_info_list_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+ TokenUtil.getToken();


        List<String> openidList = getOpenidList();
        //发起  post请求
        String paramBody = createParamBody(openidList);

        //结果处理：参照api
        String result = WXNetUtil.httpPost(user_info_list_url, paramBody);

        JSONObject jsonObject = JSONObject.parseObject(result);

        if(jsonObject.containsKey("user_info_list"))
        {
            String user_info_list = jsonObject.getString("user_info_list");
            List<Map> hashMaps = JSONArray.parseArray(user_info_list, Map.class);
            return hashMaps;
        }else{
            throw new RuntimeException("出现异常..."+result);
        }
    }



    /**
     * 封装参数
     * @param openidList  需要查询的openID
     * @return 封装的数据
     */
    private String createParamBody(List<String> openidList){

       /* {
            "user_list": [
            {
                "openid": "otvxTs4dckWG7imySrJd6jSi0CWE",
                    "lang": "zh_CN"
            },
            {
                "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg",
                    "lang": "zh_CN"
            }
    ]
        }
*/
        //封装数据
        Map<String,List<Map<String,String>>> paramMap =new HashMap<>();

        List<Map<String, String>> list = new ArrayList<>(); //存放修改后的数据

        for (String openId : openidList) {

            Map<String, String> map = new HashMap<>();
            map.put("openid",openId);
            map.put("lang","zh_CN");

            list.add(map);
        }




        paramMap.put("user_list",list);

        //System.out.println(paramMap);

        String paramResult = JSONObject.toJSONString(paramMap);

        return paramResult;
    }

    /**
     * 获取用户的openid
     * @return
     */
    private List<String> getOpenidList(){

        //url
        String openid_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+ TokenUtil.getToken();

        //get请求
        String result = WXNetUtil.httpGet(openid_url);
        //正确时候返回的数据
        //{
       /* "total":2,
                "count":2,
                "data":{
            "openid":["OPENID1","OPENID2"]},
        "next_openid":"NEXT_OPENID"
        }*/

       //错误数据:{"errcode":40013,"errmsg":"invalid appid"}
        JSONObject jsonObject = JSONObject.parseObject(result);

        if(jsonObject.containsKey("data"))
        {
            String openid = jsonObject.getJSONObject("data").getString("openid");
            List<String> list = JSONArray.parseArray(openid, String.class);
            return list;
        }else
        {
           // System.out.println("----->用户列表获取失败....");
            throw new RuntimeException("----->用户列表获取失败...."+result);
          //  return null;
        }


    }





}
