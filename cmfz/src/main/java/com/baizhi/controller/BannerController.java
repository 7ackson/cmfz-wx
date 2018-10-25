package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/selectByPage")
    public @ResponseBody Map selectByPage(int page, int rows){
        Map map = bannerService.selectByPage(page, rows);
        return map;
    }

    @RequestMapping("/insert")
    public @ResponseBody boolean insert(Banner banner){
        System.out.println(banner);
        try {
            bannerService.insert(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/update")
    public void update(Banner banner){
        bannerService.update(banner);
    }

    @RequestMapping("delete")
    public @ResponseBody boolean delete(int[] ids){
        try {
            bannerService.delete(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("selectOne")
    public @ResponseBody Banner selectOne(int id){
        return bannerService.selectOne(id);
    }

}
