package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public @ResponseBody Map login(Admin admin, String vrifyCode, HttpServletRequest httpServletRequest){
        Map map = new HashMap();
        String yzm = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        if(yzm.equalsIgnoreCase(vrifyCode)){
            if(adminService.login(admin) != null){
                map.put("login",true);
                map.put("log","登陆成功");
            }else{
                map.put("login",false);
                map.put("log","账号或密码错误");
            }
        }else{
            map.put("login",false);
            map.put("log","验证码错误");
        }
        return map;
    }
}
