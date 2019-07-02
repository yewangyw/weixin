package com.wx.proxy.base;

import com.alibaba.fastjson.JSONObject;
import com.weixin.net.WXNetUtil;

/**
 * 获取二维码
 */
public class GetImageCodeProxy {


    /**
     * 获取二维码
     *
     * @return
     */
    public String getImageCode() {

        WXRedisUtil wxRedisUtil = new WXRedisUtil();
        String url = wxRedisUtil.get("image_code_url"); //从缓存中获取
        if (url == null || url.equals("")) {
            String image_code_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + TokenUtil.getToken();
            String requestParam = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"bj1901\"}}}";
            String result = WXNetUtil.httpPost(image_code_url, requestParam);

            //正确:{"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm
            //3sUw==","expire_seconds":60,"url":"http://weixin.qq.com/q/kZgfwMTm72WWPkovabbI"}

            JSONObject jsonObject = JSONObject.parseObject(result);

            if (jsonObject.containsKey("ticket")) {
                //获取票据
                String ticket = jsonObject.getString("ticket");
                //根据票据获取二维码路径
                String imageUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;

                //不过期
                wxRedisUtil.set("image_code_url", imageUrl, 60*60*24*365*20);
                return imageUrl;
            } else {
                throw new RuntimeException("获取tikent 失败+" + result);
            }
        } else {
            System.out.println("----->redis获取imageCode");
            return url;
        }
    }


}
