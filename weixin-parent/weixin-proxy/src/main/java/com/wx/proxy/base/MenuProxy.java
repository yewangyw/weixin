package com.wx.proxy.base;

import com.alibaba.fastjson.JSONObject;
import com.weixin.net.WXNetUtil;

/**
 * 生成菜单
 */
public class MenuProxy {


    /**
     * 创建菜单
     *
     * @param paramBody 菜单json
     * @return
     */
    public boolean createMenu(String paramBody) {
        String create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + TokenUtil.getToken();
        String result = WXNetUtil.httpPost(create_menu_url, paramBody);
        //{"errcode":0,"errmsg":"ok"}
        if ("ok".equals(JSONObject.parseObject(result).getString("errmsg"))) {
            return true;
        }
        System.out.println(result);
        return false;
    }


    public static void main(String[] args) {

        String body = "{\"button\":[{\"type\":\"click\",\"name\":\"石头\",\"key\":\"V1001_SHITOU\"},{\"type\":\"click\",\"name\":\"剪刀\",\"key\":\"V1001_JIANDAN\"},{\"type\":\"click\",\"name\":\"布\",\"key\":\"V1001_BU\"}]}";

        boolean menu = new MenuProxy().createMenu(body);
        System.out.println(menu);


    }


}
