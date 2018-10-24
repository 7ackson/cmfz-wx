package com.baizhi.controller;

import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/selectAll")
    public @ResponseBody Map selectAll(){
        Map map = new HashMap();
        map.put("menu",menuService.selectAll());
        return map;
    }

}
