package com.wx.web.controller;

import com.wx.proxy.base.GetImageCodeProxy;
import com.wx.proxy.user.UserListProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/info/list")
    public String userInfoList(Model model){

        //用户列表
        UserListProxy userListProxy = new UserListProxy();
        List<Map> mapList = userListProxy.getUserInfoList();

        //获取图片二维码
        GetImageCodeProxy imageCodeProxy = new GetImageCodeProxy();
        String imageCodeUrl = imageCodeProxy.getImageCode();

        model.addAttribute("uesrList",mapList);
        model.addAttribute("imageCodeUrl",imageCodeUrl);
        return "main";
    }



}
